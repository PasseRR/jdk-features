package feature1;

import java.util.List;

/**
 * @author xiehai
 * @date 2022/01/26 10:09
 */
public class LocalVariableSpec {
    public static void main(String[] args) {
        // 局部变量
        var list = List.of(1, 2, 3);
        // foreach
        for (var i : list) {
            System.out.println(i);
        }
        // 传统for
        var len = list.size();
        for (var i = 0; i < len; i++) {
            System.out.println(list.get(i));
        }
    }
}
