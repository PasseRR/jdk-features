# 集合API新增

- **T[] toArray(IntFunction<T[]> generator)**  
集合转为数组的重载方法 接受一个`IntFunction`参数类型

```java
List<Integer> list = List.of(1, 2, 3);
// 集合转数组
System.out.println(Arrays.toString(list.toArray(Integer[]::new)));
```