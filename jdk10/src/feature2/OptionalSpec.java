package feature2;

import java.util.Optional;
import java.util.Random;

/**
 * @author xiehai
 * @date 2022/01/26 10:27
 */
public class OptionalSpec {
    public static void main(String[] args) {
        Integer integer = Optional.of(new Random().nextInt())
            // 过滤奇数
            .filter(it -> (it & 1) == 1)
            .orElseThrow();
        
        System.out.println(integer);
    }
}
