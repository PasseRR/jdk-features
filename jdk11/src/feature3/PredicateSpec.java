package feature3;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author xiehai
 * @date 2022/01/27 10:36
 */
public class PredicateSpec {
    public static void main(String[] args) {
        Optional.of(" ")
            // 非空白字符串
            .filter(Predicate.not(String::isBlank))
            // 将抛出NoSuchElementException
            .orElseThrow();
    }
}
