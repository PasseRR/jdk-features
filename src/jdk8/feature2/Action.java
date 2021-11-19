package jdk8.feature2;

/**
 * 接口
 * @author xiehai
 * @date 2021/11/18 18:24
 */
public interface Action {
    // 名字必须重写
    String name();

    // 默认说话
    default String say() {
        return "default";
    }

    static String call() {
        return "static";
    }
}
