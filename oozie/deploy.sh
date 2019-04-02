#!/bin/sh

set +x

source ../common.properties

if [ "${OOZIE_BASE}" == "" ]; then
    echo "OOZIE_BASE can't be empty!"
    exit 1
fi

if $(hadoop fs -test -d ${OOZIE_BASE}); then
    hdfs dfs -rm -R -f ${OOZIE_BASE}
fi

#### CREATE PATHS ####
if $(! hadoop fs -test -d ${OOZIE_BASE}); then
    echo Creating path ${OOZIE_BASE}/
    hdfs dfs -mkdir -p ${OOZIE_BASE}/
fi

hdfs dfs -mkdir -p ${OOZIE_BASE}/coord/
hdfs dfs -mkdir -p ${OOZIE_BASE}/wf/bin/
hdfs dfs -mkdir -p ${OOZIE_BASE}/wf/lib/
hdfs dfs -mkdir -p ${OOZIE_BASE}/wf/ql/
hdfs dfs -mkdir -p ${OOZIE_BASE}/wf/conf/

#### DEPLOY FILES ####
hdfs dfs -put -f coord/* ${OOZIE_BASE}/coord/

hdfs dfs -put -f wf/* ${OOZIE_BASE}/wf/
hdfs dfs -put -f wf/bin/*  ${OOZIE_BASE}/wf/bin/
hdfs dfs -put -f wf/lib/*  ${OOZIE_BASE}/wf/lib/
hdfs dfs -put -f wf/ql/*   ${OOZIE_BASE}/wf/ql/
hdfs dfs -put -f wf/conf/* ${OOZIE_BASE}/wf/conf/

# CREATE THE DB PASSWORD ENCRYPTED - SEE job.properties FOR HOW WE ACCESS IT
hadoop credential create mysql.password -value ${DB_PASS} -provider jceks://hdfs${PARENT_ZONE}/oozie/wf/password.jceks
