package bargaining

object caseClassExamples {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet


  class Point(inx: Int, iny: Int)
  {
    def x = inx
    def y = iny
  
    override def toString = "Point(%d,%d)".format(x,y)
    
    override def equals(other:Any): Boolean =
      if (other.isInstanceOf[Point]) {
        false
      } else {
        val otherPoint = other.asInstanceOf[Point]
        otherPoint.x == x && otherPoint.y == y
      }
    
    override def hashCode: Int =
      x + y
    
  }
     
  class Point3(x:Int,y:Int,z:Int) extends Point(x,y)
  
  object Point
  {
  
    def apply(x:Int, y:Int): Point =
    {  new Point(x,y) }
    
    def unapply(p:Point): Option[(Int,Int)]
      = Some(p.x,p.y)
  
  }
  
  object Twice
  {
  
    def unapply(x:Int): Option[Int]
      = if (x % 2 == 0) Some(x/2) else None
   
  
  }


  val  p1 = new Point(1,2)                        //> p1  : bargaining.caseClassExamples.Point = Point(1,2)
  
  val  p2 = new Point(1,3)                        //> p2  : bargaining.caseClassExamples.Point = Point(1,3)
  
   
  val p = Point(1,1)                              //> p  : bargaining.caseClassExamples.Point = Point(1,1)
  
  p.x                                             //> res0: Int = 1

  7 match {
    case Twice(x) => Console.println("twice:"+x)
    case _  => Console.println("even")
  }                                               //> even
  
  p                                               //> res1: bargaining.caseClassExamples.Point = Point(1,1)

}