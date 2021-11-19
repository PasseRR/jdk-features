---
layout: page
title: 访问者模式
permalink: visitor.html
---

> 访问者模式的目的是封装一些施加于某种数据结构元素之上的操作。一旦这些操作需要修改的话，接受这个操作的数据结构则可以保持不变。  

### JDK中的使用
访问者一般成对出现 即访问者和被访问者  
- java.util.function.Consumer java.util.List#forEach(java.util.function.Consumer)
- java.util.function.BiConsumer java.util.Map#forEach(java.util.function.BiConsumer)
- java.util.function.BiFunction java.util.Map#replaceAll(java.util.function.BiFunction)
- javax.lang.model.element.AnnotationValue javax.lang.model.element.AnnotationValueVisitor
- javax.lang.model.element.Element javax.lang.model.element.ElementVisitor
- javax.lang.model.type.TypeMirror javax.lang.model.type.TypeVisitor

### 代码实现
```java
@Data
public class Order {
    /**
     * 周几 1~7 周一~周日
     */
    private int dayOfWeek;
    /**
     * 金额
     */
    private BigDecimal amount;
}
@FunctionalInterface
public interface Visitable<T> {
    void accept(T t);
}
// MyList见迭代器模式
public class VisitableList<T> extends MyList<T>{
    /**
     * 类似forEach 实现自己的访问者
     * @param visitable 访问者
     */
    public void visitEach(Visitable<? super T> visitable) {
        for(T t : this){
            visitable.accept(t);
        }
    }
}
```

### 单元测试
```groovy
class VisitableListSpec extends Specification {
    def visit() {
        given:
        // 记录原始数据
        def book = new HashMap<Integer, Order>()
        book.put(1, new Order(dayOfWeek: 1, amount: 6))
        book.put(2, new Order(dayOfWeek: 2, amount: 12))
        book.put(5, new Order(dayOfWeek: 5, amount: 7))
        book.put(6, new Order(dayOfWeek: 6, amount: 11))
        book.put(7, new Order(dayOfWeek: 7, amount: 8))
        // 复制数据
        def list = new VisitableList<Order>()
        book.each { key, value ->
            list.add(new Order(dayOfWeek: value.getDayOfWeek(), amount: value.getAmount()))
        }
        when:
        // 工作日 统一总价打八折
        // 使用闭包/lambda实现Visitable接口
        list.visitEach({ t ->
            if (t.getDayOfWeek() < 6) {
                t.setAmount(t.getAmount() * 0.8)
            }
        })
        then:
        // 依次遍历list 确保数据一致
        list.every{ it ->
            def day = it.getDayOfWeek()
            if(day < 6){
                it.getAmount() == book.get(day).getAmount() * 0.8
            }else{
                it.getAmount() == book.get(day).getAmount()
            }
        }

        when:
        // 周末 价格大于10块 打九折
        list.visitEach({ t ->
            if (t.getDayOfWeek() > 5 && t.getAmount() >= 10) {
                t.setAmount(t.getAmount() * 0.9)
            }
        })
        then:
        // 依次遍历list 确保数据一致
        list.every { it ->
            def day = it.getDayOfWeek()
            if(day < 6){
                it.getAmount() == book.get(day).getAmount() * 0.8
            }else if(day > 5 && book.get(day).getAmount() >= 10){
                it.getAmount() == book.get(day).getAmount() * 0.9
            }else{
                it.getAmount() == book.get(day).getAmount()
            }
        }
    }
}
```