package feature2;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xiehai
 * @date 2022/01/27 15:07
 */
public class CollectorsSpec {
    public static void main(String[] args) {
        List<Integer> collect = Stream.of(1, 2, 3, 4, 5)
            .collect(
                Collectors.teeing(
                    // 最小值
                    Collectors.minBy(Comparator.naturalOrder()),
                    // 最大值
                    Collectors.maxBy(Comparator.naturalOrder()),
                    (Optional<Integer> min, Optional<Integer> max) -> List.of(min.orElseThrow(), max.orElseThrow())
                )
            );

        System.out.println(collect);
    }
}
