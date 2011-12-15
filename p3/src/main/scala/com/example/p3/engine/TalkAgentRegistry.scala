package com.example.p3.engine

import scala.collection.mutable.ConcurrentMap;

trait TalkAgentRegistry {

  def add(agent:TalkAgent):Unit;
  
  def remove(agent:TalkAgent):Unit;
  
  def allNames:Iterable[String];
  
  def find(name:String):Option[TalkAgent]
  
  def foreach(f:(TalkAgent=>Unit)):Unit
  
}


class SimpleTalkAgentRegistry(m: ConcurrentMap[String,TalkAgent]) extends TalkAgentRegistry
{

  def add(agent:TalkAgent) = m.update(agent.name,agent);
  
  def remove(agent:TalkAgent) = m.remove(agent.name,agent);
 
  def foreach(f:(TalkAgent=>Unit)) = m.foreach((x:(String,TalkAgent))=>f(x._2));
  
  def withFilter(f:(TalkAgent=>Boolean)) = m.values.filter(f(_));
 
  def find(name:String) = m.get(name); 
  
  def allNames = m.keys;
  
}
