package com.example.p5.engine.agents

import com.example.p5.engine._;

class HumanAgent(val name:String) extends TalkAgent 
                                       //with OutputQueue
                                       with DBMessageQueue
                                       with InterlocutorKeeper
{

    def  takeMessage(senderName: String, message: String): Unit = {
      // do nothing: all messages are deliver to human vie reading from screen by eyes 
    }
    
    override def isParticipateInAll = true;
    
    override def recordOwnMessage(receiverName: String, message: String)
    {
      pushMessageToShow(receiverName, message)
    }

    override var interlocutorName: Option[String] = None;
    
}
