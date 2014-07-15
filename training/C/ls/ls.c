#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <dirent.h>
#include <sys/stat.h>

int main(int argc, char **argv)
{
    char *dirname = NULL;
    char filename[256];
    DIR *dir;
    struct dirent *de;
    struct stat statbuf;

    if (argc <= 1) {
        printf("usage: %s <dir>\n", argv[0]);
        return 1;
    }

    dirname = argv[1];
    printf("opening %s\n", dirname);

    dir = opendir(dirname);
    if (NULL == dir) {
        printf("error %d: %s\n", errno, strerror(errno));
        return 1;
    }

    while (NULL != (de = readdir(dir))) {
        snprintf(filename, 255, "%s/%s", dirname, de->d_name);
        lstat(filename, &statbuf);
        printf("%10s %d:%d\n", de->d_name, statbuf.st_uid, statbuf.st_gid);
    }

    closedir(dir);

    return 0;
}
