---
layout: page
title: 桥接模式
permalink: bridge.html
---

> 将抽象部分与它的实现部分分离，使它们都可以独立地变化。

### JDK中的使用
- java.util.logging.Handler
- java.util.logging.Formatter
- java.util.Collections#newSetFromMap()
- jdbc

### 代码实现
邮件、微信、QQ都可以发送文字、图片、声音消息 当邮件、微信、QQ发送消息时 只有发送消息内容改变 其他不会变
```java
public abstract class Message {
    protected abstract String getMsg();
}
public class Text extends Message {
    @Override
    protected String getMsg() {
        return "text";
    }
}
public class Picture extends Message {
    @Override
    protected String getMsg() {
        return "picture";
    }
}
public class Voice extends Message {
    @Override
    protected String getMsg() {
        return "voice";
    }
}
```
消息发送  
```java
public abstract class Communicator {
    Message message;
    public abstract String send();

    public void setMessage(Message message){
        this.message = message;
    }
}
public class Email extends Communicator {
    @Override
    public String send() {
        return "email send " + super.message.getMsg();
    }
}
public class Qicq extends Communicator {
    @Override
    public String send() {
        return "qicq send " + super.message.getMsg();
    }
}
public class Wechat extends Communicator {
    @Override
    public String send() {
        return "wechat send " + super.message.getMsg();
    }
}
```

### 单元测试
以微信为例 其他两个类似  
```groovy
class WechatSpec extends Specification {
    @Unroll
    def "#result"() {
        given:
        def wechat = new Wechat()
        wechat.setMessage(message)
        expect:
        wechat.send() == result
        where:
        message       || result
        new Picture() || "wechat send picture"
        new Text()    || "wechat send text"
        new Voice()   || "wechat send voice"
    }
}
```
