
import org.scalatest._
import x._
import x.export._
import language.experimental.macros


class TieSpec extends FlatSpec  {

  "Tie" should "created" in {
    val tie = makeTie
  }

}
