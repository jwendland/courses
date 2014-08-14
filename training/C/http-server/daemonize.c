#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include "daemonize.h"
#include "bailout.h"

void daemonize()
{
    pid_t child_pid;

    child_pid = fork();

    switch(child_pid) {
        case 0:
            break;;
        case -1:
            bailout_crit("could not fork");
            break;;
        default:
            printf("forked child with PID %d\n", child_pid);
            exit(0);
    }

    chdir("/");
    if (-1 == setuid(0)) {
        bailout_warn("could not setuid(0)");
    }
}
