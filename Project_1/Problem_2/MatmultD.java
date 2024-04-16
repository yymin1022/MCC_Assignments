import java.util.*;
import java.lang.*;

// command-line execution example) java MatmultD 6 < mat500.txt
// 6 means the number of threads to use
// < mat500.txt means the file that contains two matrices is given as standard input
//
// In eclipse, set the argument value and file input by using the menu [Run]->[Run Configurations]->{[Arguments], [Common->Input File]}.

// Original JAVA source code: http://stackoverflow.com/questions/21547462/how-to-multiply-2-dimensional-arrays-matrix-multiplication
public class MatmultD {
    private static final Scanner sc = new Scanner(System.in);

    static int NUM_THREADS;
    static int[][] MATRIX_A;
    static int[][] MATRIX_B;

    public static void main(String[] args) {
        NUM_THREADS = args.length == 1 ? Integer.parseInt(args[0]) : 1;
        MATRIX_A = readMatrix();
        MATRIX_B = readMatrix();

        long startTime = System.currentTimeMillis();

        MatmultD.MultiplyMatrixThread[] threads = new MatmultD.MultiplyMatrixThread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new MatmultD.MultiplyMatrixThread(i, NUM_THREADS);
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
        System.out.println("Result Matrix Sum: " + MultiplyMatrixThread.matrixSum);
    }

    public static int[][] readMatrix() {
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = sc.nextInt();
            }
        }

        return result;
    }

    static class MultiplyMatrixThread extends Thread {
        static int[][] matrixResult;
        static int matrixSum;
        static int cur_row;

        int thread_cnt;
        int thread_idx;
        int tmpSum;
        long timeDiff;

        public MultiplyMatrixThread(int idx, int cnt) {
            this.thread_cnt = cnt;
            this.thread_idx = idx;

            matrixResult = new int[MATRIX_A.length][MATRIX_B[0].length];
            matrixSum = 0;
        }

        @Override
        public void run() {
            tmpSum = 0;

            int numFrom = (MATRIX_A.length / thread_cnt) * thread_idx;
            int numTo = (thread_idx + 1 != thread_cnt ? (MATRIX_A.length / thread_cnt) * (thread_idx + 1) : MATRIX_A.length);

            long startTime = System.currentTimeMillis();

            while (cur_row < numFrom) {
                Thread.yield();
            }

            while (cur_row < numTo) {
                for(int i = 0; i < MATRIX_B[0].length; i++){
                    for(int j = 0; j < MATRIX_A[0].length; j++){
                        matrixResult[cur_row][i] += MATRIX_A[cur_row][j] * MATRIX_B[j][i];
                        tmpSum += MATRIX_A[cur_row][j] * MATRIX_B[j][i];
                    }
                }

                cur_row++;
            }

            matrixSum += tmpSum;

            long endTime = System.currentTimeMillis();
            timeDiff = endTime - startTime;
        }
    }
}
