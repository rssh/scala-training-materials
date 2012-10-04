package com.example.p5.engine.agents

import com.example.p5.engine._

class SysAgent(registry: TalkAgentRegistry) extends FunAnswerAgent
{
  
  def name = SysAgent.NAME;
  
  def answer(senderName: String, message:String): String =
  {
    message match {
      case ":bye" => registry.find(senderName) match {
                       case Some(agent) => 
                          if (agent.isInstanceOf[InterlocutorKeeper]) {
                            agent.asInstanceOf[InterlocutorKeeper].interlocutorName=None;
                            "now you can connect with somebody other"
                          } else {
                            ":bye is not applicable for you, ignoring"
                          }
                       case None =>
                         "user "+senderName+" is not in chat now"
      }
      case _ => "Unknown command:"+message ;
    }
    
  }
  
  
}

object SysAgent
{
  final val NAME = "sys";  
}
