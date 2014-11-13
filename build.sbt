name := "example"

organization := "com.mapr.scala"

version := "0.1"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "org.apache.hadoop" % "hadoop-client" % "1.0.3-mapr-3.0.3" excludeAll(
    ExclusionRule(organization = "com.sun.jdmk"),
    ExclusionRule(organization = "com.sun.jmx"),
    ExclusionRule(organization = "javax.jms")), 
  "org.scalatest" % "scalatest_2.10" % "1.9.2" % "test"
)

resolvers += "MapR jars" at "http://repository.mapr.com/nexus/content/groups/mapr-public/"

initialCommands := "import com.mapr.scala.example._"

