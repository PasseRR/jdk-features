---
layout: page
title: 策略模式
permalink: strategy.html
---

> 每一个算法封装到具有共同接口的独立的类中，从而使得它们可以相互替换。策略模式使得算法可以在不影响到客户端的情况下发生变化。  

### JDK中的使用
- java.util.Comparator
- java.util.concurrent.RejectedExecutionHandler
- java.io.FilenameFilter
- java.io.FileFilter

### 代码实现
在超时购买商品在不同时期有不同的优惠策略 比如正常策略、促销策略、临期策略等  
```java
public class Order {
    private BigDecimal amount;
    public Order(BigDecimal amount){
        this.amount = amount;
    }

    public BigDecimal check(DiscountPolicy policy){
        return policy.discount(this.amount);
    }
}
@FunctionalInterface
public interface DiscountPolicy {
    BigDecimal discount(BigDecimal amount);
}
public class NormalPolicy implements DiscountPolicy {
    @Override
    public BigDecimal discount(BigDecimal amount) {
        return amount;
    }
}
public class PromotioPolicy implements DiscountPolicy {
    @Override
    public BigDecimal discount(BigDecimal amount) {
        // 促销9折
        return amount.multiply(new BigDecimal("0.9"));
    }
}
public class DeadlinePolicy implements DiscountPolicy {
    @Override
    public BigDecimal discount(BigDecimal amount) {
        // 临期6折
        return amount.multiply(new BigDecimal("0.6"));
    }
}
```

### 单元测试
```groovy
class OrderSpec extends Specification {
    @Shared
    def selfPolicy = { BigDecimal it ->
        return it * (new BigDecimal("0.1"))
    }

    def order() {
        given:
        def order = new Order(new BigDecimal("100"))
        expect:
        order.check(policy) == amount

        where:
        policy               || amount
        new NormalPolicy()   || 100
        new PromotioPolicy() || 90
        new DeadlinePolicy() || 60
        // 自定义闭包策略
        selfPolicy           || 10
    }
}
```