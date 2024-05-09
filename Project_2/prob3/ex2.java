import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ex2 {
    private final List<Integer> list = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        ex2 example = new ex2();
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
            lock.writeLock().lock();

            System.out.println("Producing " + value);
            list.add(value++);

            lock.writeLock().unlock();
            Thread.sleep(1000);
        }
    }

    public void consumer() throws InterruptedException {
        for(int i = 0; i < 10; i++) {
            lock.readLock().lock();

            if(!list.isEmpty()) {
                int value = list.remove(0);
                System.out.println("Consuming " + value);
            }

            lock.readLock().unlock();
            Thread.sleep(1000);
        }
    }
}
