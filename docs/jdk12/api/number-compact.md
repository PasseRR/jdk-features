# 简化数字格式API

简化的数字格式可以直接转换数字显示格式，比如 1000 -> 1K，1000000 -> 1M

```java
NumberFormat nf = NumberFormat.getCompactNumberInstance();
// 小数位设置
nf.setMaximumFractionDigits(3);
// 中文环境只有万、亿
// 1.011万
System.out.println(nf.format(10111));
// 1亿
System.out.println(nf.format(100000000));

nf = NumberFormat.getCompactNumberInstance(Locale.CANADA, NumberFormat.Style.SHORT);
// 英文环境有K、M
// 10K
System.out.println(nf.format(10111));
// 100M
System.out.println(nf.format(100000000));
```