package feature2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author xiehai
 * @date 2022/01/27 14:35
 */
public class FilesSpec {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Path a = Paths.get(FilesSpec.class.getResource("a.txt").toURI());
        Path b = Paths.get(FilesSpec.class.getResource("b.txt").toURI());
        Path c = Paths.get(FilesSpec.class.getResource("c.txt").toURI());

        // 正数表示从该索引位置开始 文件内容不一致
        System.out.println(Files.mismatch(a, b));
        // -1表示文件一致
        System.out.println(Files.mismatch(a, c));
    }
}
