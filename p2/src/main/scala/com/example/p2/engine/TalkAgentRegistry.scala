package com.example.p2.engine

trait TalkAgentRegistry {

  
  def allNames:Iterable[String];
  
  def find(name:String):Option[TalkAgent]
  
  
}


class SimpleTalkAgentRegistry(private val m: Map[String,TalkAgent]) extends TalkAgentRegistry
{
  
  def find(name:String) = m.get(name); 
  
  def allNames = m.keys;
  
}