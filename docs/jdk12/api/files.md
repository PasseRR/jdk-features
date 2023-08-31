# Files API新增

- **static long mismatch(Path path, Path path2)**  
比较两个路径的文件内容差异，相同结果为-1，否则表示从该索引位置开始两个文件内容不同

```java
Path a = Paths.get(FilesSpec.class.getResource("a.txt").toURI());
Path b = Paths.get(FilesSpec.class.getResource("b.txt").toURI());
Path c = Paths.get(FilesSpec.class.getResource("c.txt").toURI());

// 正数表示从该索引位置开始 文件内容不一致
System.out.println(Files.mismatch(a, b));
// -1表示文件一致
System.out.println(Files.mismatch(a, c));
```
