
import org.scalatest._
import x._
import language.experimental.macros


class TieSpec extends FlatSpec  {

  "X" should "compile apply" in {
    X.xProvider.apply(1)
  }

  "X" should "compile short form of apply" in {
    X.xProvider(1)
  }

  "without macros" should "possibke use short for or apply" in {
    class A {
       def apply(x:Int) = {}
    }
    def newA = new A
    newA(1)
  }

  "Y" should "compile short form of apply" in {
    val newY = new Y
    newY(1)
  }

}
