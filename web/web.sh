#!/bin/bash
mvn clean package
mv target/web-0.0.1-SNAPSHOT.jar ./web.jar
#scp web.jar huawei:~
#scp server.sh huawei:~
#scp Dockerfile huawei:~
#ssh huawei "~/server.sh"
bash server.sh