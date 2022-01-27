package feature2;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author xiehai
 * @date 2022/01/27 14:47
 */
public class NumberCompact {
    public static void main(String[] args) {
        NumberFormat nf = NumberFormat.getCompactNumberInstance();
        // 小数位设置
        nf.setMaximumFractionDigits(3);
        // 中文环境只有万、亿
        // 1.011万
        System.out.println(nf.format(10111));
        // 1亿
        System.out.println(nf.format(100000000));

        nf = NumberFormat.getCompactNumberInstance(Locale.CANADA, NumberFormat.Style.SHORT);
        // 英文环境有K、M
        // 10K
        System.out.println(nf.format(10111));
        // 100M
        System.out.println(nf.format(100000000));
    }
}
