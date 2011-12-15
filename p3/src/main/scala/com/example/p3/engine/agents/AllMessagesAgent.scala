package com.example.p3.engine.agents

import com.example.p3.engine._
import scala.collection.mutable.ConcurrentMap
import java.util.concurrent.{ ConcurrentHashMap => JavaConcurrentHashMap }
import scala.collection.JavaConversions._


class AllMessagesAgent(registry: TalkAgentRegistry) extends TalkAgent
{
  
    def name="all";
  
    def  answer(askingName: String, optMessage:Option[String]) :Option[String] =
    {
      
      for(message <- optMessage) {
         registry.foreach { (agent:TalkAgent) => 
           if (agent.isHuman) { 
            for(answer <- agent.answer(askingName,optMessage)) {
                registry.foreach{ (x:TalkAgent) => 
                                       if (x.isHuman && x.name!=agent.name) pushMessageToShow(x.name,answer); 
                                } 
            }
           }
         } 
      }
      
      val replies = popMessagesToShow(askingName);
      if (replies.isEmpty)
          None
        else
          Some(replies.mkString("\n"));
      
    }

    
    
    def pushMessageToShow(whom: String, message: String):Unit =
    {
       messagesToShow.get(whom) match {
         case Some(messages) => messagesToShow.replace(whom,(messages :+ message).takeRight(MAX_HISTORY));  
         case None => messagesToShow.putIfAbsent(whom, IndexedSeq(message));
       }
    }

    def popMessagesToShow(whom: String): List[String] =
    {
      messagesToShow.get(whom) match {
        case Some(messages) => val retval = messages.toList
                               messagesToShow.replace(whom,IndexedSeq())
                               retval
        case None => List()
      }
    }
    
    final val MAX_HISTORY = 50;
    
    val messagesToShow : ConcurrentMap[String,IndexedSeq[String]] = new JavaConcurrentHashMap[String,IndexedSeq[String]]();
    

}
