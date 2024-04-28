# Supplier

与Consumer相反，Supplier只提供返回值，无参数。

```java
@FunctionalInterface
public interface Supplier<T> {

    /**
     * Gets a result.
     * 获得返回值
     * @return a result
     */
    T get();
}
```

## JDK中的使用场景

- java.util.stream.Collectors
    - [#groupingBy](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Collectors.java#L833-L912)
    - [#toMap](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Collectors.java#L1297-L1306)
    - [#toConcurrentMap](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Collectors.java#L1449-L1458)
- [java.util.concurrent.CompletableFuture#supplyAsync](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/concurrent/CompletableFuture.java#L2126-L2151)
- java.util.Optional
    - [#orElseGet](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/Optional.java#L266-L268)
    - [#orElseThrow](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/Optional.java#L286-L292)
- java.util.stream.Stream
    - [#collect](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Stream.java#L747)
    - [#generate](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Stream.java#L1046-L1050)

## 扩展接口

- [BooleanSupplier](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/BooleanSupplier.java)：返回值为`boolean`的Supplier
- [IntSupplier](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/IntSupplier.java)：返回值为`int`的Supplier
- [LongSupplier](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/LongSupplier.java)：返回值为`long`的Supplier
- [DoubleSupplier](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/DoubleSupplier.java)：返回值为`double`的Supplier
