# CompletableFuture API改进

JDK9中对JDK8引入的`CompletableFuture`异步编程做了进一步提升。

## 新增工厂方法

| 方法              | 描述                                   |
|:----------------|:-------------------------------------|
| completedStage  | 以给定值创建一个完成的CompletionStage           |
| failedStage     | 以给定异常创建一个完成的CompletionStage          |
| failedFuture    | 以给定异常创建一个完成的CompletableFuture        |
| delayedExecutor | 创建一个静态延迟异步线程池，默认为fork join common线程池 |

```java
public void stage() {
    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 4)
        .thenCombineAsync(CompletableFuture.completedFuture(2), (a, b) -> a * b)
        .thenCombineAsync(CompletableFuture.failedFuture(new RuntimeException("haha")), Integer::sum)
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
```

## 提升对子类化的支持

| 方法                     | 描述                              |
|:-----------------------|:--------------------------------|
| newIncompleteFuture    | 构造一个待完成的任务，子类应该重写               |
| defaultExecutor        | 默认异步线程池                         |
| copy                   | 复制当前任务，且执行结果与当前任务一致，用于防御性复制备份   |
| minimalCompletionStage | 构造一个CompletionStage，执行结果与当前任务一致 |

## 支持结果timeout及完成异步

| 方法                | 描述                                |
|:------------------|:----------------------------------|
| completeAsync     | 以给定值异步完成当前任务                      |
| orTimeout         | 任务超时时间设置，若任务超时将获得TimeoutException |
| completeOnTimeout | 若任务超时，则以给定值完成任务                   |

```java
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
```

