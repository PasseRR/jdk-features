package jdk8.feature2;

/**
 * 接口默认方法及静态方法使用
 * @author xiehai
 * @date 2021/11/18 18:24
 */
public class Feature2 implements Interface {
    static class OverrideInterface implements Interface {
        @Override
        public String say() {
            return "override";
        }
    }

    public static void main(String[] args) {
        assert "static".equals(Interface.call());
        assert "default".equals(new Feature2().say());
        assert "override".equals(new OverrideInterface().say());
    }
}
