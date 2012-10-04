package com.example.p2.engine.agents

import com.example.p2.engine._

object YesSir extends TalkAgent
{
  
  def name="tom"
  
  def answer(askingName:String, optMessage:Option[String]):Option[String] 
           = optMessage map { x => "yes, sir" };

}