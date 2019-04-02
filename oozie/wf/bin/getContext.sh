#!/bin/sh

NN_URI=hdfs://nameservice1

INGEST_YEAR=$(date '+%Y')
INGEST_MONTH=$(date '+%m')
INGEST_DAY=$(date '+%d')

echo ingestYear=${INGEST_YEAR}
echo ingestMonth=${INGEST_MONTH}
echo ingestDay=${INGEST_DAY}

echo hueTargetDir=${NN_URI}/auth/landing/hue
echo scmTargetDir=${NN_URI}/auth/landing/scm
echo sentryTargetDir=${NN_URI}/auth/landing/sentry
echo navHdfsTargetDir=${NN_URI}/auth/landing/navhdfs
echo navHiveTargetDir=${NN_URI}/auth/landing/navhive
echo navHueTargetDir=${NN_URI}/auth/landing/navhue
echo navMsTargetDir=${NN_URI}/auth/landing/navms
echo navImpalaTargetDir=${NN_URI}/auth/landing/navimpala
echo navSentryTargetDir=${NN_URI}/auth/landing/navsentry

echo navTableSuffix=AUDIT_EVENTS_${INGEST_YEAR}_${INGEST_MONTH}_${INGEST_DAY}

echo inputDir=${NN_URI}/auth/landing
echo outputDir=${NN_URI}/auth/final
