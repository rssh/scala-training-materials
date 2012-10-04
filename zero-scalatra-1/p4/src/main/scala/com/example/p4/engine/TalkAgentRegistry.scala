package com.example.p4.engine

import scala.concurrent.stm._;

trait TalkAgentRegistry {

  def add(agent:TalkAgent):Unit;
  
  def remove(agent:TalkAgent):Unit;
  
  def find(login :TalkEngine#NameType ):Option[TalkAgent];

  def allNames:Iterable[String];
    
  def allAgents:Iterable[TalkAgent];
    
}

class SimpleTalkAgentRegistry extends TalkAgentRegistry
{

  def add(agent:TalkAgent) = atomic ( implicit txn => m.update(agent.name,agent) );
  
  def remove(agent:TalkAgent) = atomic (implicit txn => m.remove(agent.name) );
 
  def find(name:String) = atomic (implicit txn => m.get(name) ); 
  
  def allNames = m.single.keys;
 
  def allAgents = m.single.values;
 
  val m = TMap[String,TalkAgent]();
  
}


