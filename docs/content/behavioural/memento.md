---
layout: page
title: 备忘录模式
permalink: memento.html
---

> 备忘录模式又叫做快照模式(Snapshot Pattern)或Token模式，是一个用来存储另外一个对象内部状态的快照的对象，备忘录模式的用意是在不破坏封装的条件下，将一个对象的状态捕捉(Capture)住，并外部化，存储起来，从而可以在将来合适的时候把这个对象还原到存储起来的状态。备忘录模式常常与命令模式和迭代子模式一同使用。  

### JDK中的使用
- java.util.Date Date对象通过自身内部的一个long值来实现备忘录模式
- 所有实现java.io.Serializable接口的类

### 代码实现
一个jar包的groupId、artifactId、version在使用的时候备份 可以随时还原到上一个版本  
```java
public interface Undoable {
    /**
     * 保存快照
     */
    void save();
    /**
     * 还原到最近一次快照
     */
    void undo();
}

@Data
@ToString(exclude = "snapshot")
public class Jar implements Undoable {
    private String groupId;
    private String artifactId;
    private String version;
    /**
     * 备份的快照
     */
    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PRIVATE)
    private Jar snapshot;

    @Override
    public void save() {
        if(null == this.snapshot){
            this.snapshot = new Jar();
        }
        this.snapshot.setGroupId(this.getGroupId());
        this.snapshot.setArtifactId(this.getArtifactId());
        this.snapshot.setVersion(this.getVersion());
    }

    @Override
    public void undo() {
        if (null != this.snapshot) {
            this.setGroupId(this.snapshot.getGroupId());
            this.setArtifactId(this.snapshot.getArtifactId());
            this.setVersion(this.snapshot.getVersion());
        }
    }
}
```

### 单元测试
```groovy
class JarSpec extends Specification {
    def jar(){
        given:
        def jar = new Jar(groupId: "com.gome", artifactId: "pattern", version: "1.0.0")
        jar.save()
        jar.setVersion("1.0.1")
        expect:
        jar != null
        jar.groupId == "com.gome"
        jar.artifactId == "pattern"
        jar.version == "1.0.1"

        when:
        jar.undo()
        then:
        jar.groupId == "com.gome"
        jar.artifactId == "pattern"
        jar.version == "1.0.0"

        when:
        jar.setGroupId("org.apache")
        jar.save()
        jar.setGroupId("com.gome")
        jar.setVersion("1.2.1")
        jar.undo()
        then:
        jar.groupId == "org.apache"
        jar.artifactId == "pattern"
        jar.version == "1.0.0"
    }
}
```