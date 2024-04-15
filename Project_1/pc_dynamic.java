public class pc_dynamic {
    private static int NUM_END = 200000;
    private static int NUM_THREADS = 1;

    public static void main(String[] args) {
        if (args.length==2) {
            NUM_THREADS = Integer.parseInt(args[0]);
            NUM_END = Integer.parseInt(args[1]);
        }

        int counter = 0;
        long startTime = System.currentTimeMillis();
        int i;
        for (i = 0; i < NUM_END; i++) {
            if (isPrime(i)) {
                counter++;
            }
        }

        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;

        System.out.println("Program Execution Time: " + timeDiff + "ms");
        System.out.println("1..." + (NUM_END - 1) + " prime# counter=" + counter);
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
            int numTo = (NUM_END / thread_cnt) * (thread_idx + 1) - 1;

            long startTime = System.currentTimeMillis();
            for (int i = numFrom; i < numTo; i++) {
                if (isPrime(i)) {
                    tmpCount++;
                }
            }

            count += tmpCount;

            long endTime = System.currentTimeMillis();
            timeDiff = endTime - startTime;
        }
    }
}