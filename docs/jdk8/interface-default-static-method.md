# 接口默认方法和静态方法

在JDK8以前，接口方法默认是public abstract，所有实现接口的类必须重写接口方法。
在JDK8以后，接口新增默认方法，在非必须的情况，实现类可以不用重写默认方法。
接口静态方法跟类静态方法基本用法一样，只是接口静态方法只能是public。 若接口继承了多个接口，有相同名字的默认方法/静态方法，可以指定接口名调用。

::: code-group

<<< @/../jdk8/src/feature2/DefaultAndStaticMethodTest.java#snippet

<<< @/../jdk8/src/feature2/Action.java#snippet

:::
