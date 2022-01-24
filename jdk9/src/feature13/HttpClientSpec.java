package feature13;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author xiehai
 * @date 2022/01/24 10:25
 */
public class HttpClientSpec {
    static void async() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.baidu.com")).GET().build();
        // 异步请求
        client.sendAsync(request, HttpResponse.BodyHandler.asString(StandardCharsets.UTF_8))
            .whenComplete((r, e) -> System.out.println(r.body()));
        System.out.println("request send complete");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ignore) {
        }
    }

    static void sync() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.baidu.com")).GET().build();
        // 同步请求
        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandler.asString(StandardCharsets.UTF_8));
        System.out.println(send.body());
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        async();

        sync();
    }
}
