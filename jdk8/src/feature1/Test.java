package feature1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 测试注解
 * @author xiehai
 * @date 2021/11/18 18:09
 */
// #region snippet
@Repeatable(Tests.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    String value();
}
// #endregion snippet
