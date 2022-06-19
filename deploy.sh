#!/bin/bash
cd front
/bin/bash front.sh
cd -
rm -rf web/src/main/resources/static/*
mv front/dist/* web/src/main/resources/static
cd web
/bin/bash web.sh
cd -