# 内部类的钻石操作符

省略内部类的泛型类型声明。

```java
// JDK9以前
Map<String, String> m = new HashMap<String, String>() {{
    this.put("a", "b");
    this.put("b", "c");
}};

// 现在
// 省略了内部类的类型声明
Map<String, String> map = new HashMap<>(4) {{ 
    this.put("a", "b");
    this.put("b", "c");
}};
```