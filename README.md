Requires Scala compiler which you can download from http://www.scala-lang.org. And also you need MapR v3.0.3 Sandbox which can be downloaded here : https://www.mapr.com/products/hadoop-download

How to compile?

Compile the .scala files with 

“scalac -cp .:$(hadoop classpath) *.scala”

How to run?

Run the scala class files with 

“scala -cp .:$(hadoop classpath) com.mapr.scala.example.Main”


Using SBT (Simple Build tool)

root@ubuntu1:/home/maprfs-scala-example# sbt
[info] Set current project to example (in build file:/home/maprfs-scala-example/)

> clean
[success] Total time: 1 s, completed Sep 15, 2014 11:03:26 AM
> compile
[info] Updating {file:/home/maprfs-scala-example/}maprfs-scala-example...
[info] Resolving org.fusesource.jansi#jansi;1.4 ...
[info] Done updating.
[info] Compiling 2 Scala sources to /home/maprfs-scala-example/target/scala-2.10/classes...
[success] Total time: 28 s, completed Sep 15, 2014 11:04:44 AM
< run
[info] Running com.mapr.scala.example.Main
New file created as empty-file.txt
Creating new file
Writing Example text  into the testfile.txt
Saving the file testfile.txt to MaprFs
Appended text from testfile1.txt to testfile.txt
Deleting the file empty-file.txt
[success] Total time: 2 s, completed Oct 6, 2014 10:30:35 AM
>

