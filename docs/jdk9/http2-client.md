# 🐣Http/2.0客户端

## 概述
在JDK9之前，http都是通过`HttpURLConnection`实现，它只支持HTTP/1.1，阻塞模式，代码很难维护。

JDK9中提供了新的http客户端孵化器，并且提供`WebSocket`和`HTTP/2.0`支持及流和服务器推送api。
其中大量使用了`Reactive Stream`特性，使用`CompletableFuture`实现异步请求。

HTTP/2.0和1.1区别

- HTTP/2采用二进制格式而非文本格式
- 完全的多路复用
- 使用头压缩，降低了开销
- 服务器可以主动推送（push）数据到客户端

## http示例

```java
static void async() {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.baidu.com")).GET().build();
    // 异步请求
    client.sendAsync(request, HttpResponse.BodyHandler.asString(StandardCharsets.UTF_8))
        .whenComplete((r, e) -> System.out.println(r.body()));
    System.out.println("request send complete");
}

static void sync() throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.baidu.com")).GET().build();
    // 同步请求
    HttpResponse<String> send = client.send(request, HttpResponse.BodyHandler.asString(StandardCharsets.UTF_8));
    System.out.println(send.body());
}
```

## websocket示例

```java
public static void main(String[] args) throws InterruptedException {
    WebSocket join = HttpClient.newHttpClient()
        .newWebSocketBuilder(
            URI.create("wss://demo.piesocket.com/v3/channel_1?api_key=oCdCMcMPQpbvNjUIzqtvF1d2X2okWpDQj4AwARJuAgtjhzKxVEjQU6IdCjwm&notify_self"),
            new WebSocket.Listener() {
                @Override
                public void onOpen(WebSocket webSocket) {
                    System.out.println("open ws");
                    WebSocket.Listener.super.onOpen(webSocket);
                }

                @Override
                public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
                    System.out.printf("close ws with code = %d, reason = %s\n", statusCode, reason);
                    return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
                }

                @Override
                public CompletionStage<?> onText(WebSocket webSocket, CharSequence message,
                                                 WebSocket.MessagePart part) {
                    System.out.println("receive " + message);
                    return WebSocket.Listener.super.onText(webSocket, message, part);
                }

                @Override
                public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
                    System.out.println("receive ping");
                    return WebSocket.Listener.super.onPing(webSocket, message);
                }

                @Override
                public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
                    System.out.println("receive pong");
                    return WebSocket.Listener.super.onPong(webSocket, message);
                }
            }
        )
        .buildAsync()
        .join();

    join.sendPing(ByteBuffer.allocate(0));
    Scanner scanner = new Scanner(System.in);
    String text;
    while (!(text = scanner.nextLine()).isEmpty()) {
        join.sendText(text, true);
    }
    join.sendClose(1000, "normal");

    TimeUnit.SECONDS.sleep(2);
}
```