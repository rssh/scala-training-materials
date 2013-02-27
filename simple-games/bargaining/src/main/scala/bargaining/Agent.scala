package bargaining

case class Division(forMe: Int, forYou: Int)

trait Agent {

  def name: String = "unknown"
  
  def propose(agent: Agent, sum: Int): Division
  
  def accept(agent: Agent, division: Division): Boolean
  
  
}
