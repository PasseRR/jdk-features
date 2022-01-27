package feature3;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author xiehai
 * @date 2022/01/27 10:14
 */
public class TimeUnitSpec {
    public static void main(String[] args) {
        Duration duration = Duration.ofDays(1L);
        // 日
        System.out.println(TimeUnit.DAYS.convert(duration));
        // 转小时
        System.out.println(TimeUnit.HOURS.convert(duration));
        // 转分钟
        System.out.println(TimeUnit.MINUTES.convert(duration));
        // 转秒
        System.out.println(TimeUnit.SECONDS.convert(duration));
        // 毫秒
        System.out.println(TimeUnit.MILLISECONDS.convert(duration));
        // 微秒
        System.out.println(TimeUnit.MICROSECONDS.convert(duration));
        // 纳秒
        System.out.println(TimeUnit.NANOSECONDS.convert(duration));
    }
}
