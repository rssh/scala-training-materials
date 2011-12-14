package com.example.p2.engine.agents

import com.example.p2.engine._

object YesSir extends TalkAgent
{
  
  def name="tom"
  
  def answer(askingName:String, message:String):String = "yes, sir";

}