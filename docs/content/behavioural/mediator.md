---
layout: page
title: 中介者模式
permalink: mediator.html
---

> 用一个中介者对象封装一系列的对象交互，中介者使各对象不需要显示地相互作用，从而使耦合松散，而且可以独立地改变它们之间的交互。通过使用一个中间对象来进行消息分发以及减少类之间的直接依赖。  

### JDK中的使用
- java.util.Timer#schedule(java.util.TimerTask, java.util.Date)
- java.lang.reflect.Method#invoke(java.lang.Object, java.lang.Object...)
- java.util.concurrent.ExecutorService#submit(java.util.concurrent.Callable)

### 代码实现
ATM作为中介者 让银行和客户交互 以取存款为例  
```java
// 虚拟出纳
public interface Teller {
    /**
     * 取钱操作
     * @param account 卡号
     * @param amount 金额
     */
    void withdraw(String account, BigDecimal amount);
    /**
     * 存钱操作
     * @param account 卡号
     * @param amount 金额
     */
    void deposit(String account, BigDecimal amount);
}
public class Bank implements Teller {
    @Getter
    private BigDecimal balance;

    public Bank(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public void withdraw(String account, BigDecimal amount) {
        // 客户取钱 银行余额减少
        this.balance = this.balance.subtract(amount);
    }

    @Override
    public void deposit(String account, BigDecimal amount) {
        // 客户存钱 银行余额增加
        this.balance = this.balance.add(amount);
    }
}
public class Customer implements Teller {
    @Getter
    private BigDecimal cash;

    public Customer(BigDecimal cash) {
        this.cash = cash;
    }

    @Override
    public void withdraw(String account, BigDecimal amount) {
        // Atm取钱 客户现金增加
        this.cash = this.cash.add(amount);
    }

    @Override
    public void deposit(String account, BigDecimal amount) {
        // Atm存钱 客户现金减少
        this.cash = this.cash.subtract(amount);
    }
}
public class Atm implements Teller {
    private Bank bank;
    private Customer customer;

    public Atm(Bank bank, Customer customer) {
        this.bank = bank;
        this.customer = customer;
    }

    @Override
    public void withdraw(String account, BigDecimal amount) {
        this.bank.withdraw(account, amount);
        this.customer.withdraw(account, amount);
    }

    @Override
    public void deposit(String account, BigDecimal amount) {
        this.bank.deposit(account, amount);
        this.customer.deposit(account, amount);
    }
}
```

### 单元测试
```groovy
class AtmSpec extends Specification {
    def atm(){
        given:
        def bank = new Bank(new BigDecimal("1000"))
        def customer1 = new Customer(new BigDecimal("250"))
        def customer2 = new Customer(new BigDecimal("200"))

        when:
        def atm1 = new Atm(bank, customer1)
        atm1.deposit("1234", new BigDecimal("100"))
        then:
        customer1.getCash() == new BigDecimal("150")
        bank.getBalance() == new BigDecimal("1100")


        when:
        atm1.withdraw("1234", new BigDecimal("50"))
        then:
        customer1.getCash() == new BigDecimal("200")
        bank.getBalance() == new BigDecimal("1050")

        when:
        def atm2 = new Atm(bank, customer2)
        atm2.withdraw("4321", new BigDecimal("20"))
        then:
        customer2.getCash() == new BigDecimal("220")
        bank.getBalance() == new BigDecimal("1030")

        when:
        atm2.deposit("4321", new BigDecimal("70"))
        then:
        customer2.getCash() == new BigDecimal("150")
        bank.getBalance() == new BigDecimal("1100")
    }
}
```