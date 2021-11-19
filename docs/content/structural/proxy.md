---
layout: page
title: 代理模式
permalink: proxy.html
---

> 代理模式就是多一个代理类出来，替原对象进行一些操作。  
> 代理模式分为两种，静态代理和动态代理  

- 静态代理：由程序员创建或工具生成代理类的源码，再编译代理类。所谓静态也就是在程序运行前就已经存在代理类的字节码文件，代理类和委托类的关系在运行前就确定了。
- 动态代理：动态代理类的源码是在程序运行期间由JVM根据反射等机制动态的生成，所以不存在代理类的字节码文件。代理类和委托类的关系是在程序运行时确定。

### 代码实现
学生、白领通过中介租房  
```java
public interface Renting{
    String rent();
}
public class Student implements Renting {
    @Override
    public String rent() {
        return "student rent";
    }
    public String name(){
        return "student";
    }
}
public class WhiteCollarWorker implements Renting {
    @Override
    public String rent() {
        return "white collar worker rent";
    }
}
```

#### 静态代理
运行之前代理类和委托类关系就已经确定  
```java
public class Realtor implements Renting {
    private Renting renting;
    public Realtor(Renting renting){
        this.renting = renting;
    }

    @Override
    public String rent() {
        return "realtor in the name of " + this.renting.rent();
    }
}
```

#### 动态代理
动态代理分为jdk动态代理和cglib动态代理  
- jdk动态代理通过反射类Proxy和InvocationHandler回调接口实现，要求委托类必须实现一个接口，只能对该类接口中定义的方法实现代理，这在实际编程中有一定的局限性。
- cglib是针对类来实现代理的，他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的类进行代理。

```java
public class JdkProxy implements InvocationHandler {
    private Object target;

    public JdkProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.target, args);
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
            this.target.getClass().getClassLoader(),
            this.target.getClass().getInterfaces(),
            this
        );
    }
}

public class CglibProxy implements MethodInterceptor {
    private Object target;
    public CglibProxy(Object target){
        this.target = target;
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) 
        throws Throwable {
        return methodProxy.invokeSuper(o, objects);
    }

    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);

        return enhancer.create();
    }
}
```

### 单元测试
静态代理  
```java
class RealtorSpec extends Specification {
    def renting() {
        expect:
        renting.rent() == result
        where:
        renting                 || result
        new Student()           || "student rent"
        new WhiteCollarWorker() || "white collar worker rent"
    }

    def realtor() {
        expect:
        realtor.rent() == result
        where:
        realtor                              || result
        new Realtor(new Student())           || "realtor in the name of student rent"
        new Realtor(new WhiteCollarWorker()) || "realtor in the name of white collar worker rent"
    }
}
```
动态代理  
```groovy
class JdkProxySpec extends Specification {
    def jdkProxyInterface() {
        given:
        def jdkProxy = new JdkProxy(target)
        def instance = (Renting) jdkProxy.getProxyInstance()
        expect:
        instance.rent() == result
        where:
        target                  || result
        new Student()           || "student rent"
        new WhiteCollarWorker() || "white collar worker rent"
    }

    def jdkProxyClass(){
        given:
        def jdkProxy = new JdkProxy(new Student())

        when:
        // 代理类不能被强转为Student
        def studentProxy = (Student) jdkProxy.getProxyInstance()
        then:
        thrown(Exception.class)
        studentProxy == null
    }
}

class CglibProxySpec extends Specification {
    def cglibProxyInterface(){
        given:
        def cglibProxy = new CglibProxy(target)
        def instance = (Renting) cglibProxy.getProxyInstance()
        expect:
        instance.rent() == result
        where:
        target                  || result
        new Student()           || "student rent"
        new WhiteCollarWorker() || "white collar worker rent"
    }

    def cglibProxyClass(){
        given:
        def cglibProxy = new CglibProxy(new Student())
        // 和jdk动态代理主要区别
        // jdk 获得实例返回的是接口
        // cglib 获得实例返回的是类
        def instance = (Student) cglibProxy.getProxyInstance()

        expect:
        instance.rent() == "student rent"
        instance.name() == "student"
    }
}
```