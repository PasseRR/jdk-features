# 重复注解

用@Repeatable标记的注解可以在注解目标类型上重复添加该注解。

```java
import feature1.Test;
import feature1.Tests;

// @Test注解作用于TYPE，且带有@Repeatable注解的元素注解
// @Tests为重复注解容器
@Test("1")
@Test("2")
// 容器注解不能和元素注解一起出现
// @Tests({})
class Feature1 {
    // 获取重复注解方法
    public static void main(String[] args) {
        // @Tests
        Tests annotation = Feature1.class.getAnnotation(Tests.class);
        // @Test数组
        Test[] annotations = Feature1.class.getAnnotationsByType(Test.class);
    }
}
```
