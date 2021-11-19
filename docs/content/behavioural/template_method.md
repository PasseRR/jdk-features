---
layout: page
title: 模版方法模式
permalink: template_method.html
---

> 准备一个抽象类，将部分逻辑以具体方法以及具体构造函数的形式实现，然后声明一些抽象方法来迫使子类实现剩余的逻辑。不同的子类可以以不同的方式实现这些抽象方法，从而对剩余的逻辑有不同的实现。让子类可以重写方法的一部分，而不是整个重写，你可以控制子类需要重写那些操作。  

### JDK中的使用
- java.util.AbstractCollection#iterator()
- java.util.AbstractCollection#size()
- java.util.Collections#sort(java.util.List, java.util.Comparator)
- java.io.InputStream#read()
- spring service事务拦截 service就是模板方法

### 代码实现
手动档汽车和自动档汽车起步的步骤 手动档钥匙点火->挂1挡->起步 自动档按钮点火->挂D挡->起步  
```java
public interface Car {
    String drive();
}
public abstract class AbstractCar implements Car {
    @Override
    public String drive() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.ignite());
        sb.append(" -> ");
        sb.append(this.shift());
        sb.append(" -> ");
        sb.append(this.advance());

        return sb.toString();
    }

    /**
     * 点火
     * @return
     */
    protected abstract String ignite();

    /**
     * 挂挡
     * @return
     */
    protected abstract String shift();

    /**
     * 前进
     * @return
     */
    protected String advance(){
        return "前进";
    }
}
public class ManualGearshiftCar extends AbstractCar {
    @Override
    protected String ignite() {
        return "钥匙点火";
    }

    @Override
    protected String shift() {
        return "挂1挡";
    }
}
public class AutomaticGearshiftCar extends AbstractCar {
    @Override
    protected String ignite() {
        return "按钮点火";
    }

    @Override
    protected String shift() {
        return "挂D挡";
    }
}
```

### 单元测试
```groovy
class CarSpec extends Specification {
    def car() {
        given:
        def Car myCar = car

        expect:
        myCar.drive() == result

        where:
        car                         || result
        new ManualGearshiftCar()    || "钥匙点火 -> 挂1挡 -> 前进"
        new AutomaticGearshiftCar() || "按钮点火 -> 挂D挡 -> 前进"
    }
}
```