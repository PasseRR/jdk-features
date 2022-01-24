package feature6;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * {@link Optional}API改进
 * @author xiehai
 * @date 2021/11/22 19:33
 */
public class OptionalImprove {
    public static void main(String[] args) {
        String s1 = null, s2 = "hello", s3 = "world";
        Optional.ofNullable(s1)
            // 根据存在与否做不同动作
            .ifPresentOrElse(System.out::println, () -> System.out.println(s2));

        Optional.ofNullable(s1)
            // 若当前为null则取or的提供
            .or(() -> Optional.ofNullable(s2))
            .or(() -> Optional.ofNullable(s3))
            .ifPresent(System.out::println);

        List<String> s = List.of("hello", "world", "!");
        
        // 将Optional转为stream
        Optional.ofNullable(s)
            .filter(it -> it.size() > 0)
            .stream()
            .flatMap(Collection::stream)
            .forEach(System.out::print);
    }
}
