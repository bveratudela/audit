#!/bin/sh

source ../common.properties

TABLE_NAME=scm
LANDING_ZONE=${PARENT_ZONE}/landing/${TABLE_NAME}

function import() {
    DB_QUERY='SELECT "0.0.0.0", USERS.USER_NAME, "scm", CAST(REVISIONS.TIMESTAMP/1000 AS INTEGER) FROM CONFIGS_AUD INNER JOIN REVISIONS INNER JOIN USERS WHERE $CONDITIONS AND CONFIGS_AUD.REV=REVISIONS.REVISION_ID AND USERS.USER_ID=REVISIONS.USER_ID;';
    echo "Importing ${TABLE_NAME} ..."
    sqoop import --connect jdbc:mysql://${DB_HOST}/scm --username ${DB_USER} --password ${DB_PASS} --query "${DB_QUERY}" --target-dir hdfs://${LANDING_ZONE}/dt=${INGEST_YEAR}${INGEST_MONTH}${INGEST_DAY} -m 1 --hive-import --hive-table ${TABLE_NAME} --create-hive-table --fields-terminated-by "|"
}

function clean() {
    echo "Removing ${LANDING_ZONE} ..."
    hdfs dfs -rm -R -f hdfs://${LANDING_ZONE}
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
