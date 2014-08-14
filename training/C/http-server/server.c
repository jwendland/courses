#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <string.h>
#include "bailout.h"
#include "hello-world.h"
#include "server.h"

int create_server(const char *port, void (*callback)(int))
{
    int sockfd, cfd;
    struct addrinfo hints, *res;
    struct sockaddr caddr;
    socklen_t caddr_len = sizeof(caddr);

    memset(&hints, 0, sizeof(hints));
    hints.ai_family = PF_UNSPEC;
    hints.ai_socktype = SOCK_STREAM;
    hints.ai_flags = AI_PASSIVE;
    if (0 != getaddrinfo(NULL, port, &hints, &res)) {
        bailout_crit("getaddrinfo");
    }
    printf("got an address\n");
    res = res->ai_next;
    if (NULL == res) {
        bailout_crit("ai_next is NULL");
    }
    sockfd = socket(res->ai_family, res->ai_socktype, res->ai_protocol);    
    if (-1 == sockfd) {
        bailout_crit("could not create socket");
    }
    printf("socket created\n");

    if (-1 == bind(sockfd, res->ai_addr, res->ai_addrlen)) {
        bailout_crit("could not bind");
    }
    printf("bound to socket\n");

    if (-1 == listen(sockfd, 0)) {
        bailout_crit("could not listen");
    }
    printf("listening\n");

    while (0 < (cfd = accept(sockfd, &caddr, &caddr_len))) {
        printf("got a connection!\n");
        callback(cfd);
        close(cfd);
    }
    if (-1 == cfd) {
        bailout_crit("accept");
    }
    
    return 0;
}
