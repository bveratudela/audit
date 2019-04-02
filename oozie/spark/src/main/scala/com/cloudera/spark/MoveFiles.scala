package com.cloudera.spark;

import java.text.SimpleDateFormat
import java.util.Date

import scala.collection.mutable.ListBuffer

import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext._
import org.apache.spark.{SparkConf, SparkContext}

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.fs.{FileAlreadyExistsException, FileSystem, FileUtil, Path}

object MoveFiles extends java.io.Serializable {
    val fmt = new SimpleDateFormat("yyyyMMddHHmmss")
    val date = fmt.format(new Date())

    def main(args: Array[String]) {
        if (args.length < 1) {
            System.err.println("Usage: MoveFiles <src path> <dst path>")
            System.exit(1)
        }

        val conf = new SparkConf()
        val sc = new SparkContext(conf)

        conf.setAppName("Audit Move Files")
        conf.set("spark.cleaner.ttl", "120000")

        val srcPath = new Path(args(0))
        val dstPath = new Path(args(1))

        val hdfs = FileSystem.get(new Configuration())

        if (!hdfs.exists(dstPath)) {
            hdfs.mkdirs(dstPath)
        }

        val files = hdfs.listFiles(srcPath, true)
        val fileList = ListBuffer[(String, String)]()

        while (files.hasNext()) {
            val thisFile = files.next()
            if (thisFile.isFile() && !thisFile.getPath().getName().endsWith("_SUCCESS")) {
                val parent = thisFile.getPath().getParent().toString
                val source = parent.substring(parent.lastIndexOf("/") + 1)
                val srcFile = parent + "/" + thisFile.getPath().getName().toString
                val dstFile = args(1) + "/" + date + "." + source
                fileList += ((srcFile, dstFile))
                hdfs.rename(new Path(srcFile), new Path(dstFile))
            }
        }
/*      SEE https://stackoverflow.com/questions/24633309/how-to-rename-huge-amount-of-files-in-hadoop-spark
        val filesRDD = sc.parallelize(fileList, 8)

        filesRDD.collect().foreach(println)

        val result = filesRDD.mapPartitions(iter => {
            val hdfs2 = FileSystem.get(new Configuration())
            val partition = iter.map(file => {
                hdfs2.rename(new Path(file._1), new Path(file._2))
            }).toList
            partition.iterator
        })

        filesRDD.coalesce(1, true).saveAsTextFile("audit-processed-" + date + ".out")
*/
        sc.stop()
    }
}
