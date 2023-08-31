# try-with-resources改进

从JDK7开始，新增try-with-resources特性，所有实现`java.io.Closeable`都可以放在try子句中，
编译器会自动添加上finally调用close方法释放资源。
JDK9中改进了这一特性，使try子句中的表达式更简洁了。

```java
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
```