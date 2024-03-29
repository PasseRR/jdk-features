# 局部变量类型推断

Local Variable Type Inference（局部变量类型推断），顾名思义只能用做为局部变量。标识符`var`
不是关键字，而是一个保留类型，不支持类或接口名字为var，也不符合命名规范。

- 仅适用于局部变量(for、foreach)
- 不能使用于方法形参、构造函数形参、方法返回类型或任何其他类型的变量声明

除了在for循环中，局部变量使用`var`代码可读性较低，除非能快速看出var所代表的类型，不然还是不建议使用var。

```java
// 局部变量
var list = List.of(1, 2, 3);
// foreach
for (var i : list) {
    System.out.println(i);
}
// 传统for
var len = list.size();
for (var i = 0; i < len; i++) {
    System.out.println(list.get(i));
}
```
