# 模块化

open, module, requires, transitive, exports, opens, to, uses, provides 和 with是受限关键字

## exports

exports表示该模块暴露的模块中的包名。如果模块不暴露出去，其他模块是无法引用这些包的。
```java
exports java.util;
```

## requires
 
requires表示该模块所要用到的模块名字。
```java
requires java.logging;
```

## transitive

依赖传递，如下，所有requires `java.sql`模块的可以同时使用`java.logging`模块
 
```java
requires transitive java.logging;
```

## static

有时候，我们在代码中使用到了某些类，那么编译的时候必须要包含这些类的jar包才能够编译通过。但是在运行的时候我们可能不会用到这些类，这样我们可以使用static来表示，该module是可选的。
```java
requires static java.compiler;
```

## exports to

只想将包export暴露给具体的某个或者某些模块，则可以使用exports to
```java
// 多个模块逗号分隔
exports jdk9.feature3 to java.base,java.compiler;
```

## opens

使用static我们可以在运行时屏蔽模块，而使用open我们可以将某些package编译时不可以，但是运行时可用。
```java
opens java.sql;
```

## provides with

提供语句指定服务接口的一个或多个服务提供程序实现类
 
```java
provides javax.naming.spi.InitialContextFactory with
 com.sun.jndi.rmi.registry.RegistryContextFactory;
```

## uses

使用语句可以指定服务接口的名字，当前模块就会发现它，使用java.util.ServiceLoader类进行加载
```java
uses java.security.Provider;
```
