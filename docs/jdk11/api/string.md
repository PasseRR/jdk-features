# String API新增

- **boolean isBlank()**  
字符串是否为空或者仅包含空白字符(参考Character.isWhitespace判断是否空白字符)
- **Stream lines()**  
返回从此字符串中提取的行的流，由行终止符分隔
- **String repeat(int count)**  
返回一个当前字符串重复`count`次的字符串
- **String strip()**  
去掉字符串前后空白 相对于`trim()` 去除的空白字符不仅仅是空格
- **String stripLeading()**  
去掉字符串前导空白
- **String stripTrainling()**  
去掉字符串尾部空白

```java
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
```