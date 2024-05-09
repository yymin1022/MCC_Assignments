import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ex4 {
    private final CyclicBarrier barrier = new CyclicBarrier(2, () -> {});
    int num = 0;

    public static void main(String[] args) {
        ex4 example = new ex4();
        Thread producerThread = new Thread(() -> {
            try {
                example.producer();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (BrokenBarrierException e) {
                System.out.println("Barrier was broken!");
            }
        }, "Producer Thread");

        Thread consumerThread = new Thread(() -> {
            try {
                example.consumer();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (BrokenBarrierException e) {
                System.out.println("Barrier was broken!");
            }
        }, "Consumer Thread");

        producerThread.start();
        consumerThread.start();
    }

    public void producer() throws InterruptedException, BrokenBarrierException {
        for(int i = 0; i < 10; i++) {
            System.out.println("Producing " + num);
            num++;
            Thread.sleep(1000);
        }
        barrier.await();
    }

    public void consumer() throws InterruptedException, BrokenBarrierException {
        for(int i = 0; i < 10; i++) {
            System.out.println("Consuming " + num);
            Thread.sleep(1000);
        }
        barrier.await();
    }
}
