---
layout: page
title: 单例模式
permalink: singleton.html
---

> 保证一个类仅有一个实例，并提供一个访问它的全局访问点。

### JDK中的使用
- java.lang.Runtime#getRuntime()
- java.awt.Desktop#getDesktop()
- java.lang.System#getSecurityManager()

### 代码实现
#### 懒汉式  
实例在第一次使用的时候初始化
```java
public class LazySingleton {
    private static LazySingleton instance;
    private LazySingleton(){

    }

    public static synchronized LazySingleton getInstance(){
        if(null == instance){
            instance = new LazySingleton();
        }

        return instance;
    }
}
```
#### 双重锁实现  
懒汉式在多线程情况下会失效 由此进化而来
```java
public class DoubleLockSingleton {
    private static volatile DoubleLockSingleton instance;
    private DoubleLockSingleton(){

    }

    public static DoubleLockSingleton getInstance(){
        if(null == instance){
            synchronized (DoubleLockSingleton.class){
                if(null == instance){
                    instance = new DoubleLockSingleton();
                }
            }
        }

        return instance;
    }
}
```
#### 饿汉式  
实例在类加载的时候初始化
```java
public class HungrySingleton {
    private HungrySingleton(){

    }

    public static HungrySingleton getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder{
        private static final HungrySingleton INSTANCE = new HungrySingleton();
    }
}
```
#### 枚举实现  
目前最为安全的实现单例的方法是通过内部静态enum的方法来实现，因为JVM会保证enum不能被反射并且构造器方法只执行一次
```java
public enum EnumSingleton {
    INSTANCE
}
```

### 单元测试
以DoubleLockSingleton为例  
```groovy
class DoubleLockSingletonSpec extends Specification{
    def "is DoubleLockSingleton singleton"(){
        given:
        def instance = DoubleLockSingleton.getInstance()
        def anotherInstance = DoubleLockSingleton.getInstance()

        expect:
        instance != null
        anotherInstance != null
        instance == anotherInstance
        instance.is(anotherInstance)
    }
}
```