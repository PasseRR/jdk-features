package feature1;

/**
 * @author xiehai
 * @date 2022/01/27 13:44
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

    public static void main(String[] args) {
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        // 多个case
//        switch (dayOfWeek) {
//            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> System.out.println("weekday");
//            default -> System.out.println("weekend");
//        }
//        
//        // switch表达式返回值
//        int index = switch (dayOfWeek) {
//            case MONDAY -> 1;
//            case TUESDAY -> 4;
//            default -> dayOfWeek.ordinal();
//        };

//        System.out.println(index);
    }
}
