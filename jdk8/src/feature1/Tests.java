package feature1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 重复注解
 * @author xiehai
 * @date 2021/11/18 18:09
 */
// #region snippet
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Tests {
    Test[] value();
}
// #endregion snippet
