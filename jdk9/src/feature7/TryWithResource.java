package feature7;

import java.io.Closeable;
import java.io.IOException;

/**
 * try-with-resources
 * @author xiehai
 * @date 2021/11/24 11:07
 */
public class TryWithResource {
    public static void main(String[] args) {
        // jdk7写法
        try (Closeable closeable = null; Closeable closeable1 = null) {

        } catch (IOException ignore) {
        }

        // jdk9写法
        Closeable closeable = null, closeable1 = null;
        // try子句可以更简洁，多个Closeable基本不需要换行
        try (closeable; closeable1) {

        } catch (IOException ignore) {
        }
    }
}
