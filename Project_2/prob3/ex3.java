import java.util.concurrent.atomic.AtomicInteger;

public class ex3 {
    private final AtomicInteger value = new AtomicInteger(0);

    public static void main(String[] args) {
        ex3 example = new ex3();
        Thread producerThread = new Thread(() -> {
            try {
                example.producer();
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer Thread");

        Thread consumerThread = new Thread(() -> {
            try {
                example.consumer();
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer Thread");

        producerThread.start();
        consumerThread.start();
    }

    public void producer() throws InterruptedException {
        for(int i = 0; i < 10; i++) {
            int curValue = value.getAndAdd(1);
            int newValue = value.addAndGet(1);
            value.set(newValue + 1);
            System.out.println("Producing " + newValue + " from " + curValue);
            Thread.sleep(1000);
        }
    }

    public void consumer() throws InterruptedException {
        for(int i = 0; i < 10; i++) {
            int curValue = value.get();
            System.out.println("Consuming " + curValue);
            Thread.sleep(1000);
        }
    }
}
