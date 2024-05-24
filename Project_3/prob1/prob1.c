#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define NUM_END 200000

int     isPrime(int x);
int     parse_schedule_type(char *str);
int     parse_thread_cnt(char *str);
void    error_exit(char *msg);

int count = 0;

int main(int argc, char **argv)
{
    int schedule_type;
    int thread_cnt;

    if (argc != 3)
        error_exit("Invalid Argument! Usage: ./prob1 scheduling_type# #_of_thread");
    schedule_type = parse_schedule_type(argv[1]);
    thread_cnt = parse_thread_cnt(argv[2]);
    if (schedule_type == -1 || thread_cnt == -1)
        error_exit("Invalid Argument Value!");
    printf("Hello, World!\n");

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