---
layout: page
title: 装饰器模式
permalink: decorator.html
---

> 装饰模式以对客户端透明的方式扩展对象的功能，是继承关系的一个替代方案

### JDK中的使用
- java.io.BufferedInputStream#BufferedInputStream(java.io.InputStream)
- java.io.BufferedOutputStream#BufferedOutputStream(java.io.OutputStream)
- java.io.DataInputStream#DataInputStream(java.io.InputStream)
- java.util.Collections.CheckedList
- java.util.Collections.CheckedSet
- ava.util.Collections.CheckedMap

### 代码实现
齐天大圣可以72变 在和杨戬大战中分别变了鸟、鱼、树、寺庙、本身猴子 各个变换之间来回变化 
```java
public interface TheGreatestSage {
    String move();
}
public class Monkey implements TheGreatestSage {
    private TheGreatestSage theGreatestSage;
    public Monkey() {

    }

    public Monkey(TheGreatestSage theGreatestSage) {
        this.theGreatestSage = theGreatestSage;
    }
    @Override
    public String move() {
        StringBuilder sb = new StringBuilder();
        if (null != this.theGreatestSage) {
            sb.append(this.theGreatestSage.move());
            sb.append(" -> ");
        }

        sb.append("monkey");

        return sb.toString();
    }
}
public class Bird implements TheGreatestSage {
    private TheGreatestSage theGreatestSage;
    public Bird(TheGreatestSage theGreatestSage){
        this.theGreatestSage = theGreatestSage;
    }

    @Override
    public String move() {
        StringBuilder sb = new StringBuilder(this.theGreatestSage.move());
        sb.append(" -> ");
        sb.append("bird");

        return sb.toString();
    }
}
public class Fish implements TheGreatestSage {
    private TheGreatestSage theGreatestSage;
    public Fish(TheGreatestSage theGreatestSage){
        this.theGreatestSage = theGreatestSage;
    }

    @Override
    public String move() {
        StringBuilder sb = new StringBuilder(this.theGreatestSage.move());
        sb.append(" -> ");
        sb.append("fish");

        return sb.toString();
    }
}
public class Tree implements TheGreatestSage {
    private TheGreatestSage theGreatestSage;
    public Tree(TheGreatestSage theGreatestSage){
        this.theGreatestSage = theGreatestSage;
    }

    @Override
    public String move() {
        StringBuilder sb = new StringBuilder(this.theGreatestSage.move());
        sb.append(" -> ");
        sb.append("tree");

        return sb.toString();
    }
}
public class Temple implements TheGreatestSage {
    private TheGreatestSage theGreatestSage;
    public Temple(TheGreatestSage theGreatestSage){
        this.theGreatestSage = theGreatestSage;
    }

    @Override
    public String move() {
        StringBuilder sb = new StringBuilder(this.theGreatestSage.move());
        sb.append(" -> ");
        sb.append("temple");

        return sb.toString();
    }
}
```

### 单元测试
```groovy
class TheGreatestSageSpec extends Specification {
    def theGreatestSage() {
        expect:
        theGreatestSage.move() == changeProcess
        where:
        theGreatestSage                                                    || changeProcess
        new Monkey()                                                       || "monkey"
        new Bird(new Monkey())                                             || "monkey -> bird"
        new Tree(new Fish(new Monkey()))                                   || "monkey -> fish -> tree"
        new Fish(new Bird(new Temple(new Monkey())))                       || "monkey -> temple -> bird -> fish"
        new Temple(new Tree(new Bird(new Fish(new Monkey()))))             || "monkey -> fish -> bird -> tree -> temple"
        new Monkey(new Tree(new Temple(new Bird(new Fish(new Monkey()))))) || "monkey -> fish -> bird -> temple -> tree -> monkey"
    }
}
```