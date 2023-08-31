# 集合API新增

Map、Set、List新增`copyOf`静态方法，Set、List接收一个Collection<?>类型参数，Map接受一个Map<?,?&gt;类型参数。
复制的集合均为只读类型。


```java
List<Integer> origin = List.of(1, 2, 3);

List<Integer> copy = List.copyOf(origin);
// readonly
// copy.add(1);
Set<Integer> copySet = Set.copyOf(origin);
// readonly
// copySet.add(1);

Map<Integer, Integer> originMap = Map.of(1, 1, 2, 2);
Map<Integer, Integer> copyMap = Map.copyOf(originMap);
// readonly
// copyMap.put(1, 1);
```