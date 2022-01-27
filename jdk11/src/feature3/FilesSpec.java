package feature3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author xiehai
 * @date 2022/01/27 10:47
 */
public class FilesSpec {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Path path = Paths.get(FilesSpec.class.getResource("FilesSpec.txt").toURI());
        // 写文件
        Files.writeString(path, "abc你好\r\n", StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        // 读文件
        System.out.println(Files.readString(path));
        // 按行读
        Files.readAllLines(path).forEach(System.out::println);
    }
}
