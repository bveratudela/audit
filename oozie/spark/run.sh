spark-submit --class com.cloudera.spark.MoveFiles --master yarn --deploy-mode client --executor-memory 1G --num-executors 2 ./target/scala-2.10/movefiles_2.10-1.0.jar /auth/landing /auth/final
