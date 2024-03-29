# Optional API改进

| 方法              | 描述                            |
|:----------------|:------------------------------|
| or              | 如果当前值存在则返回当前值，否则返回or的提供值      |
| ifPresentOrElse | 根据当前值的存在与否做两个不同动作，扩展ifPresent |
| stream          | 转为当前类型的stream                 |

```java
String s1 = null, s2 = "hello", s3 = "world";
Optional.ofNullable(s1)
    // 根据存在与否做不同动作
    .ifPresentOrElse(System.out::println, () -> System.out.println(s2));

Optional.ofNullable(s1)
    // 若当前为null则取or的提供
    .or(() -> Optional.ofNullable(s2))
    .or(() -> Optional.ofNullable(s3))
    .ifPresent(System.out::println);

List<String> s = List.of("hello", "world", "!");

// 将Optional转为stream
Optional.ofNullable(s)
    .filter(it -> it.size() > 0)
    .stream()
    .flatMap(Collection::stream)
    .forEach(System.out::print);
```
