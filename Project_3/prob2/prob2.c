#include <stdio.h>
#include </opt/homebrew/Cellar/libomp/18.1.6/include/omp.h>

#define NUM_STEPS 10000000

double  step;
int     parse_chunk_size(char *str);
int     parse_schedule_type(char *str);
int     parse_thread_cnt(char *str);
void    error_exit(char *msg);

int main(void)
{
    double  x;
    double  pi;
    double  sum;
    double  time_diff;
    double  time_end;
    double  time_start;
    int     chunk_size;
    int     schedule_type;
    int     thread_cnt;

    if (argc != 4)
        error_exit("Invalid Argument! Usage: ./prob1 scheduling_type# #_of_thread");

    chunk_size = parse_chunk_size(argv[2]);
    schedule_type = parse_schedule_type(argv[1]);
    thread_cnt = parse_thread_cnt(argv[3]);
    if (chunk_size == -1 || schedule_type == -1 || thread_cnt == -1)
        error_exit("Invalid Argument Value!");

    time_start = omp_get_wtime();

    step = 1.0 / (double)num_steps;
    for (long i = 0; i < NUM_STEPS; i++)
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

int parse_chunk_size(char *str)
{
    int res;

    res = strtol(str, NULL, 10);
    if (res < 1)
        return (-1);
    return (res);
}

int parse_schedule_type(char *str)
{
    int res;

    res = strtol(str, NULL, 10);
    if (res < 1 || res > 4)
        return (-1);
    return (res);
}

int parse_thread_cnt(char *str)
{
    int res;

    res = strtol(str, NULL, 10);
    if ((res > 1 && res % 2 == 1) || res < 1 || res > 16)
        return (-1);
    return (res);
}

void    error_exit(char *msg)
{
    dprintf(2, "Error: %s\n", msg);
    exit(1);
}