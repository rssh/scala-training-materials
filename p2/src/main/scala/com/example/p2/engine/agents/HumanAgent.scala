package com.example.p2.engine.agents

import com.example.p2.engine._;

class HumanAgent(val name:String) extends TalkAgent
{

    def  answer(askingName: String, message:String) :String =
    {
      val retval = typedSentences.mkString("\n");
      typedSentences = Nil;
      retval;
    }
    
    override def isHuman = true;
    
    override def addSentence(message:String): Unit =
    {
      typedSentences = message::typedSentences ;
    }

    var typedSentences:List[String] = List();
    
}