#include <stdio.h>
#include "hello-world.h"

void hello_world(int fd)
{
    dprintf(fd, "Hello World!\r\n");
}
