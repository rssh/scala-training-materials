package bargaining

import scala.math._

class GameRunner(agents: Seq[Agent], randomSeed: Int = 1)
{

  case class AgentRecord(agent:Agent, balance: Int)
  {
     def incrBalance(x:Int) = copy(balance = balance + x)
  }

  var records = (agents map (AgentRecord(_,0)) ).toArray
  val random = new java.util.Random()
  random.setSeed(randomSeed)

  def playRandomGames(nGames: Int, sum: Int): Unit =
  {
   for( i <- 1 to nGames) {
     val xi = abs(random.nextInt) % records.length 
     val yi = abs(random.nextInt) % records.length 
     System.err.println("xi="+xi+",yi="+yi+", length="+records.length)
     val d = doOnePlay(records(xi),records(yi), sum)
     records(xi) = records(xi) incrBalance d.forMe
     records(yi) = records(yi) incrBalance d.forYou
   } 
  }

  def printState =
      Console.println(records.toSeq)

  def  doOnePlay(x: AgentRecord, y: AgentRecord, sum: Int) : Division =
  {
    val d = x.agent.propose(y.agent, sum)
    if (y.agent.accept(x.agent,d)) 
       d
    else 
       Division(0,0)    
  }



}

