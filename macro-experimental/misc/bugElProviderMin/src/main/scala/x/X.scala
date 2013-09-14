package x

import language.experimental.macros
import scala.reflect.macros._
import scala.reflect.api._

object export
{
   def makeTie = macro X.makeTieImpl
}


trait Channel[A]

class Tie



object X
{

  def makeTieImpl(c:Context) =
  {
    import c.universe._    
    {
     val rtree = q"""
     {
      class H0
      {
        def tie = new Tie;
        def reading[A](ch: Channel[A]) = macro X.readingImpl[H0,A]
      }
      new H0 {}
     }
     """
     c.Expr[Any](rtree)
    }
  }


  def readingImpl[H0, A](c:Context)(ch: c.Expr[Channel[A]])(implicit h0tag: c.WeakTypeTag[H0]) =
  {
    import c.universe._    
    val h0Type = /*typeOf[H0]*/ h0tag.tpe
    val tie = h0Type.members.filter(_.name.decoded=="tie").head
    var rtree = q"""
    {
     class  H1 {
       def tie:Tie = $tie
       def channel = $ch
       //def apply(f: A => Unit) = macro X.applyReadFun[this.type,A]
     }
     new H1
    } 
    """
    Console.println("rtree = " + rtree)
    c.Expr[Any](rtree)
  }
     


}
