---
layout: page
title: 抽象工厂模式
permalink: abstract_factory.html
---

> 提供一个创建一系列相关或相互依赖对象的接口，而**无需指定它们具体的类**。  
> 创建新对象的方法，但是返回的是接口或者抽象类。

### JDK中的使用
- java.util.ResourceBundle#getBundle(java.lang.String)
- java.sql.Connection#createStatement()
- java.util.Calendar#getInstance()

### 代码实现
电视有很多品牌，每个品牌加工厂可以生产不同尺寸的电视，抽象工厂确定有多少种尺寸的电视，不同品牌的工厂可以生产不同品牌的电视
```java
public interface Tv {
    String getName();
}
```
假设工厂只能生产21和50英寸的电视 以海尔为例  
```java
public interface TvFactory {
    Tv create21InchTv();
    Tv create50InchTv();
}

public class Haier21InchTv implements Tv {
    @Override
    public String getName() {
        return "Haier 21 inch";
    }
}

public class Haier50InchTv implements Tv {
    @Override
    public String getName() {
        return "Haier 50 inch";
    }
}
```
如下分别为海尔和索尼的电视工厂  
```java
public class HaierTvFactory implements TvFactory {
    @Override
    public Tv create21InchTv() {
        return new Haier21InchTv();
    }

    @Override
    public Tv create50InchTv() {
        return new Haier50InchTv();
    }
}

public class SonyTvFactory implements TvFactory {
    @Override
    public Tv create21InchTv() {
        return new Sony21InchTv();
    }

    @Override
    public Tv create50InchTv() {
        return new Sony50InchTv();
    }
}
```
### 单元测试 
```groovy
class HaierTvFactorySpec extends Specification {
    def "create 21 inch haier tv"(){
        given:
        def factory = new HaierTvFactory()
        def tv = factory.create21InchTv()

        expect:
        tv != null
        tv instanceof Haier21InchTv
    }

    def "create 50 inch haier tv"(){
        given:
        def factory = new HaierTvFactory()
        def tv = factory.create50InchTv()

        expect:
        tv != null
        tv instanceof Haier50InchTv
    }
}
```