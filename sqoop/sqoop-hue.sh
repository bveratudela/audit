#!/bin/sh

source ../common.properties

TABLE_NAME=hue
LANDING_ZONE=${PARENT_ZONE}/landing/${TABLE_NAME}

function import() {
#    DB_QUERY='SELECT "0.0.0.0", a.username, "hue", UNIX_TIMESTAMP(a.last_login) from hue.auth_user a, hue.auth_user_groups b, hue.auth_group c WHERE $CONDITIONS AND a.id = b.user_id and b.group_id = c.id order by a.id;'

#DB_QUERY="SELECT  a.username,'hue', a.last_login from hue.auth_user a, hue.auth_user_groups b, hue.auth_group c WHERE  a.id = b.user_id and b.group_id = c.id AND \$CONDITIONS order by a.id"

DB_QUERY="SELECT '0.0.0.0', a.username,'hue', (a.last_login) from hue.auth_user a, hue.auth_user_groups b, hue.auth_group c WHERE  a.id = b.user_id and b.group_id = c.id AND \$CONDITIONS order by a.id"

    echo "Importing ${TABLE_NAME} ..."

#sqoop import --connect jdbc:mysql://${DB_HOST}/hue --username ${DB_USER} --password ${DB_PASS} --query "${DB_QUERY}" --target-dir hdfs://${LANDING_ZONE}/dt=${INGEST_YEAR}${INGEST_MONTH}${INGEST_DAY} -m 1 --hive-import --hive-table ${TABLE_NAME} --create-hive-table --fields-terminated-by "|"

#sqoop import --connect jdbc:oracle:thin:@//${DB_HOST} --username ${DB_USER} --password ${DB_PASS} --query "${DB_QUERY}" --target-dir hdfs://${LANDING_ZONE}/dt=${INGEST_YEAR}${INGEST_MONTH}${INGEST_DAY} -m 1 --hive-import --hive-table OPS_TEAM_AUDIT.${TABLE_NAME}  --create-hive-table --fields-terminated-by "|"

sqoop import --options-file sqoop-hue.options --target-dir hdfs://${LANDING_ZONE}/
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
