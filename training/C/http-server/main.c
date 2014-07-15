#include "daemonize.h"
#include "server.h"
#include "hello-world.h"

int main(int argc, char **argv)
{
    daemonize();
    create_server("8888", hello_world);
    return 0;
}
