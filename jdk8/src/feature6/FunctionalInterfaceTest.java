package feature6;

/**
 * 函数式接口
 * @author xiehai
 * @date 2024/04/25 16:00
 */
// #region snippet
public class FunctionalInterfaceTest {
    @FunctionalInterface
    public interface Fly {
        /**
         * 我会飞
         */
        void doFly();
    }

    public interface Walk {
        /**
         * 我会走
         */
        void doWalk();
    }

//    @FunctionalInterface
//    public interface Error extends Fly, Walk {
//    }

    public static void main(String[] args) {
        Fly fly = () -> System.out.println("我会飞");
        Walk walk = () -> System.out.println("我会走");

        fly.doFly();
        walk.doWalk();
    }
}
// #endregion snippet
