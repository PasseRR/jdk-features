package feature12;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author xiehai
 * @date 2022/01/24 09:53
 */
public class ReactiveStreamSpec {
    public static void main(String[] args) throws InterruptedException {
        // 生产50个数字
        SubmissionPublisher<Integer> producer = new SubmissionPublisher<>();
        producer.subscribe(new BinaryProcessor());
        IntStream.range(0, 50).forEach(producer::submit);
        // 生产完成
        producer.close();
        TimeUnit.SECONDS.sleep(3);
    }

    // 数字转二进制字符串处理器
    static class BinaryProcessor extends SubmissionPublisher<String> implements Flow.Processor<Integer, String> {
        Flow.Subscription subscription;

        public BinaryProcessor() {
            super();
            super.subscribe(new BinarySubscriber());
            super.subscribe(new LimitedBinarySubscriber());
        }

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            this.subscription.request(1L);
        }

        @Override
        public void onNext(Integer item) {
            System.out.printf("on next in binary processor %d \n", item);
            this.subscription.request(1L);

            super.submit(Integer.toBinaryString(item));
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println(throwable.getMessage());
            throwable.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.println("binary processor complete");
            // 触发消费完成
            this.close();
        }
    }

    // 数量限制消费者
    static class LimitedBinarySubscriber extends BinarySubscriber {
        AtomicInteger counter = new AtomicInteger();

        @Override
        public void onNext(String item) {
            counter.getAndAdd(1);
            System.out.printf("on next in limited binary subscriber %s\n", item);
            if (counter.get() >= 5) {
                super.subscription.cancel();
            }
            this.subscription.request(1L);
        }

        @Override
        public void onComplete() {
            System.out.println("odd binary subscriber complete");
        }
    }

    // 二进制数字字符串消费者
    static class BinarySubscriber implements Flow.Subscriber<String> {
        Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            this.subscription.request(1L);
        }

        @Override
        public void onNext(String item) {
            System.out.printf("on next in binary subscriber %s\n", item);
            this.subscription.request(1L);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println(throwable.getMessage());
            throwable.printStackTrace();
            this.subscription.cancel();
        }

        @Override
        public void onComplete() {
            System.out.println("binary subscriber complete");
        }
    }
}
