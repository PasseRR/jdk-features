package feature4;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// #region snippet
public class OptionalTest {
    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Accessors(chain = true)
    static class User {
        String firstName;
        String lastName;
        String age;
        List<String> addresses;

        public int getRequiredAge() {
            return
                Optional.ofNullable(this.age)
                    .map(Integer::parseInt)
                    // 年龄为空 抛出异常
                    .orElseThrow(() -> new RuntimeException("年龄为空了!"));
        }
    }

    public static void main(String[] args) {
        User user = new User();
        // 数字年龄解析
        // 若年龄字符串为空 默认值为0
        int age = Optional.ofNullable(user.getAge()).map(Integer::parseInt).orElse(0);
        System.out.printf("orElse年龄为:%d%n", age);

        // 若年龄字符串为空 使用一个随机年龄
        age = Optional.ofNullable(user.getAge()).map(Integer::parseInt).orElseGet(() -> new SecureRandom().nextInt());
        System.out.printf("orElseGet年龄为:%d%n", age);

        // 若年龄为空 抛出异常
        try {
            age = user.getRequiredAge();
        } catch (Exception e) {
            // 保证main正常执行 未实际抛出异常
            e.printStackTrace(System.err);
        }

        // 重新设置年龄
        user.setAge("18");
        System.out.printf("正常年龄解析:%d%n", user.getRequiredAge());

        // 是否非空及过滤
        user.setLastName("");
        boolean present =
            Optional.of(user)
                // 姓名不能为空字符串或null
                .filter(it -> Objects.nonNull(it.getFirstName()) && !it.getFirstName().trim().isEmpty())
                .filter(it -> Objects.nonNull(it.getLastName()) && !it.getLastName().trim().isEmpty())
                .isPresent();
        System.out.println(present);
        
        // 当非空是才处理
        Optional.ofNullable(user.getAge())
            .filter(it -> !it.trim().isEmpty())
            .ifPresent(it -> {
                System.out.printf("年龄为%s不是空的%n", it);
                // 做其他事情...
            });
    }
}
// #endregion snippet