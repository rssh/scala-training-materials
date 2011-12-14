package com.example.p2.engine.agents

import com.example.p2.engine._;

class AllMessageAgent(registry: TalkAgentRegistry) extends TalkAgent
{
  
    def name="all";
  
    def  answer(askingName: String, message:String) :String =
    {
      
      registry.foreach { (agent:TalkAgent) =>
        if (agent.isHuman)
          agent.addSentence(askingName+":"+message);
      }
      
      registry.find(askingName) match {
        case Some(person) => val reply = person.answer(askingName,message);
                             registry.foreach{ (agent:TalkAgent) => if (agent.isHuman) agent.addSentence(reply);  }
        case None =>    throw new RuntimeException("name "+askingName+" is not found");                 
      }
      
      //registry.find(askingName) match {
      //  case 
      //}
      
      //registry.eachAgents()
      
      ""
    }

 

}