package feature5;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author xiehai
 * @date 2021/11/22 19:07
 */
public class StreamImprove {
    public static void main(String[] args) {
        // 输出1、2
        System.out.println("takeWhile");
        IntStream.of(1, 2, 0)
            .takeWhile(it -> it > 0)
            .forEach(System.out::println);
        
        // 输出1、3、4
        System.out.println("dropWhile");
        IntStream.of(3, 2, 1, 3, 4)
            .dropWhile(it -> it > 1)
            .forEach(System.out::println);

        // 迭代 输出1-9
        System.out.println("iterate");
        IntStream.iterate(1, it -> it < 10, it -> it + 1)
            .forEach(System.out::println);

        // 可以为null的单元素Stream
        System.out.println("ofNullable");
        System.out.println(Stream.ofNullable(null).count());
    }
}
