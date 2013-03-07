package bargaining

import agents._

object Main {
  
  def main(args: Array[String]): Unit =
  {
    val agents = Seq(
                   new RandomAgent(),
                   new ProportionKeeper(0.5),
                   //new ProportionKeeper(0.7),
                   //new ProportionKeeper(0.8),
                   //new ProportionKeeper(0.9),
                   new Scrooge(),
                   new YarsAgent()
                 )
    val runner = new GameRunner(agents,scala.util.Random.nextInt)
    runner.printState
    runner.playRandomGames(nGames=1000, sum=100)
    runner.printState
  }

}