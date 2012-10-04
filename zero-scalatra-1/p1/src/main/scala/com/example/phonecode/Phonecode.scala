package phonecode

import scala.io._
import java.io.File

import scala.collection.mutable._

import scala.io.Source

object Phonecode {

  val substTable = HashMap[Char, Char]('e'->'0', 'j'->'1', 'n'->'1', 'q'->'1', 'r'->'2', 'w'->'2', 'x'->'2', 'd'->'3', 's'->'3', 'y'->'3', 'f'->'4', 't'->'4', 'a'->'5', 'm'->'5', 'c'->'6', 'i'->'6', 'v'->'6', 'b'->'7', 'k'->'7', 'u'->'7', 'l'->'8', 'o'->'8', 'p'->'8', 'g'->'9', 'h'->'9', 'z'->'9')

  def calcKey(word: String) = word.toLowerCase.map(
        substTable.getOrElse(_,"")
     ).mkString
  

  
  var dictionary = new HashMap[String, List[String]]()
  
   
  
  def main(args: Array[String]): Unit = {
    
   // var dictionaryLines = Source.fromFile(new File("resources\\test.w")).getLines()
    
    /*
    Source
    	.fromFile(new File("resources\\test.w"))
    	.getLines()
    	.foreach(
    	    dictionary.put(
    	        calcKey(_),
    	        dictionary.getOrElse(
    	            calcKey(_), 
    	            List[String]()
    	        )
    	        + _
    	     )
    	)
    	*/
 
    for( line <- Source.fromFile(new File("resources\\test.w")).getLines()) {
      val key = calcKey(line)
      dictionary.put(key,dictionary.get(key) match {
                           case None => List(line)
                           case Some(x) => line :: x
                     })
    }
    
    //dictionary.foreach(println _)
    
    def find(line: String, index: Int, buffer: String, phone: String, wasDigit: Boolean = false): Unit = {
           
      if(index == line.length()) {
        println(phone + ": " + buffer.trim)
        return
      }
      
      var wasCall = false 
      
      var currentLen = 1
      
      
      
      while (currentLen + index <= line.length()){
        
        val key = line.substring(index, index + currentLen)
    
        for(l <- dictionary.get(key); word <- l) {
           find(line, index + currentLen, buffer + " " + word, phone)
           wasCall = true         
        }
        
       //dictionary.get(key).flatMap(word:String =>  
       //
        
        
  //      dictionary.get(key) match {
  //        case Some(l) =>
  //          for(word <- l ) {
  //             find(line, index + currentLen, buffer + " " + word, phone)
  //             wasCall = true
  //          }
  //        case None =>
  //      }
           
        currentLen += 1
      } 
      
      if(!wasDigit && !wasCall) {
          find(line, index + 1, buffer + " " + line.charAt(index), phone, true)
        }
      
    }
   
    for( phone <- Source.fromFile(new File("resources\\test.t")).getLines()) {
    
      find(phone.replaceAll("[^0-9]",""), 0, "", phone)
      
    }  
  }

}