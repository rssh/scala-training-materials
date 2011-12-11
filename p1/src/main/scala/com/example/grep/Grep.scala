package com.example.grep

import scala.io._
import scala.util.matching._

object Grep {
  
  
  def  main(args:Array[String]):Unit =
  {
    grep(args.slice(1,args.length).toSeq:_*);
  }
  
  def grep(args:String*):Unit = 
  {
    if (args.length == 0 ) {
      Console.println("Usage: grep pattern [fnames]");
    } else {
      val regexpr = new Regex(args(1));
      if (args.length == 2) {
        checkSource(regexpr,Source.stdin,"stdin");
      } else {
        checkFiles(regexpr, args.slice(1,args.length));
      }
    }    
  }
  
  
  def checkFiles(regexpr:Regex, files:Seq[String]): Unit =
  {
    for(fname <- files) {
        checkSource(regexpr,Source.fromFile(fname,"UTF-8"),fname)
    }
  }
  
  def checkSource(regexpr:Regex, source:Source, fname:String):Unit =
  {
    var i=1;
    for(line <- source.getLines()) {
       val its = regexpr.findAllIn(line);
       if (!its.isEmpty) {
         Console.println("%s %d:%s".format(fname,i,line));
       }
       i=i+1;
    }
    
  }

}