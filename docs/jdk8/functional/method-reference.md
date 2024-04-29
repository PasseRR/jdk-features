# 方法引用

在Java中，方法引用是一种简洁的语法形式，用于直接引用现有方法作为Lambda表达式的实现。
方法引用可以替代Lambda表达式，使代码更加简洁清晰。

## 语法

```java
// Class实例
ClassName::methodName
// 对象实例
objectName::methodName
```

## 示例

<<< @/../jdk8/src/feature6/MethodReferenceTest.java#snippet
