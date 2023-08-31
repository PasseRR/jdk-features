# lambda参数的局部变量语法

```java
var map = Map.of(1, "one", 2, "two", 3, "three");
// 添加var后可以添加注解
map.forEach((@Deprecated var key, var value) -> System.out.printf("key=%d value=%s", key, value));
```