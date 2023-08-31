# Collectors API新增

- **toUnmodifiableList**  
Stream收集为只读List
- **toUnmodifiableSet**  
Stream收集为只读Set
- **toUnmodifiableMap**(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper)  
Stream收集为只读Map
- **toUnmodifiableMap**(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U&gt; mergeFunction)  
Stream收集为只读Map

```java
List<Integer> origin = List.of(1, 1, 2, 2, 3, 3);
// 收集为只读list
List<Integer> unmodifiableList = origin.stream().collect(Collectors.toUnmodifiableList());
// 收集为只读set
Set<Integer> unmodifiableSet = origin.stream().collect(Collectors.toUnmodifiableSet());
// 收集为只读map
Map<Integer, Integer> unmodifiableMap =
    // 无重复收集
    unmodifiableSet.stream().collect(Collectors.toUnmodifiableMap(it -> it, it -> it));
unmodifiableMap =
    origin.stream()
        // 存在重复key收集
        .collect(Collectors.toUnmodifiableMap(Function.identity(), Function.identity(), (o, n) -> n));
```
