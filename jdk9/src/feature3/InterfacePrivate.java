package feature3;

/**
 * 接口私有方法
 * @author xiehai
 * @date 2021/11/21 19:10
 */
public class InterfacePrivate implements Private {
    public static void main(String[] args) {
        System.out.println(new InterfacePrivate().sayPublic());
        System.out.println(Private.publicSay());
    }
}

interface Private {
    default String sayPublic() {
        return "from " + this.say();
    }

    static String publicSay() {
        return "from " + privateSay();
    }

    private String say() {
        return "private";
    }

    private static String privateSay() {
        return "private static";
    }
}
