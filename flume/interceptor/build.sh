#!/bin/bash

set +x

source ./build.properties

if [ $# -ne 1 ]; then
    echo "Usage: build.sh [ clean | compile | package ]"
    exit -1
fi

PATH=${JAVA_HOME}/bin:$PATH

# clean, compile, package
mvn $1

mkdir -p plugins.d/syslog/lib
cp target/flume-interceptor-1.0.jar plugins.d/syslog/lib
