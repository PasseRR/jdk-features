# Collectors API新增

- **static <T, R1, R2, R>
  Collector<T, ?, R> teeing(Collector<? super T, ?, R1> downstream1,
  Collector<? super T, ?, R2> downstream2,
  BiFunction<? super R1, ? super R2, R> merger)**  
它的作用是 merge 两个 collector 的结果

![teeing](/assets/12/2.3.jpg)

```java
public static void main(String[] args) {
    List<Integer> collect = Stream.of(1, 2, 3, 4, 5)
        .collect(
            Collectors.teeing(
                // 最小值
                Collectors.minBy(Comparator.naturalOrder()),
                // 最大值
                Collectors.maxBy(Comparator.naturalOrder()),
                (Optional<Integer> min, Optional<Integer> max) -> List.of(min.orElseThrow(), max.orElseThrow())
            )
        );

    System.out.println(collect);
}
```