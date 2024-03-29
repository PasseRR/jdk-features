# 接口默认方法和静态方法

在JDK8以前，接口方法默认是public abstract，所有实现接口的类必须重写接口方法。
在JDK8以后，接口新增默认方法，在非必须的情况，实现类可以不用重写默认方法。
接口静态方法跟类静态方法基本用法一样，只是接口静态方法只能是public。 若接口继承了多个接口，有相同名字的默认方法/静态方法，可以指定接口名调用。

```java
public interface Action {
    /**
     * 这个方法必须重写
     * @return 名字
     */
    String name();

    /**
     * 默认方法 按需重写
     * @return 说话
     */
    default String say() {
        return "default";
    }

    /**
     * 静态方法 可以通过接口名.方法名直接调用
     * @return 电话
     */
    static String call() {
        return "static";
    }
}
```