package bargaining

import agents._

object Main {
  
  def main(args: Array[String]): Unit =
  {
    val agents = Seq(
                   new RandomAgent(),
                   new ProportionKeeper(0.5),
                   new ProportionKeeper(0.6)
                 )
    val runner = new GameRunner(agents)
    runner.printState
    runner.playRandomGames(nGames=1000, sum=10)
    runner.printState
  }

}