package com.example.p4.engine.agents

import com.example.p4.engine._;

trait FunAnswerAgent extends TalkAgent with OutputQueue
{
  
  def answer(senderName: String, message:String): String;

  def  takeMessage(senderName: String, message: String): Unit =
  { 
    pushMessageToShow(senderName, answer(senderName,message));
  }
  
  def  recordOwnMessage(to:String, message:String): Unit = {}
  
}
