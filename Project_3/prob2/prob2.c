#include <stdio.h>
#include </opt/homebrew/Cellar/libomp/18.1.6/include/omp.h>

double  step;
long    num_steps = 10000000;

int main(void)
{
    double  x;
    double  pi;
    double  sum;
    double  time_diff;
    double  time_end;
    double  time_start;

    time_start = omp_get_wtime();

    step = 1.0 / (double)num_steps;
    for (long i = 0; i < num_steps; i++)
    {
        x = (i + 0.5) * step;
        sum = sum + 4.0 / (1.0 + x * x);
    }
    pi = step * sum;

    time_end = omp_get_wtime();
    time_diff = time_end - time_start;

    printf("Execution Time : %lfs\n", time_diff);
    printf("pi=%.24lf\n", pi);

    return (0);
}