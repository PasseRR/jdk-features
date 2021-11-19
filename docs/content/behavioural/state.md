---
layout: page
title: 状态模式
permalink: state.html
---

> 将主对象和其状态分离，状态对象负责主对象的状态转换，使主对象类功能减轻  

### JDK中的使用
未发现  

### 代码实现
程序员在不同时间编码可能有不同状态 比如上午10点很愉快的编码 下午4点确实昏昏欲睡的编码  
```java
interface State {
    String FORENOON_STATE = "%s点正在努力编码!";
    String NOON_STATE = "%s点正在吃饭!";
    String AFTERNOON_STATE = "%s点疲劳的编码!";
    String EVENING_STATE = "%s点疲惫的加班编码!";
    String NIGHT_STATE = "%s点正在睡觉!";
    String OFF_DUTY_STATE = "%s点愉快下班!";
    String coding(Task task);
}
public class ForenoonState implements State {
    @Override
    public String coding(Task task) {
        if(task.getTime() < 9){
            task.setState(new NightState());
            return task.coding();
        }

        if(task.getTime() < 12){
            return String.format(FORENOON_STATE, task.getTime());
        }

        task.setState(new NoonState());
        return task.coding();
    }
}
public class NoonState implements State {
    @Override
    public String coding(Task task) {
        if(task.getTime() >= 12 && task.getTime() <= 13){
            return String.format(NOON_STATE, task.getTime());
        }

        task.setState(new AfternoonState());
        return task.coding();
    }
}
public class AfternoonState implements State{
    @Override
    public String coding(Task task) {
        if(task.getTime() > 13 && task.getTime() < 18){
            return String.format(AFTERNOON_STATE, task.getTime());
        }

        task.setState(new EveningState());
        return task.coding();
    }
}
public class EveningState implements State {
    @Override
    public String coding(Task task) {
        if(task.getTime() >= 18 && task.getTime() <= 22){
            return String.format(EVENING_STATE, task.getTime());
        }

        task.setState(new NightState());
        return task.coding();
    }
}
public class NightState implements State {
    @Override
    public String coding(Task task) {
        return String.format(NIGHT_STATE, task.getTime());
    }
}
```

### 单元测试
```groovy
class StateSpec extends Specification {
    def task() {
        given:
        def task = new Task(time, isDone)
        expect:
        task.coding() == state
        where:
        time | isDone || state
        1    | false  || String.format(NIGHT_STATE, 1)
        10   | false  || String.format(FORENOON_STATE, 10)
        12   | false  || String.format(NOON_STATE, 12)
        13   | true   || String.format(OFF_DUTY_STATE, 13)
        14   | false  || String.format(AFTERNOON_STATE, 14)
        19   | false  || String.format(EVENING_STATE, 19)
        20   | true   || String.format(OFF_DUTY_STATE, 20)
        23   | false  || String.format(NIGHT_STATE, 23)
        23   | true   || String.format(OFF_DUTY_STATE, 23)
    }
}
```