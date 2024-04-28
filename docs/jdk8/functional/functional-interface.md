# @FunctionalInterface

`@FunctionalInterface`是一个注解，用于标识一个接口是函数式接口，即该接口只包含一个抽象方法。
函数式接口在Java8中引入，允许通过Lambda表达式来创建该接口的实例，从而实现函数式编程的特性。

::: tip 提示
- 若一个接口满足只有一个抽象方法，不加`@FunctionalInterface`注解，也可以当做函数式接口使用。
- 若一个接口有多个抽象方法，添加`@FunctionalInterface`注解，会编译错误。
:::

## 示例

<<< @/../jdk8/src/feature6/FunctionalInterfaceTest.java#snippet