package com.example.p3.engine



/**
 * Base trait for possible interlocutors.
 **/
trait TalkAgent {
  
  
  def  name:String;

  //def  answer(askingName: String, message: String) :String
  def  answer(askingName: String, optMessage:Option[String]) :Option[String];
  
  def  isHuman: Boolean = false;
  
  /**
   * add sentence to answer:  valid only for humans.
   * (btw, this is not 'scala-way' to do it right. )
   */
  def  addSentence(message:String): Unit = throw new UnsupportedOperationException("unsupported");
  
}
