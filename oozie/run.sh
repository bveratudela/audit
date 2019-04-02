#!/bin/sh

set +x

source ../common.properties

if [ -e job.properties ]; then
    rm -f job.properties
fi

hdfs dfs -get ${OOZIE_BASE}/wf/job.properties
oozie job -verbose -oozie ${OOZIE_URI} -config ./job.properties -run
