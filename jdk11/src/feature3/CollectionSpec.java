package feature3;

import java.util.Arrays;
import java.util.List;

/**
 * @author xiehai
 * @date 2022/01/27 10:12
 */
public class CollectionSpec {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3);
        // 集合转数组
        System.out.println(Arrays.toString(list.toArray(Integer[]::new)));
    }
}
