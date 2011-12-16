package com.example.p3.engine

import agents._;

trait FederatedMessageProcessor {

  this: TalkAgentRegistryProvider =>
    
  def processSend(senderName: String, receiverName:String, message: String):Unit =
  { 
    
    for(sender <- registry.find(senderName)) {
        sender.recordOwnMessage(senderName,message);
    }
    
    
    registry.find(receiverName) match {
      case Some(receiver) => receiver.takeMessage(senderName,message)
      case None => processSend(SysAgent.name,senderName,"Sorry, agent "+receiverName+" is not found");
    }
    
  }
  
  def processRequestNew(requestorName:String): String =
  {
    takeNewMessages(requestorName).map( _._2 ).mkString("\n"); 
  }
  
  def takeNewMessages(requestorName:String): Iterable[(String,String)] = {
    
      //var retval: List[(String,String)] = Nil;
      //for(agent<-registry.allAgents; message <- agent.giveMessageFor(requestorName)) {
      //    retval = (agent.name, message) :: retval ;
      //}
      //retval;

      
      for(agent<-registry.allAgents;
          message <- agent.giveMessageFor(requestorName)) yield 
            (agent.name, message)
      
      //registry.allAgents.flatMap(agent => agent.giveMessagessFor(requestorName) ).flatMap((agent.name,_))      
            
  
    
      //registry.allAgents foldRight(Nil)(
      //           (agent,l)=>(agent.name,agent.giveMessageFor(requestorName))::l 
      
      
  }
  
  def chooseAgent(askingName: String, message:String):Either[String,String] =
  {
    //log("TalkEngine:chooseAgent");
    registry.allAgents.find(message == _.name) match {
      case Some(agent) => Right(agent.name);
      case None => Left("please, choose one of interlocutors to speak from: "+registry.allNames.toSeq.mkString(","));
    }
  }
 
}

trait LoggingMessageProcessor extends FederatedMessageProcessor
{

  this: TalkAgentRegistryProvider with Logged =>
  
   override def processSend(senderName: String, receiverName:String, message: String):Unit =
   {
      log("%s -> %s : %s".format(senderName, receiverName, message))  
      super.processSend(senderName, receiverName, message);
   }
   

}
