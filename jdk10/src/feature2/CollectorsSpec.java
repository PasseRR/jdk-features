package feature2;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xiehai
 * @date 2022/01/26 10:54
 */
public class CollectorsSpec {
    public static void main(String[] args) {
        List<Integer> origin = List.of(1, 1, 2, 2, 3, 3);
        // 收集为只读list
        List<Integer> unmodifiableList = origin.stream().collect(Collectors.toUnmodifiableList());
        // 收集为只读set
        Set<Integer> unmodifiableSet = origin.stream().collect(Collectors.toUnmodifiableSet());
        // 收集为只读map
        Map<Integer, Integer> unmodifiableMap =
            // 无重复收集
            unmodifiableSet.stream().collect(Collectors.toUnmodifiableMap(it -> it, it -> it));
        unmodifiableMap =
            origin.stream()
                // 存在重复key收集
                .collect(Collectors.toUnmodifiableMap(Function.identity(), Function.identity(), (o, n) -> n));
    }
}
