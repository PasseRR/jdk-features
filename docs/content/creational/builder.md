---
layout: page
title: 构造器模式
permalink: builder.html
---

> 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。  
> 遇到多个构造器参数时要考虑用构建器。  
> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;———— 自Effective Java书中第二条

### JDK中的使用
- java.lang.StringBuilder#append(boolean)
- java.nio.ByteBuffer#put(byte)

### 代码实现
使用[Lombok](http://www.projectlombok.org/)中的@Builder可以快速实现构造器  
```java
@Data
public class StudentVo implements Serializable {
    private String no;
    private String name;
    private String sex;
    private Integer age;

    public static StudentBuilder builder(){
        return new StudentBuilder();
    }

    public static class StudentBuilder {
        private String no;
        private String name;
        private String sex;
        private Integer age;

        public StudentBuilder no(String no){
            this.no = no;
            return this;
        }

        public StudentBuilder name(String name){
            this.name = name;
            return this;
        }

        public StudentBuilder sex(String sex){
            this.sex = sex;
            return this;
        }

        public StudentBuilder age(Integer age){
            this.age = age;
            return this;
        }

        public StudentVo build(){
            StudentVo studentVo = new StudentVo();
            studentVo.setName(this.name);
            studentVo.setSex(this.sex);
            studentVo.setAge(this.age);
            studentVo.setNo(this.no);

            return studentVo;
        }
    }
}
```

### 单元测试
```groovy
class StudentVoSpec extends Specification {
    def build() {
        when:
        def student = StudentVo.builder()
            .name("Jack")
            .build()
        then:
        student != null
        student.name == "Jack"
        student.sex == null
        student.age == null
        student.no == null

        when:
        student = StudentVo.builder()
            .name("zhangsan")
            .age(10)
            .sex("male")
            .build()
        then:
        student != null
        student.name == "zhangsan"
        student.age == 10
        student.sex == "male"
        student.no == null
    }
}
```