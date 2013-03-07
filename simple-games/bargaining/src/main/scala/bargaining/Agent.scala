package bargaining

case class Division(forMe: Int, forYou: Int)


class Division1(forMe: Int, forYou: Int)

case class Division2( var forMe: Int, var forYou: Int)


trait Agent {

  def name: String = "unknown"
  
  def propose(agent: Agent, sum: Int): Division
  
  def accept(agent: Agent, division: Division): Boolean
  
  
}
