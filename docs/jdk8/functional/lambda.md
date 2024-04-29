# Lambda表达式

Lambda表达式是Java8中引入的一个重要特性，它允许以一种更简洁、更直观的方式来表示可传递的代码块。
Lambda表达式可以看作是一种轻量级的匿名函数，它可以像参数一样传递给方法，或者作为方法的返回值。

## 语法

```java
// 完整Lambda表达式语法格式
(parameter1, parameter2, ...) -> {
    statements1;
    statements2;
    
    // 若存在返回值
    return value;
}

// 省略参数括号
p -> {
    statements1;
    statements2;

    return value;
}

// 省略表达式大括号和分号
p -> System.out.println("Hello World")

// 无参不能省略括号
() -> System.out.println("Hello World")
```

::: tip 提示

- 若Lambda只有一个参数，可以省略参数括号。
- 若Lambda的主体若只有一行表达式，则可以省略大括号。
- Lambda常配合[方法引用](method-reference.md)一起使用。
:::

## 示例

<<< @/../jdk8/src/feature6/LambdaTest.java#snippet
