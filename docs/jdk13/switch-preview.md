# 👀switch表达式

在[JDK12中](../jdk12/switch-preview.md)，switch表达式可以通过函数表达式方式省略break关键字，
JDK13中又对`switch`表达式进行了增强，增加了`yield`关键词用于返回值，相比`break`，语义更加明确了。

```java
enum DayOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

public static String test(DayOfWeek dayOfWeek) {
    return
        switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY: {
                yield "工作日";
            }
            case SATURDAY, SUNDAY: {
                yield "周末";
            }
        };
}
```
