#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <stdio.h>
#include "bailout.h"

void bailout_warn(const char *msg)
{
    int _errno = errno;
    fprintf(stderr, "%s: %d %s\n", msg, _errno, strerror(_errno));
}

void bailout_crit(const char *msg)
{
    bailout_warn(msg);
    exit(1);
}
