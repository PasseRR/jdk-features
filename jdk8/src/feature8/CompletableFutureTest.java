package feature8;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * {@link java.util.concurrent.CompletableFuture}
 * @author xiehai
 * @date 2021/12/03 10:04
 */
public class CompletableFutureTest {
    // #region run
    public void run() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() ->
                System.out.printf("%s says, hello world \n", Thread.currentThread().getName())
            )
            .thenRunAsync(() -> System.out.printf("%s says, hello world again\n", Thread.currentThread().getName()));

        future.join();
    }
    // #endregion run

    // #region supply
    public void supply() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
            .thenApplyAsync(s -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ignore) {
                }
                System.out.printf("%s says, %s\n", Thread.currentThread().getName(), s);
                return s + " World";
            })
            .thenApplyAsync(s -> {
                System.out.printf("%s says, %s\n", Thread.currentThread().getName(), s);
                return s + "!";
            })
            .whenComplete((s, e) -> {
                if (Objects.nonNull(e)) {
                    System.out.println(e.getMessage());
                }

                System.out.printf("%s says, %s\n", Thread.currentThread().getName(), s);
            });

        System.out.println(future.join());
    }
    // #endregion supply

    // #region allOf
    public void allOf() {
        // 异步执行所有任务
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() ->
                    System.out.printf("%s says, hello world \n", Thread.currentThread().getName())
                ),
                CompletableFuture.runAsync(() ->
                    System.out.printf("%s says, hello world again\n", Thread.currentThread().getName())
                ),
                CompletableFuture.runAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException ignore) {
                    }
                    System.out.printf("%s says, hello world \n", Thread.currentThread().getName());
                })
            )
            // 当所有任务完成时
            .join();
    }
    // #endregion allOf

    // #region combine
    public void combine() {
        // 异步执行带返回值的任务
        Integer join = CompletableFuture.completedFuture(1)
            .thenCombineAsync(CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException ignore) {
                }
                System.out.printf("calc 2 is thread %s\n", Thread.currentThread().getName());
                return 2;
            }), Integer::sum)
            .thenCombineAsync(CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ignore) {
                }
                System.out.printf("calc 3 is thread %s\n", Thread.currentThread().getName());
                return 3;
            }), Integer::sum)
            .join();
        System.out.println(join);
    }
    // #endregion combine

    public static void main(String[] args) {
        CompletableFutureTest spec = new CompletableFutureTest();
//        spec.run();
        spec.supply();
//        spec.allOf();
//        spec.combine();
    }
}
