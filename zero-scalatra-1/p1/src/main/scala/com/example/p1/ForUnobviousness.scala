package ua.gradsoft.p1;


object ForUnoviousness
{

  def main(args:Array[String]):Unit =
  {
    val l = List(1,2,3);
    var i = 0;
    val l1 = for(e <- l) yield { i=i+1; e }
    System.out.println("i="+i);

    val s = (1 to 100).toStream
    i = 0;
    val s1 = for(e <- s) yield { i=i+1; e+1 }
    System.out.println("i="+i);
  

    System.out.println("s1="+s1(5));
    System.out.println("i="+i);
    
  }
  
  //map(h::t) = map(h)::map(t)
  // map(lcons(h,t)) = lcons(map(h),map(t))

}
