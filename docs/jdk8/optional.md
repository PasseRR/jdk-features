# Optional工具类

Optional是一个容器对象，可以包含或不包含非空值。它用于处理可能为`null`的值，避免了空指针异常的发生，同时提高了代码的可读性和健壮性。
Optional是函数式编程的一部分，用于表示可能存在或不存在的值。

## 方法介绍

| 方法名                                                   | 描述                        |
|:------------------------------------------------------|:--------------------------|
| `static` of(T t)                                      | 创建一个非空的Optional，若t为空会NPE  |
| `static` ofNullable(T t)                              | 创建一个可为空的Optional          |
| filter(Predicate&lt;super T> predicate)               | 对源对象做过滤，不满足条件返回空的Optional |
| map(Function&lt;super T, ? extends U> mapper)         | 对非空的Optional做转换           |
| flatMap(Function&lt;super T, Optional&lt;U>> mapper)  | 将非空Optional转换为其他的Optional |
| orElse(T other)                                       | 若Optional为空，则返回给定值        |
| orElseGet(Supplier&lt;extends T> other)               | 若Optional为空，则返回提供器的值      |
| orElseThrow(Supplier&lt;extends X> exceptionSupplier) | 若Optional为空，则抛出异常提供器的异常   |

::: warning
注意`orElse`和`orElseGet`的区别，若是常量，则使用`orElse`，否则尽量都使用`orElseGet`
:::

## 扩展工具类

- [OptionalDouble](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/OptionalDouble.java)：用于处理`double`类型的非空Optional
- [OptionalInt](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/OptionalInt.java)：用于处理`int`类型的非空Optional
- [OptionalLong](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/OptionalLong.java)：用于处理`long`类型的非空Optional

## 示例

<<< @/../jdk8/src/feature4/OptionalTest.java#snippet
