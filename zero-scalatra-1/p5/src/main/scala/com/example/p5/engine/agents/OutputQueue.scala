package com.example.p5.engine.agents

import com.example.p5.engine._;
import scala.concurrent.stm._;

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
      atomic { implicit txn =>
       messagesToShow.put(whom, 
          messagesToShow.get(whom) match {
                case Some(messages) => (messages :+ message).takeRight(MAX_HISTORY)  
                case None           =>  IndexedSeq(message)         
          }
       );
      } 
    }

    def popMessagesToShow(whom: String): List[String] =
    {
     atomic{ implicit txn => 
      messagesToShow.get(whom) match {
        case Some(messages) => val retval = messages.toList
                               messagesToShow.put(whom,IndexedSeq())
                               retval
        case None => List()
      }
     } 
    }
    
    val MAX_HISTORY = 50 ;
  
    val messagesToShow = TMap[String,IndexedSeq[String]]() ;
    
}
