package com.example.p3.engine.agents

import com.example.p3.engine._

object YesSir extends TalkAgent
{
  
  def name="tom"
  
  def answer(askingName:String, optMessage:Option[String]):Option[String] 
           = optMessage map { x => "yes, sir" };

}
