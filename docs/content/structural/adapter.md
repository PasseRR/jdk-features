---
layout: page
title: 适配器模式
permalink: adapter.html
---

> 适配器模式将某个类的接口转换成客户端期望的另一个接口表示，目的是消除由于接口不匹配所造成的类的兼容性问题。

### JDK中的使用
- java.util.Arrays#asList()
- java.util.Collections#list()
- java.util.Collections#enumeration()
- java.io.InputStreamReader#InputStreamReader(java.io.InputStream)
- java.io.OutputStreamWriter#OutputStreamWriter(java.io.OutputStream)

### 代码实现
美国使用的三孔插座且需要的电压是110V 中国使用的是两孔插座且且需要电压是220V 三孔插座拿到中国需要转换为两孔插座才能使用  
```java
public interface TwoPinSoket {
    String chargeWithTwoPin();
    int voltage();
}
public class TwoPinSoketChina implements TwoPinSoket {
    @Override
    public String chargeWithTwoPin() {
        return "两孔插座";
    }
    @Override
    public int voltage() {
        return 220;
    }
}
public interface ThreePinSoket {
    String chargeWithThreePin();
    int voltage();
}
public class ThreePinSoketAmerica implements ThreePinSoket {
    @Override
    public String chargeWithThreePin() {
        return "三孔插座";
    }

    @Override
    public int voltage() {
        return 110;
    }
}
```
美国插座的适配器  
```java
public class AmericaAdapter implements TwoPinSoket {
    private ThreePinSoket threePinSoket;
    public AmericaAdapter(ThreePinSoket threePinSoket){
        this.threePinSoket = threePinSoket;
    }

    @Override
    public String chargeWithTwoPin() {
        return this.threePinSoket.chargeWithThreePin();
    }

    @Override
    public int voltage() {
        return this.threePinSoket.voltage() * 2;
    }
}
```

### 单元测试
```groovy
class AmericaAdapterSpec extends Specification {
    def twoPinSoket(){
        given:
        def twoPinSoket = new TwoPinSoketChina()
        expect:
        twoPinSoket.chargeWithTwoPin() == "两孔插座"
        twoPinSoket.voltage() == 220
    }

    def threePinSoket(){
        given:
        def threePinSoket = new ThreePinSoketAmerica()
        expect:
        threePinSoket.chargeWithThreePin() == "三孔插座"
        threePinSoket.voltage() == 110
    }

    def adapter(){
        given:
        def threePinSoket = new ThreePinSoketAmerica()
        def twoPinSoket = new AmericaAdapter(threePinSoket)
        expect:
        twoPinSoket.chargeWithTwoPin() == "三孔插座"
        twoPinSoket.voltage() == 220
    }
}
```