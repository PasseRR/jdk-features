package feature1;

/**
 * @author xiehai
 * @date 2022/10/31 16:34
 */
public class SwitchSpec {
    enum DayOfWeek {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    public static String test(DayOfWeek dayOfWeek) {
        return
            switch (dayOfWeek) {
                case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY: {
                    yield "工作日";
                }
                case SATURDAY, SUNDAY: {
                    yield "周末";
                }
            };
    }

    public static void main(String[] args) {
        System.out.println(test(DayOfWeek.MONDAY));
    }
}
