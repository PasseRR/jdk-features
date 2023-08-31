# Deprecated注解

在JDK9以前，`@Deprecated`注解是没有属性的。JDK9新增了两个属性。

| 属性名        | 类型      | 默认值   | 描述               |
|:-----------|:--------|:------|:-----------------|
| since      | String  | 空字符串  | 从哪个版本开始标记为过期     |
| forRemoval | boolean | false | 在将来版本是否会移除该标记API |

```java
@Deprecated(since = "9")
public class DeprecatedApi {
    @Deprecated(since = "8", forRemoval = true)
    public void deprecatedMethod() {
    }
}
```