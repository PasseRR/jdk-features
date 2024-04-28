# Predicate

Predicate表示接受一个参数，并对参数做断言，返回值恒定为`boolean`类型。

```java
@FunctionalInterface
public interface Predicate<T> {

    /**
     * Evaluates this predicate on the given argument.
     * 断言
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(T t);

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     * 逻辑且
     * @param other a predicate that will be logically-ANDed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * AND of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     * 逻辑非
     * @return a predicate that represents the logical negation of this
     * predicate
     */
    default Predicate<T> negate() {
        return (t) -> !test(t);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     * 逻辑或
     * @param other a predicate that will be logically-ORed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * OR of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    /**
     * Returns a predicate that tests if two arguments are equal according
     * to {@link Objects#equals(Object, Object)}.
     * 对象是否相等的断言
     * @param <T> the type of arguments to the predicate
     * @param targetRef the object reference with which to compare for equality,
     *               which may be {@code null}
     * @return a predicate that tests if two arguments are equal according
     * to {@link Objects#equals(Object, Object)}
     */
    static <T> Predicate<T> isEqual(Object targetRef) {
        return (null == targetRef)
                ? Objects::isNull
                : object -> targetRef.equals(object);
    }
}
```

## JDK中的使用场景

- [java.util.Collection#removeIf](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/Collection.java#L409-L420)
- [java.util.stream.Collectors#partitioningBy](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Collectors.java#L1091-L1139)
- [java.util.Optional#filter](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/Optional.java#L173-L179)
- java.util.stream.Stream
    - [#filter](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Stream.java#L169)
    - [#anyMatch](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Stream.java#L871)
    - [#allMatch](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Stream.java#L894)
    - [#noneMatch](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/stream/Stream.java#L917)

## 扩展接口

- [IntPredicate](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/IntPredicate.java)：参数为`int`类型的Predicate
- [LongPredicate](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/LongPredicate.java)：参数为`long`类型的Predicate
- [DoublePredicate](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/DoublePredicate.java)：参数为`double`类型的Predicate
- [BiPredicate](https://github.com/openjdk/jdk/blob/jdk8-b120/jdk/src/share/classes/java/util/function/BiPredicate.java)：有两个参数为`T`、`U`的Predicate
