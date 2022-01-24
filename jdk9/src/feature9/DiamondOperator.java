package feature9;

import java.util.HashMap;
import java.util.Map;

/**
 * 内部类钻石操作符
 * @author xiehai
 * @date 2021/11/24 11:46
 */
public class DiamondOperator {
    public static void main(String[] args) {
        // JDK9以前
        Map<String, String> m = new HashMap<String, String>() {{
            this.put("a", "b");
            this.put("b", "c");
        }};
        
        // 省略了内部类的类型声明
        Map<String, String> map = new HashMap<>(4) {{
            this.put("a", "b");
            this.put("b", "c");
        }};
    }
}
