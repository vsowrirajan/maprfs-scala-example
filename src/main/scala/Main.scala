package com.mapr.scala.example
import java.io._
import java.util.Arrays;

object Main {
  def main(args: Array[String]) {
    val testfileName = "testfile.txt"
    val testText = "Example text "
    val appendFile = "testfile1.txt"

    val emptyFile = "empty-file.txt"

    val fileService = new MapRfsFileService

    //Create an Empty file in MapRFS

    println("Creating an empty file")
    fileService.createNewFile(emptyFile)

    //Creating a new file and saving it to MapRFS

    println("Creating new file")
    val testfile = new File(testfileName)
    val testfileWriter = new BufferedWriter(new FileWriter(testfile))
    println("Writing " + testText + " into the " + testfileName)
    testfileWriter.write(testText)
    testfileWriter.close
    println("Saving the file " + testfileName + " to MaprFs")
    fileService.createAndSave(testfileName)

    //Append to file in MapRFS

    fileService.appendToFile(testfileName,appendFile)
    println("Appended text from "+ appendFile + " to "+testfileName)

     //Reading a file from MapRFS

    val outputStream = new FileOutputStream(new File(testfileName))
    val in = fileService.getFile(testfileName)
    var b = new Array[Byte](1024)
    var numBytes = in.read(b)
    while (numBytes > 0) {
      outputStream.write(b, 0, numBytes)
      numBytes = in.read(b)
    }
    outputStream.close
    in.close

    //Deleting a file from MapRFS

    println("Deleting the file " + emptyFile)
    fileService.deleteFile(emptyFile)

   //Close the FileSystem Handle
    fileService.close
   }

  }

