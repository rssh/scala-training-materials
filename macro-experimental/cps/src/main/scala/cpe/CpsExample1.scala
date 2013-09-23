package cpe

import scala.util.continuations._

object X
{

  def doSomething = reset {
     println("ready")
     val result = 1 + wacky * 3
     println(result)
  }                                     
  
  def wacky = shift {
     k: (Int => Unit) =>
       k(2)
       println("Yo!")
       k(3)
  }                       

  def main(args:Array[String]):Unit =
  {
    val r = doSomething
    println(r)
  }

}




