#!/bin/sh

source ../common.properties

rm -f ./job.properties

hdfs dfs -get ${OOZIE_BASE}/coord/job.properties .

JOBID=$(oozie job -oozie ${OOZIE_URI} -config ./job.properties -submit | awk '{print $2}')
echo $JOBID > job.id
oozie job -oozie ${OOZIE_URI} -config ./job.properties -start ${JOBID}
