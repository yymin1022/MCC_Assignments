import java.util.concurrent.ArrayBlockingQueue;

public class ex1 {
    private final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        ex1 example = new ex1();
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
        int value = 0;
        for(int i = 0; i < 10; i++) {
            System.out.println("Producing " + value);
            queue.put(value++);
            Thread.sleep(1000);
        }
    }

    public void consumer() throws InterruptedException {
        for(int i = 0; i < 10; i++) {
            int value = queue.take();
            System.out.println("Consuming " + value);
            Thread.sleep(1000);
        }
    }
}
