# 进程API

## ProcessHandle

| 方法名                       | 描述                    |
|:--------------------------|:----------------------|
| `static`allProcesses      | 获取当前进程能看见的所有进程的stream |
| `static`current           | 获取当前进程                |
| `static`of                | 获取指定进程id的进程           |
| pid                       | 获取进程id                |
| parent                    | 获取父进程，若存在             |
| children                  | 获取进程的所有直接子进程          |
| descendants               | 获取进程的所有后代进程           |
| info                      | 获取进程的快照信息             |
| onExit                    | 进程退出是回调               |
| supportsNormalTermination | 是否支持普通终止进程            |
| destroy                   | 普通终止进程                |
| destroyForcibly           | 强制终止进程                |
| isAlive                   | 进程是否存活                |

```java
ProcessHandle current = ProcessHandle.current();
ProcessHandle.Info info = current.info();
// 进程命令
System.out.println(info.command().orElse(""));
// 进程命令参数
System.out.println(Arrays.toString(info.arguments().orElse(new String[0])));
// 进程启动用户
System.out.println(info.user().orElse(""));
// 进程启动时间
System.out.println(info.startInstant().orElse(null));
// 仅当命令及命令参数都非空时才有返回
// 结果为命令拼接命令参数
System.out.println(info.commandLine().orElse(""));
// cpu时间片总时间
System.out.println(info.totalCpuDuration().map(Duration::getNano).orElse(-1));

// 不能用于当前进程回调
ProcessHandle.of(1111L)
    .ifPresent(it -> it.onExit().thenAcceptAsync(p -> System.out.printf("%d is terminal\n", p.pid())));
```

## Process API改进

新增`toHandle`方法获取进程的`ProcessHandle`, 实现ProcessHandle的所有成员方法通过toHandle.xxx()方法实现。