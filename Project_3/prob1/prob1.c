#include <stdio.h>
#include <stdlib.h>
#include </opt/homebrew/Cellar/libomp/18.1.6/include/omp.h>

#define NUM_END 200000

int     count = 0;
int     isPrime(int x);
int     parse_schedule_type(char *str);
int     parse_thread_cnt(char *str);
void    error_exit(char *msg);

int main(int argc, char **argv)
{
    double  time_diff;
    double  time_end;
    double  time_start;
    int     schedule_type;
    int     thread_cnt;

    if (argc != 3)
        error_exit("Invalid Argument! Usage: ./prob1 scheduling_type# #_of_thread");

    schedule_type = parse_schedule_type(argv[1]);
    thread_cnt = parse_thread_cnt(argv[2]);
    if (schedule_type == -1 || thread_cnt == -1)
        error_exit("Invalid Argument Value!");

    omp_set_num_threads(thread_cnt);
    time_start = omp_get_wtime();

    if (schedule_type == 1)
    {
#pragma omp parallel for schedule(static) reduction(+:count)
        for (int i = 1; i <= NUM_END; i++)
        {
            if (isPrime(i))
                count++;
        }
    }
    else if (schedule_type == 2)
    {
#pragma omp parallel for schedule(dynamic) reduction(+:count)
        for (int i = 1; i <= NUM_END; i++)
        {
            if (isPrime(i))
                count++;
        }
    }
    else if (schedule_type == 3)
    {
#pragma omp parallel for schedule(static, 10) reduction(+:count)
        for (int i = 1; i <= NUM_END; i++)
        {
            if (isPrime(i))
                count++;
        }
    }
    else if (schedule_type == 4)
    {
#pragma omp parallel for schedule(dynamic, 10) reduction(+:count)
        for (int i = 1; i <= NUM_END; i++)
        {
            if (isPrime(i))
                count++;
        }
    }

    time_end = omp_get_wtime();
    time_diff = time_end - time_start;

    printf("Program Execution Time: %.2fms\n", time_diff * 1000);
    printf("1...%d prime# counter=%d\n", NUM_END - 1, count);

    return (0);
}

int isPrime(int x)
{
    if (x <= 1)
        return (0);

    for (int i = 2; i * i <= x; i++)
    {
        if (x % i == 0)
            return (0);
    }
    return (1);
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