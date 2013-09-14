
import org.scalatest._
import x._
import x.export._
import language.experimental.macros


class TieSpec extends FlatSpec  {

  "Tie" should "created" in {
    val tie = makeTie
  }

  "Tie" should "be able to do reading macro" in {
    val tie = makeTie
    val ch = new Channel[Int] {}
    tie.reading(ch)
  }


}
