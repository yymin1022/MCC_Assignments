public class pc_static_cyclic {
    private static int NUM_END = 200000;
    private static int NUM_THREADS = 4;

    public static void main(String[] args) {
        if (args.length == 2) {
            NUM_THREADS = Integer.parseInt(args[0]);
            NUM_END = Integer.parseInt(args[1]);
        }

        System.out.println("Static Cyclic Multi-Thread with " + NUM_THREADS + " threads.");

        long startTime = System.currentTimeMillis();

        pc_static_cyclic.StaticCyclicThread[] threads = new pc_static_cyclic.StaticCyclicThread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new pc_static_cyclic.StaticCyclicThread(i, NUM_THREADS);
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].start();
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.err.println("Error in thread " + e);
            }
        }

        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;

        for (int i = 0; i < NUM_THREADS; i++) {
            System.out.printf("Thread #%d Execution Time: %dms\n", i, threads[i].timeDiff);
        }

        System.out.println("Program Execution Time: " + timeDiff + "ms");
        System.out.println("1..." + (NUM_END - 1) + " prime# counter=" + pc_static_cyclic.StaticCyclicThread.count);
    }

    private static boolean isPrime(int x) {
        if (x <= 1) {
            return false;
        }

        int i;
        for (i = 2; i < x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    static class StaticCyclicThread extends Thread {
        static int count;
        static int num;

        int thread_cnt;
        int thread_idx;
        int tmpCount;
        long timeDiff;

        public StaticCyclicThread(int idx, int cnt) {
            this.thread_cnt = cnt;
            this.thread_idx = idx;
        }

        @Override
        public void run() {
            tmpCount = 0;

            while (num < thread_idx * 10) {
                Thread.yield();
            }

            long startTime = System.currentTimeMillis();

            while (num < NUM_END) {
                if ((num / 10 * 10 - thread_idx * 10) % (thread_cnt * 10) != 0) {
                    Thread.yield();
                    continue;
                }

                if (isPrime(num)) {
                    count++;
                }

                num++;
            }

            long endTime = System.currentTimeMillis();
            timeDiff = endTime - startTime;
        }
    }
}