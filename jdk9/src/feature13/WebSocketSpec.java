package feature13;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.WebSocket;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Scanner;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

/**
 * @author xiehai
 * @date 2022/01/24 11:48
 */
public class WebSocketSpec {
    public static void main(String[] args) throws InterruptedException {
        WebSocket webSocket = HttpClient.newHttpClient()
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

        webSocket.sendPing(ByteBuffer.allocate(0));
        Scanner scanner = new Scanner(System.in);
        String text;
        while (!(text = scanner.nextLine()).isEmpty()) {
            webSocket.sendText(text, true);
        }
        webSocket.sendClose(1000, "normal");

        TimeUnit.SECONDS.sleep(2);
    }
}
