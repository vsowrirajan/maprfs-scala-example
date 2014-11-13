package com.mapr.scala.example

import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import org.apache.hadoop.conf._
import org.apache.hadoop.fs._

class MapRfsFileService {
  private val conf = new Configuration()
  private val maprfsCoreSitePath = new Path("core-site.xml")
  private val maprfsSitePath = new Path("maprfs-site.xml")

  conf.addResource(maprfsCoreSitePath)
  conf.addResource(maprfsSitePath)

  private val fileSystem = FileSystem.get(conf)

  def mkdirs(folderPath: String): Unit = {
   val path = new Path(folderPath)
     if (!fileSystem.exists(path)) {
	fileSystem.mkdirs(path)
     }
  }

 def createNewFile(filepath:String): Unit = {
   val file = new File(filepath)
   val out = fileSystem.createNewFile(new Path(file.getName))
   if(out)
     println("New file created as "+file.getName)
   else
     println("File cannot be created : "+file.getName)
  }

  def createAndSave(filepath: String): Unit = {
    val file = new File(filepath)
    val out = fileSystem.create(new Path(file.getName))
    var in = new BufferedInputStream(new FileInputStream(file))
    var b = new Array[Byte](1024)
    var numBytes = in.read(b)
    while (numBytes > 0) {
      out.write(b, 0, numBytes)
      numBytes = in.read(b)
    }
    in.close()
    out.close()
  }

 def appendToFile(tofilepath: String, fromfilepath: String): Unit = {
   var file = new File(tofilepath)
   var out = fileSystem.append(new Path(file.getName))
   var in = new BufferedInputStream(new FileInputStream(new File(fromfilepath)))
   var b = new Array[Byte](1024)
   var numBytes = in.read(b)
   while (numBytes > 0) {
     out.write(b, 0, numBytes)
     numBytes = in.read(b)
    }
    in.close()
    out.close()
 }

  def getFile(filename: String): InputStream = {
    var path = new Path(filename)
    fileSystem.open(path)
  }

  def deleteFile(filename: String): Boolean = {
     var path = new Path(filename)
     fileSystem.delete(path, true)
  }

  def close() = {
     fileSystem.close
   }
 }
