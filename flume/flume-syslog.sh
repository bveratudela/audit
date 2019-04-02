#!/bin/sh

PARENT_ZONE=/auth
LANDING_ZONE=${PARENT_ZONE}/landing
SOURCE_ZONE=${LANDING_ZONE}/syslog
FLUME_PLUGINS=${PWD}/interceptor/plugins.d

function import() {
    echo "Starting syslog flume agent ..."
    hdfs dfs -mkdir -p  ${SOURCE_ZONE}
    hdfs dfs -chmod 777 ${SOURCE_ZONE}
    flume-ng agent -n a1 -f ./flume-syslog.conf --plugins-path ${FLUME_PLUGINS}
}

function clean() {
    echo "Removing ${SOURCE_ZONE} ..."
    hdfs dfs -rm -R -f ${SOURCE_ZONE}
}

while getopts ":ci" opt
do
    case ${opt} in
        c ) clean
          ;;
        i ) import
          ;;
        \?) echo "Usage: ${0} [-c] [-i]"
          ;;
        : ) echo "Invalid option: ${OPTARG} requires an argument" 1>&2
          ;;
    esac
done
shift $((OPTIND -1))
