package com.example.p3.engine.agents

import com.example.p3.engine._;

trait OutputQueue
{
  
    this: TalkAgent =>

    def  giveMessageFor(receiverName: String): Option[String] =
    {
      val messages = popMessagesToShow(receiverName)
      if (messages.isEmpty) None else Some(messages mkString "\n")
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
    
    val MAX_HISTORY = 50 ;
  
    val messagesToShow = Util.newConcurrentHashMap[String,IndexedSeq[String]] ;
    
}