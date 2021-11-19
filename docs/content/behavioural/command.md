---
layout: page
title: 命令模式
permalink: command.html
---

> 命令模式把一个请求或者操作封装到一个对象中。命令模式允许系统使用不同的请求把客户端参数化，对请求排队或者记录请求日志，可以提供命令的撤销和恢复功能。  

### JDK中的使用
- java.util.concurrent.ExecutorService#execute(java.lang.Runnable)
- java.util.concurrent.ExecutorService#submit(java.util.concurrent.Callable)

### 代码实现
遥控器可以控制电视开、关、声音加减、频道变换  
```java
@FunctionalInterface
public interface Command<T> {
    T execute();
}
@Getter
@Setter(AccessLevel.PROTECTED)
public class Television {
    /**
     * 电视名称
     */
    private String name;
    /**
     * 当前所在频道
     */
    private Integer channel;
    /**
     * 当前音量
     */
    private Integer volume;

    public Television(){
        this("HaierTv");
    }

    public Television(String name){
        this(name, 10, 20);
    }

    public Television(String name, Integer channel, Integer volume){
        this.name = name;
        this.channel = channel;
        this.volume = volume;
    }

    /**
     * 电视机执行遥控器命令
     * @param command 具体命令
     * @param <T> 命令返回值
     * @return 命令执行结果
     */
    public <T> T execute(Command<T> command){
        return command.execute();
    }
}
public class RemoteControl {
    private Television television;
    @Setter
    private Command<String> turnOn = () -> this.television.getName() + " on!";
    @Setter
    private Command<String> turnOff = () -> this.television.getName() + " off!";
    @Setter
    private Command<Integer> volumeUp = () -> {
        int currentVolume = this.television.getVolume();
        this.television.setVolume(++currentVolume);
        return currentVolume <= 100 ? currentVolume : 100;
    };
    @Setter
    private Command<Integer> volumeDown = () -> {
        int currentVolume = this.television.getVolume();
        this.television.setVolume(--currentVolume);
        return currentVolume >= 0 ? currentVolume : 0;
    };
    @Setter
    private Command<Integer> channelUp = () -> {
        int currentChannel = this.television.getChannel();
        this.television.setChannel(++currentChannel);
        return currentChannel <= 200 ? currentChannel : 200;
    };
    @Setter
    private Command<Integer> channelDown = () -> {
        int currentChannel = this.television.getChannel();
        this.television.setChannel(--currentChannel);
        return currentChannel > 0 ? currentChannel : 1;
    };

    public RemoteControl(Television television) {
        this.television = television;
    }

    public String turnOn() {
        return this.television.execute(turnOn);
    }

    public String turnOff() {
        return this.television.execute(turnOff);
    }

    public Integer volumeUp() {
        return this.television.execute(volumeUp);
    }

    public Integer volumeDown() {
        return this.television.execute(volumeDown);
    }

    public Integer channelUp() {
        return this.television.execute(channelUp);
    }

    public Integer channelDown() {
        return this.television.execute(channelDown);
    }
}
```

### 单元测试
```groovy
class CommandSpec extends Specification {
    def control() {
        given:
        def tv = new Television("佳能电视")
        def control = new RemoteControl(tv)

        when:
        def on = control.turnOn()
        then:
        on == tv.getName() + " on!"

        when:
        def volumeUp = control.volumeUp()
        then:
        volumeUp == tv.getVolume()

        when:
        def volumeDown = control.volumeDown()
        then:
        volumeDown == tv.getVolume()

        when:
        def channelUp = control.channelUp()
        then:
        channelUp == tv.getChannel()

        when:
        def channelDown = control.channelDown()
        then:
        channelDown == tv.getChannel()

        when:
        def off = control.turnOff()
        then:
        off == tv.getName() + " off!"
    }

    def command(){
        given:
        def tv = new Television()
        def control = new RemoteControl(tv)
        def onMsg = "金光闪闪的打开!"
        def offMsg = "牛逼哄哄的关掉!"
        control.setTurnOn({
            return tv.getName() + onMsg
        })
        control.setTurnOff({
            return tv.getName() + offMsg
        })

        when:
        def on = control.turnOn()
        then:
        on == tv.getName() + onMsg

        when:
        def off = control.turnOff()
        then:
        off == tv.getName() + offMsg
    }
}
```