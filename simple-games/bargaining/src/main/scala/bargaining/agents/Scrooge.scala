package bargaining.agents

import bargaining._
import scala.math._


class Scrooge  extends Agent {

  override def name = "scrooge:"
  
  def propose(agent: Agent, sum: Int): Division =
  {
    val x = (sum*0.9).toInt
    Division(x, sum-x)
  }
  
  def accept(agent: Agent, d: Division): Boolean =
  {
    return d.forYou > 0
  }
  
  
  
}