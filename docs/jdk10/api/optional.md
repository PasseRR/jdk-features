# Optional API新增

- **orElseThrow**  
不同于orElseThrow(Supplier<? extends X> exceptionSupplier)，需要提供异常实例，该方法为无参，
若值非空返回值，否则抛出`NoSuchElementException`异常

Optional、OptionalDouble、OptionalInt、OptionalLong均添加了该方法。

```java
Integer integer = Optional.of(new Random().nextInt())
    // 过滤奇数
    .filter(it -> (it & 1) == 1)
    .orElseThrow();

System.out.println(integer);
```