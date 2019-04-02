#!/bin/sh

source ../common.properties

TABLE_NAME=sentry
LANDING_ZONE=${PARENT_ZONE}/landing/${TABLE_NAME}

function import() {
    DB_QUERY='select r.ROLE_NAME, g.GROUP_NAME from SENTRY_GROUP g join SENTRY_ROLE_GROUP_MAP rg on rg.GROUP_ID = g.GROUP_ID   join SENTRY_ROLE r on r.ROLE_ID = rg.ROLE_ID   join SENTRY_ROLE_DB_PRIVILEGE_MAP rp on rp.ROLE_ID = r.ROLE_ID join SENTRY_DB_PRIVILEGE p on p.DB_PRIVILEGE_ID = rp.DB_PRIVILEGE_ID WHERE $CONDITIONS order by r.ROLE_NAME, g.GROUP_NAME;'
    echo "Importing ${DB_TABLE} ..."
    sqoop import --connect jdbc:mysql://${DB_HOST}/sentry --username ${DB_USER} --password ${DB_PASS} --query "${DB_QUERY}" --target-dir hdfs://${LANDING_ZONE}/dt=${INGEST_YEAR}${INGEST_MONTH}${INGEST_DAY} -m 1 --hive-import --hive-table ${TABLE_NAME} --create-hive-table --fields-terminated-by "|"
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
