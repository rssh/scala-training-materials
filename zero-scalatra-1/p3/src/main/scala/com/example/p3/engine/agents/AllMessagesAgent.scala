package com.example.p3.engine.agents

import com.example.p3.engine._


class AllMessagesAgent(registry: TalkAgentRegistry) extends TalkAgent with OutputQueue
{
  
    def name="all";
  
    def  takeMessage(senderName: String, message: String): Unit =
    {
      
      for(agent <- registry.allAgents; if agent.isParticipateInAll) {
          if (agent.name != senderName) {
             val newMessage = senderName+":"+message;
             agent.takeMessage(name,newMessage);
             pushMessageToShow(agent.name,newMessage);
          }       
      }
            
    }
    
    def  recordOwnMessage(to:String, message:String) = {};
  
}
