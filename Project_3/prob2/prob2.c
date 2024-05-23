#include </opt/homebrew/Cellar/libomp/18.1.6/include/omp.h>
#include <stdio.h>

double  step;
long    num_steps = 10000000;

int main(void)
{
    double x, pi;
    double start_time, end_time;
    double sum = 0.0;

    start_time = omp_get_wtime();
    step = 1.0 / (double)num_steps;
    for (long i = 0; i < num_steps; i++)
    {
        x = (i + 0.5) * step;
        sum = sum + 4.0 / (1.0 + x * x);
    }
    pi = step * sum;
    end_time = omp_get_wtime();
    double timeDiff = end_time - start_time;
    printf("Execution Time : %lfs\n", timeDiff);
    printf("pi=%.24lf\n",pi);

    return (0);
}