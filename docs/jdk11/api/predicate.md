# Predicate API新增

- **static <T&gt; Predicate<T&gt; not(Predicate<? super T> target)**  
断言转非

```java
Optional.of(" ")
    // 非空白字符串
    .filter(Predicate.not(String::isBlank))
    // 将抛出NoSuchElementException
    .orElseThrow();
```
