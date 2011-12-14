package com.example.p2.engine.agents

import com.example.p2.engine._;

class HumanAgent(val name:String) extends TalkAgent
{

    def  answer(askingName: String, optMessage:Option[String]) : Option[String] =
    {
      if (typedSentences.isEmpty) {
         None;
      } else {
        val retval = Some(typedSentences.mkString("\n"));
        typedSentences = Nil;
        retval;
      }
    }
    
    override def isHuman = true;
    
    override def addSentence(message:String): Unit =
    {
      typedSentences = message::typedSentences ;
    }

    var typedSentences:List[String] = List();
    
}