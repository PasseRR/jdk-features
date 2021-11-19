package jdk8.feature2;

/**
 * 接口默认方法及静态方法使用
 * @author xiehai
 * @date 2021/11/18 18:24
 */
public class Feature2 implements Action {
    @Override
    public String name() {
        return "default";
    }

    static class OverrideAction implements Action {
        @Override
        public String name() {
            return "override";
        }

        @Override
        public String say() {
            return "override";
        }
    }

    public static void main(String[] args) {
        assert "static".equals(Action.call());
        assert "default".equals(new Feature2().say());
        assert "override".equals(new OverrideAction().say());
    }
}
