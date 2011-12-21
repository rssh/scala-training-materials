package com.example.p5.engine

import com.example.p5.persistence._;

import org.squeryl._;
import org.squeryl.dsl._;
import org.squeryl.PrimitiveTypeMode._;
import java.sql.Timestamp;

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

  def add(agent:TalkAgent): Unit = {
  	atomic ( implicit txn => m.update(agent.name,agent) );
  	inTransaction {
  		import TalkDBSchema._;
  		from(userProfiles)(u => where(u.id===agent.name) 
  				                select(u)) headOption match {
  			case Some(x) => /* do nothing */
  			case None =>
  				userProfiles.insert(new UserProfile(agent.name,None,None,None,
  						                  new Timestamp(System.currentTimeMillis())));
  		}
  	}
  }
  
  def remove(agent:TalkAgent) = atomic (implicit txn => m.remove(agent.name) );
 
  def find(name:String) = atomic (implicit txn => m.get(name) ); 
  
  def allNames = m.single.keys;
 
  def allAgents = m.single.values;
 
  val m = TMap[String,TalkAgent]();
  
}


