package com.example.p3.engine

trait FederatedMessageProcessor {

  this: TalkAgentRegistry =>
    
  def process(askingName: String, agentName:String, optMessage:Option[String]):Option[String] =
  { 
    for(message <- optMessage; talkAgent <- find(askingName)) {
       if (talkAgent.isHuman) talkAgent.addSentence(message);
    }  
    find(agentName) match {
      case Some(talkAgent) => talkAgent.answer(askingName, optMessage);
      case None => Some("Sorry, agent "+agentName+" is not found");
    }
  }
  
  def chooseAgent(askingName: String, message:String):Either[String,String] =
  {
    //log("TalkEngine:chooseAgent");
    for (name <- allNames) {
        if (message.equals(name)) {
            return Right(name);
        } 
    }
    Left("please, choose one of interlocutors to speak from: "+allNames.toSeq.mkString(","));
  }
 
}

trait LoggingMessageProcessor extends FederatedMessageProcessor
{

  this: TalkAgentRegistry with Logged =>
  
   override def process(askingName: String, agentName:String, optMessage:Option[String]):Option[String] =
   {
      log("%s -> %s : %s".format(askingName, agentName, optMessage.getOrElse("")))  
      val retval = super.process(askingName, agentName, optMessage);
      log("%s <- %s : %s".format(askingName, agentName, retval));
      retval
   }

}
