package feature6;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// #region snippet
public class MethodReferenceTest {
    public static void main(String[] args) {
        // 构造方法引用
        Supplier<MethodReferenceTest> constructor = MethodReferenceTest::new;
        MethodReferenceTest instance = constructor.get();
        // this/super方法引用
        System.out.println(instance.toStringSupplier().get());
        instance.runnable().run();

        // 实例方法引用
        Runnable run = instance::run;
        run.run();
        Predicate<Integer> isOdd = instance::isOdd;
        println(isOdd.test(3));

        // 静态方法引用
        Runnable hello = MethodReferenceTest::hello;
        hello.run();
        Consumer<Object> print = MethodReferenceTest::println;
        print.accept("World!");

        // Class实例方法引用
        // 类型强制转换方法引用
        Function<Object, String> cast = String.class::cast;
        Object s = "a", i = 1;
        cast.apply(s);
        // Class Cast Exception
        cast.apply(i);
    }

    Supplier<String> toStringSupplier() {
        // super方法引用
        return super::toString;
    }

    Runnable runnable() {
        // this方法引用
        return this::run;
    }

    void run() {
        println("Running...");
    }

    boolean isOdd(int num) {
        return (num & 1) == 1;
    }

    static void println(Object o) {
        System.out.println(o);
    }

    static void hello() {
        println("Hello");
    }
}
// #endregion snippet