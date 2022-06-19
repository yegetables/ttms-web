#!/bin/bash
docker stop ttms
docker rm ttms 
docker rmi ttmsi
docker build -t ttmsi .
docker run -d --name ttms --restart=always -p 80:80 ttmsi
docker logs -f ttms
