package feature4;

import java.time.Duration;
import java.util.Arrays;

/**
 * 进程api
 * @author xiehai
 * @date 2021/11/22 16:48
 */
public class Process {
    public static void main(String[] args) {
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
        ProcessHandle.of(1111)
            .ifPresent(it -> it.onExit().thenAcceptAsync(p -> System.out.printf("%d is terminal\n", p.pid())));
    }
}
