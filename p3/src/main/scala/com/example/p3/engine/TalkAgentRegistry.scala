package com.example.p3.engine

import scala.collection.mutable.ConcurrentMap;

trait TalkAgentRegistry {

  def add(agent:TalkAgent):Unit;
  
  def remove(agent:TalkAgent):Unit;
  
  def find(name:String):Option[TalkAgent];

  def allNames:Iterable[String];
    
  def allAgents:Iterable[TalkAgent];
    
}

class SimpleTalkAgentRegistry(m: ConcurrentMap[String,TalkAgent]) extends TalkAgentRegistry
{

  def add(agent:TalkAgent) = m.update(agent.name,agent);
  
  def remove(agent:TalkAgent) = m.remove(agent.name,agent);
 
  def find(name:String) = m.get(name); 
  
  def allNames = m.keys;
 
  def allAgents = m.values;
  
}
