package com.example.p3.engine.agents



object SysAgent extends FunAnswerAgent
{

  def name = "sys";
  
  def answer(senderName: String, message:String): String =
  {
    "Unknown command:"+message ;
  }
  
  
}