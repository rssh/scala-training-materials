package bargaining.agents

import bargaining._
import scala.math._

class RandomAgent extends Agent
{

  override def name: String = "random"
    
    
  
  def propose(agent: Agent, sum: Int): Division =
  {
    val x = random.nextInt(sum)
    val me =  max(x,sum-x)
    Division(me, sum-me)
  }
  
  def accept(agent: Agent, division: Division): Boolean =
    true
  
  val random = new java.util.Random()
  random.setSeed(0L)
  
  
}