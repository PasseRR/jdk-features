---
layout: page
title: 外观模式
permalink: facade.html
---

> 为子系统中的一组接口提供一个一致的界面，Facade模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。

### JDK中的使用
- javax.faces.context.FacesContext
- 日志log4j等
- 常见于controller、service、dao，在controller直接与service交互，service为高层接口,dao为子系统。

### 代码实现
用户发起一笔交易，余额减少，积分增加  
```java
@Data
@Builder
public class Trade {
    private BigInteger userId;
    private BigDecimal points;
    private BigDecimal cash;
    private BigDecimal consumeCash;
    private BigDecimal awardPoints;
}

public class CashDao {
    // 现金/余额记账
    public void accountCash(Trade trade){
        BigDecimal currentCash = trade.getCash();
        BigDecimal consumeCash = trade.getConsumeCash();
        trade.setCash(currentCash.subtract(consumeCash));
    }
}

public class PointDao {
    // 积分记账
    public void accountPoint(Trade trade){
        BigDecimal currentPoints = trade.getPoints();
        BigDecimal awardPoints = trade.getAwardPoints();
        trade.setPoints(currentPoints.add(awardPoints));
    }
}

public class TradeService {
    // Spring inject
    private CashDao cashDao = new CashDao();
    private PointDao pointDao = new PointDao();

    // 用户使用现金消费 积分增加
    public void consume(Trade trade){
        this.cashDao.accountCash(trade);
        this.pointDao.accountPoint(trade);
    }
}
```
对于客户端来说只知道TradeService却感知不到CashDao、PointDao  
### 单元测试
```groovy
class TradeServiceSpec extends Specification {
    def "facade"() {
        given:
        def trade = Trade.builder()
            .userId(new BigInteger("1"))
            .cash(new BigDecimal("20"))
            .points(new BigDecimal("100"))
            .consumeCash(new BigDecimal("10"))
            .awardPoints(new BigDecimal("100"))
            .build()
        def tradeService = new TradeService()

        when:
        tradeService.consume(trade)
        then:
        trade.getCash() == new BigDecimal("10")
        trade.getPoints() == new BigDecimal("200")
    }

    def "not use facade"(){
        given:
        def trade = Trade.builder()
            .userId(new BigInteger("1"))
            .cash(new BigDecimal("20"))
            .points(new BigDecimal("100"))
            .consumeCash(new BigDecimal("10"))
            .awardPoints(new BigDecimal("100"))
            .build()
        def cashDao = new CashDao()
        def pointDao = new PointDao()

        when:
        cashDao.accountCash(trade)
        then:
        trade.getCash() == new BigDecimal("10")

        when:
        pointDao.accountPoint(trade)
        then:
        trade.getPoints() == new BigDecimal("200")
    }
}
```