# Function

Function表示接收一个参数`T`并返回结果`R`的函数。

```java
@FunctionalInterface
public interface Function<T, R> {

    /**
     * Applies this function to the given argument.
     * 接受一个参数，处理后并返回结果
     * @param t the function argument
     * @return the function result
     */
    R apply(T t);

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     * 组合两个函数，给定函数返回值为当前函数的参数，并返回结果
     * @param <V> the type of input to the {@code before} function, and to the
     *           composed function
     * @param before the function to apply before this function is applied
     * @return a composed function that first applies the {@code before}
     * function and then applies this function
     * @throws NullPointerException if before is null
     *
     * @see #andThen(Function)
     */
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     * 组合两个函数，当前函数的返回值作为给定函数的参数，并返回结果
     * @param <V> the type of output of the {@code after} function, and of the
     *           composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     *
     * @see #compose(Function)
     */
    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * Returns a function that always returns its input argument.
     * 返回值恒定为给定参数的函数
     * @param <T> the type of the input and output objects to the function
     * @return a function that always returns its input argument
     */
    static <T> Function<T, T> identity() {
        return t -> t;
    }
}
```

### JDK中的使用场景

- java.util.Comparator
    - [#thenComparing](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/Comparator.java#L213-L243)
    - [#comparing](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/Comparator.java#L429-L470)
- java.util.concurrent.CompletableFuture
    - [#thenApply](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/concurrent/CompletableFuture.java#L2367-L2370)
    - [#thenApplyAsync](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/concurrent/CompletableFuture.java#L2372-L2382)
    - [#applyToEither](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/concurrent/CompletableFuture.java#L2482-L2486)
    - [#applyToEitherAsync](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/concurrent/CompletableFuture.java#L2488-L2501)
    - [#thenCompose](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/concurrent/CompletableFuture.java#L2544-L2547)
    - [#thenComposeAsync](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/concurrent/CompletableFuture.java#L2549-L2559)
- java.util.stream.Collectors
    - [#mapping](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Collectors.java#L351-L358)
    - [#collectingAndThen](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Collectors.java#L378-L395)
    - [#reducing](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Collectors.java#L639-L647)
    - [#groupingBy](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Collectors.java#L786-L912)
    - [#partitioningBy](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Collectors.java#L1091-L1139)
    - [#toMap](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Collectors.java#L1192-L1306)
    - [#toConcurrentMap](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Collectors.java#L1355-L1458)
- java.util.Map
    - [#computeIfAbsent](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/Map.java#L951-L1028)
    - [#computeIfPresent](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/Map.java#L1012-L1028)
    - [#compute](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/Map.java#L1088-L1109)
    - [#merge](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/Map.java#L1169-L1191)
- java.util.stream.Stream
    - [#map](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Stream.java#L184)
    - [#flatMap](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Stream.java#L269)
- java.util.Optional
    - [#map](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/Optional.java#L210-L217)
    - [#flatMap](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/Optional.java#L236-L243)

### 扩展接口

- 基础接口
  - [UnaryOperator](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/UnaryOperator.java)：出入参都是`T`的Function
  - [BinaryOperator](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/BinaryOperator.java)：出入参都是`double`的BiFunction
  - [BiFunction](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/BiFunction.java)：`T`、`U`转`R`的Function
- `R`为`int`
  - [ToIntFunction](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/ToIntFunction.java)：`T`转`int`的Function
  - [IntUnaryOperator](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/IntUnaryOperator.java)：出入参都是`int`的Function
  - [IntBinaryOperator](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/IntBinaryOperator.java)：出入参都是`int`的BiFunction
  - [IntFunction](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/IntFunction.java)：`int`转`R`的Function
  - [ToIntBiFunction](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/ToIntBiFunction.java)：`T`、`U`转`int`的BiFunction
  - [IntToDoubleFunction](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/IntToDoubleFunction.java)：`int`转`double`的Function
  - [IntToLongFunction](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/IntToLongFunction.java)：`int`转`long`的Function
- `R`为`long`
  - [ToLongFunction](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/ToLongFunction.java)：`T`转`long`的Function
  - [LongUnaryOperator](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/LongUnaryOperator.java)：出入参都是`long`的Function
  - [LongBinaryOperator](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/LongBinaryOperator.java)：出入参都是`long`的BiFunction
  - [LongFunction](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/LongFunction.java)：`long`转`R`的Function
  - [ToLongBiFunction](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/ToLongBiFunction.java)：`T`、`U`转`long`的BiFunction
  - [LongToIntFunction](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/LongToIntFunction.java)：`long`转`int`的Function
  - [LongToDoubleFunction](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/LongToDoubleFunction.java)：`long`转`double`的Function
- `R`为`double`
  - [ToDoubleFunction](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/ToDoubleFunction.java)：`T`转`double`的Function
  - [DoubleUnaryOperator](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/DoubleUnaryOperator.java)：出入参都是`double`的Function
  - [DoubleBinaryOperator](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/DoubleBinaryOperator.java)：出入参都是`double`的BiFunction
  - [DoubleFunction](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/DoubleFunction.java)：`double`转`R`的Function
  - [ToDoubleBiFunction](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/ToDoubleBiFunction.java)：`T`、`U`转`double`的BiFunction
  - [DoubleToIntFunction](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/DoubleToIntFunction.java)：`double`转`int`的Function
  - [DoubleToLongFunction](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/DoubleToLongFunction.java)：`double`转`long`的Function
