# audit

This is the stuff started for the audit use-case. It consists of 3 folders:

sqoop: This contains sqoop scripts that query from your RDBMS (the sample uses MySQL but you can adapt to Oracle). Specifically you will find one script for importing user access from hue, scm (Cloudera Manager), the various Navigator Audit tables, and also one for grabbing role/group info from Sentry.

oozie: This contains some progress towards an Oozie workflow (not complete yet) that could be run daily to fetch all the relevant data from your RDBMS and also move the syslog events into the same database.

flume: This contains a flume config along with code for an interceptor that parses ssh and centrify messages and puts them in the same format as the data retrieved from sqoop.

The schema for this work follows the convention:

```
host_or_IP_address_of_access (if available, otherwise hardcoded to 0.0.0.0 but can be changed to something more meaningful like the address of the load balancer)
user who accessed the system
component accessed (like hue, scm, etc.)
timestamp in seconds of last access
```

