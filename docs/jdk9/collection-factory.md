# 集合工厂

针对List、Set、Map集合新增了of工厂方法，创建的集合均是不可变的，
任何修改操作都会参数UOE(UnsupportedOperationException)。

## List

不允许有null元素

```java
// 不能有null元素 List<String> nullError = List.of(null);
// 空为java.util.ImmutableCollections#EMPTY_LIST
List<String> empty = List.of();
// 创建1-2个元素的list实现类为java.util.ImmutableCollections.List12
List<String> list = List.of("s1","s2");
// 3个元素及以上实现类为java.util.ImmutableCollections.ListN
List<String> more = List.of("s1","s2","s3");
```

## Set

不允许有重复或null元素

```java
// 不允许重复或者null元素
// Set<String> objects = Set.of(null);
// Set<String> set = Set.of("s1", "s1");
// 空为java.util.ImmutableCollections#EMPTY_SET
Set<String> s0 = Set.of();
// 创建1-2个元素的set实现类为java.util.ImmutableCollections.Set12
Set<String> s1 = Set.of("s1","s2");
// 3个元素及以上实现类java.util.ImmutableCollections.SetN
Set<String> sn = Set.of("s1","s2","s3");
```

## Map

不允许null的key或value，且不允许重复key

```java
// NPE
// Map.of("s", null);
// 重复
// Map.of("s", 1, "s", 2);
// 一个key/value对实现类为java.util.ImmutableCollections.Map1
Map<String, String> map1 = Map.of("key","value");
// 2个key/value对及以上实现类为java.util.ImmutableCollections.MapN
Map<String, Integer> mapN = Map.of("k1",1,"k2",2);
```