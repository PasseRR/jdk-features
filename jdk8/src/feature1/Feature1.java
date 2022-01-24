package feature1;

import java.util.Objects;
import java.util.stream.IntStream;

/**
 * 可重复注解
 * @author xiehai
 * @date 2021/11/18 18:11
 */
@Test("test1")
@Test("test2")
public class Feature1 {
    public static void main(String[] args) {
        Test[] tests = Feature1.class.getAnnotationsByType(Test.class);
        assert tests.length == 2;

        Tests annotation = Feature1.class.getAnnotation(Tests.class);
        assert annotation != null && annotation.value().length == 2;

        IntStream.range(0, 2)
            .forEach(it -> {
                assert tests[it].equals(annotation.value()[it]);
            });

        Test test = Feature1.class.getAnnotation(Test.class);
        assert Objects.isNull(test);
    }
}
