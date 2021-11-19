---
layout: page
title: 观察者模式
permalink: observer.html
---

> 观察者模式又叫发布-订阅(Publish/Subscribe)模式、模型-视图(Model/View)模式、源-监听器(Source/Listener)模式或从属者(Dependents)模式。观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。这个主题对象在状态上发生变化时，会通知所有观察者对象，使它们能够自动更新自己。  

### JDK中的使用
- java.util.Observer
- java.util.Observable

### 代码实现
水果店员没卖出一个订单的水果 老板会关心每种水果卖出了多少 老板娘会关心一共卖出了多少钱  
```java
@Getter
public enum Fruit {
    APPLE("苹果", new BigDecimal("2")),
    ORANGE("桔子", new BigDecimal("1.5")),
    GRAPE("葡萄", new BigDecimal("3")),
    PLUM("李子", new BigDecimal("6.5"));

    private String name;
    private BigDecimal unitPrice;
    Fruit(String name, BigDecimal unitPrice){
        this.name = name;
        this.unitPrice = unitPrice;
    }
}
@Data
@AllArgsConstructor
public class Order {
    private Fruit type;
    private BigDecimal weight;
}
public class FruitStoreAssistant extends Observable {
    public FruitStoreAssistant(Observer ...observers){
        super();
        if(observers != null && observers.length > 0){
            for(Observer observer : observers){
                super.addObserver(observer);
            }
        }
    }

    /**
     * 贩卖水果
     * @param order 水果订单
     */
    public void sell(Order order){
        super.setChanged();
        super.notifyObservers(order);
    }
}
@Getter
public class FruitsStoreBoss implements Observer {
    private Map<Fruit, BigDecimal> statistics = new HashMap<>();
    private BigDecimal total = new BigDecimal("0");

    public FruitsStoreBoss(){
        Fruit []fruits = Fruit.values();
        for(Fruit fruit : fruits){
            statistics.put(fruit, new BigDecimal("0"));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Order){
            this.account((Order) arg);
        }
    }

    private void account(Order order){
        Fruit fruit = order.getType();
        BigDecimal current = this.statistics.get(fruit);
        this.statistics.put(fruit, current.add(order.getWeight()));
        this.total = this.total.add(order.getWeight());
    }
}
@Getter
public class FruitsStoreLandlady implements Observer {
    private Map<Fruit, BigDecimal> statistics = new HashMap<>();
    private BigDecimal total = new BigDecimal("0");

    public FruitsStoreLandlady(){
        Fruit []fruits = Fruit.values();
        for(Fruit fruit : fruits){
            statistics.put(fruit, new BigDecimal("0"));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Order){
            this.account((Order) arg);
        }
    }

    private void account(Order order){
        Fruit fruit = order.getType();
        BigDecimal current = this.statistics.get(fruit);
        BigDecimal orderTotal = fruit.getUnitPrice().multiply(order.getWeight());
        this.statistics.put(fruit, current.add(orderTotal));
        this.total = this.total.add(orderTotal);
    }
}
```

### 单元测试
```groovy
class FruitStoreSpec extends Specification {
    def fruitStoreEach() {
        given:
        def boss = new FruitsStoreBoss()
        def landlady = new FruitsStoreLandlady()
        def assistant = new FruitStoreAssistant(boss, landlady)

        when:
        assistant.sell(new Order(fruit, weight))
        then:
        landlady.getTotal() == totalAmount
        boss.getTotal() == totalWeight

        where:
        fruit        | weight || totalAmount                  || totalWeight
        Fruit.PLUM   | 8.7    || Fruit.PLUM.unitPrice * 8.7   || 8.7
        Fruit.APPLE  | 1      || Fruit.APPLE.unitPrice * 1    || 1
        Fruit.GRAPE  | 2.3    || Fruit.GRAPE.unitPrice * 2.3  || 2.3
        Fruit.ORANGE | 2.5    || Fruit.ORANGE.unitPrice * 2.5 || 2.5
    }
}
```