import sbt._
import sbt.Keys._
 
object Utils extends Build {
val SPARK_VERSION = "1.2.1"
val SCALA_VERSION = "2.10.3"
 
lazy val root = Project(
id = "root",
base = file("."),
settings = coreSettings)
 
val excludeNetty = ExclusionRule(organization = "org.jboss.netty")
val excludeAsm = ExclusionRule(organization = "asm")
val excludeServletApi = ExclusionRule(organization = "javax.servlet")
val excludeJackson = ExclusionRule(organization = "org.codehaus.jackson")
val excludeJersey = ExclusionRule(organization = "com.sun.jersey")
val excludeMortbay = ExclusionRule(organization = "org.mortbay.jetty")
val excludeZookeeper = ExclusionRule(organization =
"org.apache.zookeeper:zookeeper")
 
 
def coreSettings = net.virtualvoid.sbt.graph.Plugin.graphSettings ++
Defaults.defaultSettings ++ Seq(
 
name := "maprfs-scala-example",
organization := "com.mapr.scala",
version := "0.1",
scalaVersion := SCALA_VERSION,
scalacOptions := Seq("-deprecation", "-unchecked",
"-optimize","-feature"), 
 
// Download managed jars into lib_managed.
//retrieveManaged := true,
resolvers ++= Seq(
"Local Maven" at Path.userHome.asFile.toURI.toURL + ".m2/repository",
"Local Ivy Repository" at Path.userHome.asFile.toURI.toURL +
".ivy2/local",
"maven2-repository.dev.java.net" at "http://download.java.net/maven/2",
"Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
"JBoss Repository" at "http://repository.jboss.org/nexus/content/repositories/releases/",
"Spray Repository" at "http://repo.spray.io/",
//"Cloudera Repository" at "http://repository.cloudera.com/artifactory/cloudera-repos/",
"Java.net Maven 2 Repository" at "http://download.java.net/maven/2",
"MapR Repository" at "http://repository.mapr.com/nexus/content/groups/mapr-public/"),
 
fork := true,
javaOptions += "-XX:MaxPermSize=1024m",
javaOptions += "-Xmx3g",

 
libraryDependencies ++= Seq(
"org.scalaz" %% "scalaz-core" % "7.1.0",
"org.xerial.snappy" % "snappy-java" % "1.1.1.6",
 
"org.scalatest" %% "scalatest" % "2.2.0" % "test",
"junit" % "junit" % "4.11" % "test",
"org.apache.commons" % "commons-lang3" % "3.3.2",

"org.apache.hadoop" % "hadoop-client" % "2.5.1-mapr-1501" excludeAll (excludeJackson, excludeNetty, excludeAsm),
"org.apache.hbase" % "hbase-client" % "0.98.7-mapr-1501-r1" excludeAll (excludeJackson, excludeNetty, excludeAsm, excludeServletApi, excludeMortbay)
 
),
libraryDependencies <+= scalaVersion("org.scala-lang" %
"scala-compiler" % _))
}
