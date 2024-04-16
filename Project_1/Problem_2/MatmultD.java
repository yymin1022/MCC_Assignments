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

    public static void main(String[] args) {
        int thread_no = args.length == 1 ? Integer.parseInt(args[0]) : 1;
        int[][] matrixA = readMatrix();
        int[][] matrixB = readMatrix();

        long startTime = System.currentTimeMillis();
        int[][] matrixC = multMatrix(matrixA, matrixB);
        long endTime = System.currentTimeMillis();

        printMatrix(matrixC);

        //System.out.printf("thread_no: %d\n" , thread_no);
        //System.out.printf("Calculation Time: %d ms\n" , endTime-startTime);

        System.out.printf("[thread_no]:%2d , [Time]:%4d ms\n", thread_no, endTime-startTime);
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

    public static void printMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        System.out.printf("Matrix[%d][%d]\n", rows, cols);

        int sum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum += matrix[i][j];
                System.out.printf("%4d ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.printf("Matrix Sum = %d\n", sum);
    }

    public static int[][] multMatrix(int[][] matrixA, int[][] matrixB){
        if (matrixA.length == 0) {
            return new int[0][0];
        }

        if (matrixA[0].length != matrixB.length) {
            return null;
        }

        int rows = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;
        int[][] ans = new int[colsA][colsB];
        for (int i = 0; i < rows; i++){
            for(int j = 0; j < colsB; j++){
                for(int k = 0; k < colsA; k++){
                    ans[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return ans;
    }
}
