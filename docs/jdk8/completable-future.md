# CompletableFuture异步编程

在JDK8之前，若要进行异步编程，只能通过JDK5中提供的`Future`和`Callable`实现。
若要想知道什么时候异步任务执行完成只能去轮询future.isDone()或者future.get()阻塞等待，
而且Future存在局限性，它很难描述多个异步任务之间的依赖关系。

在JDK8中引入了`CompletableFuture`，它本身实现了`Future`接口，还结合了`ExecutorService`
及`CompletionStage`可以完成一些Future不能做到的事情。

## CompletionStage接口

`CompletionStage`分为有返回值(CompletionStage&lt;T>)与无返回值(CompletionStage&lt;Void>)两种，类似Runnable和Callable。

接口方法中频繁出现了一些动词`run`、`accept`、`apply`、`handle`、`combine`、
`compose`，形容词`async`、`either`、`both`，副词`then`、`when`，主要方法名基本由这些词构成。
通过这些方法实现了任务的`串行`、`并行`、`或聚合`、`与聚合`。

方法参数大量使用函数式接口`Supplier`、 `Consumer`、`BiConsumer`、`Function`、`BiFunction`。

| 词语      | 描述                                                          |
|:--------|:------------------------------------------------------------|
| run     | 执行一个动作                                                      |
| accept  | 对返回值执行一个动作                                                  |
| apply   | 对任务返回值执行一次转换                                                |
| handle  | 对任务返回值或异常进行转换，使当前任务结果为转换后的值                                 |
| combine | 将两个任务的返回值进行结合，使当前任务返回值为结合后的值                                |
| compose | 将当前任务返回值组合成一个新任务返回                                          |
| async   | 异步执行，一般带有async方法会有一个重载方法，多一个自定义线程池参数，默认使用ForkJoin common线程池 |
| either  | 多个任务，任意一个任务完成时继续执行                                          |
| both    | 两个任务完成时继续执行                                                 |
| then    | 当前任务完成后继续执行其他任务                                             |
| when    | 当任务完成时，对返回值或异常执行一个动作                                        |

## CompletableFuture API

1. 创建CompletableFuture的方式

   |方法|描述
   |:---|:---|
   |constructor|使用构造方法直接new|
   |`static`runAsync|无返回值任务|
   |`static`supplyAsync|有返回值任务|
   |`static`allOf|无返回值任务，当所有任务完成时返回|
   |`static`anyOf|有返回值任务，当惹你任务完成时返回|
   |`static`completedFuture|有返回值任务，以给定值为任务返回值|

2. 除CompletionStage和Future之外的方法

   |方法|描述|
   |:---|:---|
   |complete|若任务未完成，以给定值完成任务|
   |completeExceptionally|若任务未完成，以给定异常完成任务|
   |getNow|若任务未完成以给定值立即返回，否则返回任务值|
   |join|类似`Future`的get方法，但是若有异常抛出，建议获取阻塞值使用这个方法|
   |obtrudeValue|强制以给定值完成任务|
   |obtrudeException|强制以给定异常完成任务|
   |getNumberOfDependents|返回当前任务待完成任务的数量，设计用于系统监控|

## CompletableFuture示例

### 异步执行无返回值

<<< @/../jdk8/src/feature8/CompletableFutureTest.java#run

### 异步执行有返回值

<<< @/../jdk8/src/feature8/CompletableFutureTest.java#supply

### 多个任务异步执行

<<< @/../jdk8/src/feature8/CompletableFutureTest.java#allOf

### 多个任务组合异步执行

<<< @/../jdk8/src/feature8/CompletableFutureTest.java#combine
