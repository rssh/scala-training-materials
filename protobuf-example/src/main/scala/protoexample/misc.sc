package protoexample

//import scala.concurrent._
import scala.actors._

object misc {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  
  val `then` = 1                                  //> then  : Int = 1
  
  
  var x: Option[Int] = None                       //> x  : Option[Int] = None
  
  x = Some(1)
  
  var s: String= null                             //> s  : String = null
    
  Option(s)                                       //> res0: Option[String] = None
  
  val y = x map (_ +1)                            //> y  : Option[Int] = Some(2)
  
  for( y <- x)  yield y+1                         //> res1: Option[Int] = Some(2)
  
  val fx: Future[Int] = Futures.future[Int]({ Thread.sleep(30); 1})
                                                  //> fx  : scala.actors.Future[Int] = <function0>
                                                  
  val fy = for( i <- fx) yield i+1                //> fy  : Responder[Int] = Responder
  
  fx()                                            //> res2: Int = 1
  
  val e: Either[Int,String] = Right("q")          //> e  : Either[Int,String] = Right(q)
  val e1: Either[Int,String] = Left(0)            //> e1  : Either[Int,String] = Left(0)
  
  for(x <- e.right;
      x1 <- e1.right) yield x+x1+"22"             //> res3: scala.util.Either[Int,String] = Left(0)
  
  e.right.flatMap(x => e1.right.map(x1 => x+x1+"22"  ))
                                                  //> res4: scala.util.Either[Int,String] = Left(0)
  
  
}