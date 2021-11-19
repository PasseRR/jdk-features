---
layout: page
title: 原型模式
permalink: prototype.html
---

> 用原型实例指定创建对象的种类，并且通过拷贝这个原型来创建新的对象。

### JDK中的使用
- java.lang.Cloneable
- java.lang.Object#clone()

### 代码实现
```java
@Data
public class CopyType implements Serializable {
    private String type;
}
```
#### 浅拷贝  
被拷贝对象的所有变量都含有与原来的对象相同的值，而所有的对其他对象的引用仍然指向原来的对象。换言之，浅拷贝仅仅拷贝所考虑的对象，而不拷贝它所引用的对象。
```java
@Data
public class ShallowCopy implements Cloneable {
    private String name;
    private CopyType copyType;
    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
```
#### 深拷贝  
被拷贝对象的所有变量都含有与原来的对象相同的值，除去那些引用其他对象的变量。那些引用其他对象的变量将指向被拷贝过的新对象，而不再是原有的那些被引用的对象。换言之，深拷贝把要拷贝的对象所引用的对象都拷贝了一遍。
```java
@Data
public class DeepCopy implements Serializable {
    private String name;
    private CopyType copyType;
    public Object clone(){
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
```

### 单元测试
```groovy
class ShallowCopySpec extends Specification {
    def "is same by shallow copy"(){
        given:
        def source = new ShallowCopy(
            name: "Jack",
            copyType: new CopyType(type: "shallow")
        )
        def copy = (ShallowCopy) source.clone()

        expect:
        copy != null
        copy.name != null
        copy.copyType != null
        copy.name == source.name
        copy.name.is(source.name)
        copy.copyType.type == source.copyType.type
        copy.copyType == source.copyType
        copy.copyType.is(source.copyType)
        !source.is(copy)

        when:
        copy.name = "Jack Chen"
        copy.copyType.type = "just shallow"

        then:
        source.name != copy.name
        !source.name.is(copy.name)
        source.copyType.type == copy.copyType.type
        source.copyType == copy.copyType
        source.copyType.is(copy.copyType)
        !source.is(copy)
    }
}

class DeepCopySpec extends Specification {
    def "is same by deep copy"(){
        given:
        def source = new DeepCopy(
            name: "Jack",
            copyType: new CopyType(type: "deep")
        )
        def copy = (DeepCopy) source.clone()
        expect:
        copy != null
        copy.name != null
        copy.copyType != null
        copy.name == source.name
        copy.copyType.type == source.copyType.type
        !copy.copyType.is(source.copyType)
        !copy.is(source)

        when:
        copy.name = "Jack Chen"
        copy.copyType.type = "just deep"

        then:
        source.name != copy.name
        source.copyType.type != copy.copyType.type
        !source.copyType.is(copy.copyType)
        !source.is(copy)
    }
}
```