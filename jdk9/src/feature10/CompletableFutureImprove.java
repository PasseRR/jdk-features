package feature10;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * {@link CompletableFuture}
 * @author xiehai
 * @date 2021/12/03 14:07
 */
public class CompletableFutureImprove {
    public void stage() {
        System.out.println(
            // stage to future
            CompletableFuture.completedStage(3).toCompletableFuture().join()
        );

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 4)
            .thenCombineAsync(CompletableFuture.completedStage(2), (a, b) -> a * b)
            .thenCombineAsync(CompletableFuture.failedStage(new RuntimeException("haha")), Integer::sum)
            .exceptionally(e -> {
                System.out.printf("%s throws %s\n", Thread.currentThread().getName(), e.getMessage());
                return 1;
            });
        System.out.println(future.join());
    }

    public void future() {
        long start = System.currentTimeMillis();
        CompletableFuture<Object> future = CompletableFuture.failedFuture(new RuntimeException("haha"))
            // 当前结果为异常 不会执行then
            .thenApplyAsync(a -> {
                System.out.println(Objects.isNull(a));
                return a;
            })
            .whenCompleteAsync((v, e) -> {
                // 会延迟1秒执行到这里
                System.out.println(System.currentTimeMillis() - start);
                System.out.println(e.getMessage());
            }, CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));

        // 结果为runtime异常抛出
        future.join();
    }

    public void timeout() {
        CompletableFuture<Integer> timeout = CompletableFuture.completedFuture(1)
            .thenApplyAsync(it -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ignore) {
                }

                return it;
            })
            .orTimeout(1, TimeUnit.SECONDS)
            // 将获得timeout异常
            .exceptionally(e -> {
                assert e instanceof TimeoutException;

                return -1;
            });
        System.out.println(timeout.join());

        CompletableFuture<Integer> future = CompletableFuture.completedFuture(1)
            .thenApplyAsync(it -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ignore) {
                }

                return it;
            })
            // 1秒后若任务没完成 则返回2
            .completeOnTimeout(2, 1, TimeUnit.SECONDS);
        System.out.println(future.join());
    }

    public void complete() {
        CompletableFuture<Integer> future = new CompletableFuture<Integer>()
            // 异步完成当前任务
            .completeAsync(() -> {
                System.out.printf("completed by %s\n", Thread.currentThread().getName());
                return 1;
            });
        System.out.println(future.join());
    }


    public static void main(String[] args) {
        CompletableFutureImprove improve = new CompletableFutureImprove();
//        improve.stage();
//        improve.future();
//        improve.timeout();
        improve.complete();
    }
}
