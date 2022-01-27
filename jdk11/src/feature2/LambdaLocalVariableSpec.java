package feature2;

import java.util.Map;

/**
 * @author xiehai
 * @date 2022/01/26 18:14
 */
public class LambdaLocalVariableSpec {
    public static void main(String[] args) {
        var map = Map.of(1, "one", 2, "two", 3, "three");
        // 添加var后可以添加注解
        map.forEach((@Deprecated var key, var value) -> System.out.printf("key=%d value=%s", key, value));
    }
}
