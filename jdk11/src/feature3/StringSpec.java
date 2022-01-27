package feature3;

/**
 * @author xiehai
 * @date 2022/01/27 09:41
 */
public class StringSpec {
    public static void main(String[] args) {
        String a = "\n\t\u000B\f\r\u001C\u001D\u001E\u001F";
        System.out.println(a.isBlank());

        String b = "line1\nline2\nline3\nline4";
        b.lines().forEach(System.out::println);

        String c = "abc";
        System.out.println(c.repeat(4));
        
        String d = "\n\u001C\u001Dab13\u001E\u001F";
        System.out.println(d.strip().length());
        System.out.println(d.stripLeading().length());
        System.out.println(d.stripTrailing().length());
    }
}
