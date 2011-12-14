package com.example.p2.engine

trait FederatedMessageProcessor {

  this: TalkAgentRegistry =>
    
  def process(askingName: String, agentName:String, message:String):String =
  {    
    find(agentName) match {
      case Some(talkAgent) => talkAgent.answer(askingName, message);
      case None => "Sorry, agent "+agentName+" is not found";
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
  
   override def process(askingName: String, agentName:String, message:String):String =
   {
      log("%s -> %s : %s".format(askingName, agentName, message))  
      val retval = super.process(askingName, agentName, message);
      log("%s <- %s : %s".format(askingName, agentName, retval));
      retval
   }

}