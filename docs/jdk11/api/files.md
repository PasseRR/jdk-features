# Files API新增

- **static String readString(Path path)**  
读取文件为文本 默认为UTF8编码
- **static String readString(Path path, Charset cs)**  
以指定编码读取文件为文本
- **static Path writeString(Path path, CharSequence csq, OpenOption... options)**  
将给定CharSequence以UTF8编码写入文件
- **static Path writeString(Path path, CharSequence csq, Charset cs, OpenOption... options)**  
  将给定CharSequence以给定编码写入文件

```java
Path path = Paths.get(FilesSpec.class.getResource("FilesSpec.txt").toURI());
// 写文件
Files.writeString(path, "abc你好\r\n", StandardOpenOption.WRITE, StandardOpenOption.APPEND);
// 读文件
System.out.println(Files.readString(path));
// 按行读
Files.readAllLines(path).forEach(System.out::println);
```
