package bargaining.agents

import bargaining._
import scala.util._

class YarsAgent extends Agent {

  override def name: String = "John Smith"

   def propose (agent: Agent, sum: Int): Division =
     Division(sum/2 + 1, sum/2 - 1)

   def accept (agent: Agent, div: Division): Boolean = 
   {
     val Division(forYou, forMe) = div
     forMe >= forYou || Random.nextBoolean 
   } 
}


