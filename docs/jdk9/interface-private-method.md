# 接口私有方法

JDK8允许接口有默认和静态方法，JDK9增加了接口私有方法的支持。
可以提取冗余的公共方法，接口中支持常量、抽象方法、公有默认方法、公有静态方法、私有方法、私有静态方法。

```java
public interface Private {
    default String sayPublic() {
        return "from " + this.say();
    }

    static String publicSay() {
        return "from " + privateSay();
    }

    private String say() {
        return "private";
    }

    private static String privateSay() {
        return "private static";
    }
}
```