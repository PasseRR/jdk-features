---
layout: page
title: 享元模式
permalink: flyweight.html
---
> 享元模式以共享的方式高效地支持大量的细粒度对象，包括内部状态和外部状态。内部状态是存储在享元对象内部的，并且是不会随环境的改变而有所不同。因此，一个享元可以具有内部状态并可以共享。外部状态是随环境的改变而改变的、不可以共享的。享元对象的外部状态必须由客户端保存，并在享元对象被创建之后，在需要使用的时候再传入到享元对象内部。外部状态不可以影响享元对象的内部状态，它们是相互独立的。

### JDK中的使用
- java.lang.Integer#valueOf(int)
- java.lang.Byte#valueOf(byte)
- Boolean, Byte, Character, Short, Long, BigDecimal 

### 代码实现
绘制不同颜色的不同图形 将已经绘制过的图形保存起来不用再次绘制  
```java
public interface Shape {
    String draw(Color color);
}
public class ConcreteShape implements Shape {
    private String shape;
    public ConcreteShape(String shape) {
        this.shape = shape;
    }

    @Override
    public String draw(Color color) {
        StringBuilder sb = new StringBuilder();
        sb.append("draw a");
        sb.append(" ");
        sb.append(color.getColor());
        sb.append(" ");
        sb.append(this.shape);

        return sb.toString();
    }
}
public class Color {
    @Getter
    private String color;
    public Color(String color){
        this.color = color;
    }
}
public class FlyweightFactory {
    private final Map<String, Shape> cache = new ConcurrentHashMap<>();

    public Shape create(String shape){
        if(!this.cache.containsKey(shape)){
            synchronized (this.cache){
                if(!this.cache.containsKey(shape)){
                    this.cache.put(shape, new ConcreteShape(shape));
                }
            }
        }

        return this.cache.get(shape);
    }

    public int size(){
        return this.cache.size();
    }
}
```

### 单元测试
```groovy
class FlyweightFactorySpec extends Specification {
    def flyweightInnerState() {
        given:
        def factory = new FlyweightFactory()

        when:
        def circle = factory.create("circle")
        def circleCopy = factory.create("circle")
        then:
        circle != null
        circleCopy != null
        circle == circleCopy
        circle.is(circleCopy)

        when:
        def rectangle = factory.create("rectangle")
        def rectangleCopy = factory.create("rectangle")
        then:
        rectangle != null
        rectangleCopy != null
        rectangle == rectangleCopy
        rectangle.is(rectangleCopy)

        when:
        def triangle = factory.create("triangle")
        def triangleCopy = factory.create("triangle")
        then:
        triangle != null
        triangleCopy != null
        triangle == triangleCopy
        triangle.is(triangleCopy)

        expect:
        factory.size() == 3
    }

    def flyweightOuterState() {
        given:
        def factory = new FlyweightFactory()

        expect:
        factory.create(shape).draw(new Color(color)) == draw

        where:
        shape       | color    || draw
        "circle"    | "red"    || "draw a red circle"
        "rectangle" | "yellow" || "draw a yellow rectangle"
        "triangle"  | "black"  || "draw a black triangle"
        "circle"    | "black"  || "draw a black circle"
    }
}
```