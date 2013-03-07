package bargaining.agents

import bargaining._
import scala.math._

class ProportionKeeper(p: Double) extends Agent 
{
  
  override def name = "proportion:"+p
  
  def propose(agent: Agent, sum: Int): Division =
  {
    val x = (sum*p).toInt
    Division(x, sum-x)
  }
  
  def accept(agent: Agent, d: Division): Boolean =
  {
    val minY = ((d.forMe+d.forYou)*(1-p)).toInt
    d match {
      case Division(x,y) => y > minY
    }
  }
  
}


class FiftyFifty extends ProportionKeeper(0.5)
