package feature6;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

// #region snippet
public class LambdaTest {
    public static void main(String[] args) {
        // 判断数字是否是奇数
        Predicate<Integer> isOdd = a -> (a & 1) == 1;
        System.out.println(isOdd.test(4));

        // 平方和是否是奇数
        BiFunction<Integer, Integer, Boolean> isQuadraticSumOdd = (a, b) -> {
            int x = a * a, y = b * b;
            return isOdd.test(x + y);
        },
            // 表达式主体改为行内
            inlineIsQuadraticSumOdd = (a, b) -> isOdd.test(a * a + b * b);
        System.out.println(isQuadraticSumOdd.apply(1, 2));
        System.out.println(inlineIsQuadraticSumOdd.apply(2, 4));

        // 单个参数消费
        Consumer<Object> println = o -> System.out.println(o),
            // 或者使用方法引用
            printlnMethodReference = System.out::println;
        println.accept("Hello");
        printlnMethodReference.accept("World");

        // 多个参数的消费者
        BiConsumer<Integer, Integer> print = (a, b) -> {
            int x = a * a, y = b * b;
            println.accept(x + y);
        },
            // 表达式主体改为行内
            inlinePrint = (a, b) -> println.accept(a * a + b * b);
        print.accept(1, 2);
        inlinePrint.accept(3, 4);
    }
}
// #endregion snippet