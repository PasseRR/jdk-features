---
layout: page
title: 工厂方法模式
permalink: factory_method.html
---

> 定义一个用于创建对象的接口，**需要指定创建对象具体类**。Factory Method使一个类的实例化延迟到其子类，返回具体对象的方法。

### JDK中的使用
- java.lang.Integer#valueOf(java.lang.String)
- java.lang.Class#forName(java.lang.String)
- java.lang.reflect.Constructor#newInstance(java.lang.Object...)
- java.lang.reflect.Proxy#newProxyInstance(java.lang.ClassLoader, java.lang.Class[], java.lang.reflect.InvocationHandler)

### 代码实现
有一个生产车的工厂，在你需要什么车的时候生产什么车，与抽象工厂方法的区别就是在使用工厂的时候需要知道具体类
```java
public class CarFactory {
    public Car create(Class<? extends Car> clazz){
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
}
```
车的种类可以水平扩展，工厂不需要变化  
```java
public interface Car {
    String getName();
}

public class BmwCar implements Car {
    @Override
    public String getName() {
        return "Bmw";
    }
}

public class BenzCar implements Car {
    @Override
    public String getName() {
        return "Benz";
    }
}

public class AudiCar implements Car{
    @Override
    public String getName() {
        return "Audi";
    }
}
```

### 单元测试 
```groovy
class CarFactorySpec extends Specification {
    def create(){
        given:
        def carFactory = new CarFactory()
        def benz = carFactory.create(BenzCar.class)
        def bmw = carFactory.create(BmwCar.class)
        def audi = carFactory.create(AudiCar.class)

        expect:
        benz != null
        bmw != null
        audi != null
        benz instanceof BenzCar
        bmw instanceof BmwCar
        audi instanceof AudiCar
        benz.getName() == "Benz"
        bmw.getName() == "Bmw"
        audi.getName() == "Audi"
    }
}
```