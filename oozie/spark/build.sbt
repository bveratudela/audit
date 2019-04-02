name         := "MoveFiles"
version      := "1.0"
organization := "cloudera"

scalaVersion := "2.10.5"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

val sparkVersion = "1.6.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-hive" % sparkVersion
)

resolvers ++= Seq(
  Resolver.mavenLocal,
  "Apache Repo" at "https://repository.apache.org/content/repositories/releases/",
  "cloudera-repos" at "https://repository.cloudera.com/artifactory/cloudera-repos/"
)
