---
layout: page
title: 迭代器模式
permalink: iterator.html
---
> 迭代器模式可以顺序地访问一个聚集中的元素而不必暴露聚集的内部表象（internal representation）  

### JDK中的使用
- java.util.Iterator
- java.util.Enumeration

### 代码实现
实现一个List的迭代器  

```java
class MyIterator<T> implements Iterator<T> {
    private LinkedList<T> list;
    private int index;

    MyIterator(LinkedList<T> list) {
        this.list = list;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.list.size();
    }

    @Override
    public T next() {
        return this.list.get(this.index ++);
    }
}

// LinkedList见组合模式
public class MyList<T> extends LinkedList<T> implements Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(this);
    }
}
```
### 单元测试
```groovy
class MyListSpec extends Specification {
    def myList(){
        def data
        given:
        def list = new MyList()
        list.add("hello")
        list.add("world")
        list.add("!")
        def iterator = list.iterator()

        expect:
        iterator != null
        iterator.hasNext()

        when:
        data = iterator.next()
        then:
        data == "hello"
        iterator.hasNext()

        when:
        data = iterator.next()
        then:
        data == "world"
        iterator.hasNext()

        when:
        data = iterator.next()
        then:
        data == "!"
        !iterator.hasNext()
    }
}
```