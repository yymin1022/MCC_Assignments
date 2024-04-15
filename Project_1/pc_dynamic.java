public class pc_dynamic {
    private static int NUM_END = 200000;
    private static int NUM_THREADS = 4;

    public static void main(String[] args) {
        if (args.length == 2) {
            NUM_THREADS = Integer.parseInt(args[0]);
            NUM_END = Integer.parseInt(args[1]);
        }

        long startTime = System.currentTimeMillis();

        DynamicThread[] threads = new DynamicThread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new DynamicThread(i, NUM_THREADS);
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
        System.out.println("1..." + (NUM_END - 1) + " prime# counter=" + DynamicThread.count);
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

    static class DynamicThread extends Thread {
        static int count;
        static int num;

        int thread_cnt;
        int thread_idx;
        int tmpCount;
        long timeDiff;

        public DynamicThread(int idx, int cnt) {
            this.thread_cnt = cnt;
            this.thread_idx = idx;
        }

        @Override
        public void run() {
            tmpCount = 0;

            int numFrom = (NUM_END / thread_cnt) * thread_idx;
            int numTo = (thread_idx + 1 != thread_cnt ? (NUM_END / thread_cnt) * (thread_idx + 1) : NUM_END);

            long startTime = System.currentTimeMillis();

            int currentNum = numFrom;
            while (num < numTo) {
                if (isPrime(currentNum)) {
                    tmpCount++;
                }
                currentNum = getNextNum();
            }
            count += tmpCount;

            long endTime = System.currentTimeMillis();
            timeDiff = endTime - startTime;
        }

        static synchronized int getNextNum() {
            num++;
            return num;
        }
    }
}