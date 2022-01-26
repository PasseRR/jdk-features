package feature2;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xiehai
 * @date 2022/01/26 10:35
 */
public class CollectionSpec {
    public static void main(String[] args) {
        List<Integer> origin = List.of(1, 2, 3);
        
        List<Integer> copy = List.copyOf(origin);
        // readonly
//        copy.add(1);
        Set<Integer> copySet = Set.copyOf(origin);
        // readonly
//        copySet.add(1);

        Map<Integer, Integer> originMap = Map.of(1, 1, 2, 2);
        Map<Integer, Integer> copyMap = Map.copyOf(originMap);
        // readonly
//        copyMap.put(1, 1);
    }
}
