#!/bin/sh

source ../common.properties

function import() {
    #for i in hbase hdfs hive hue impala navms sentry solr
    for i in hdfs hive hue impala navms
    do
        TABLE_NAME=nav${i}
        LANDING_ZONE=${PARENT_ZONE}/landing/${TABLE_NAME}
        DB_TABLE=$(echo ${i} | awk '{print toupper($0)}')_AUDIT_EVENTS_${INGEST_YEAR}_${INGEST_MONTH}_${INGEST_DAY}
#        echo "Importing ${TABLE_NAME} ..."

#        DB_QUERY='SELECT IP_ADDR, USERNAME, "'${i}'", EVENT_TIME FROM SOLR_AUDIT_EVENTS_'${INGEST_YEAR}'_'${INGEST_MONTH}'_'${INGEST_DAY}' WHERE $CONDITIONS;'

#        echo "Importing ${TABLE_NAME}  ..."

#        sqoop import --connect jdbc:mysql://${DB_HOST}/nav --username ${DB_USER} --password ${DB_PASS} --query "${DB_QUERY}" --target-dir hdfs://${LANDING_ZONE}/dt=${INGEST_YEAR}${INGEST_MONTH}${INGEST_DAY} -m 1 --hive-import --hive-table ${TABLE_NAME} --create-hive-table --fields-terminated-by "|"

DB_QUERY="SELECT IP_ADDR, USERNAME, '"${i}"', EVENT_TIME FROM nav."${DB_TABLE}" WHERE \$CONDITIONS"

echo $DB_QUERY
        echo "Importing ${TABLE_NAME}  ..."

        sqoop import --connect jdbc:oracle:thin:@//${DB_HOST} --username ${DB_USER} --password ${DB_PASS} --query "${DB_QUERY}" --target-dir hdfs://${LANDING_ZONE}/dt=${INGEST_YEAR}${INGEST_MONTH}${INGEST_DAY} -m 1 --hive-import --hive-table OPS_TEAM_AUDIT.${TABLE_NAME} --create-hive-table --fields-terminated-by "|"

    done
}

function clean() {
    for i in hbase hdfs hive hue impala navms sentry solr
    do
        TABLE_NAME=nav${i}
        LANDING_ZONE=${PARENT_ZONE}/landing/${TABLE_NAME}
        echo "Removing ${LANDING_ZONE} ..."
        hdfs dfs -rm -R -f hdfs://${LANDING_ZONE}
    done
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
