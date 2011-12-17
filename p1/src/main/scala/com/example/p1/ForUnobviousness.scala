package ua.gradsoft.p1;


object ForUnoviousness
{

  def main(args:Array[String]):Unit =
  {
    val l = List(1,2,3);
    var i = 0;
    val l1 = for(e <- l) yield { i=i+1; e }
    System.out.println("i="+i);

    val s = Stream(1,2,3);
    i = 0;
    val s1 = for(e <- s) yield { i=i+1; e }
    System.out.println("i="+i);

  }

}
