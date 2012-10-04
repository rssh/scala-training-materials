package com.example.p5.persistence

import org.squeryl._
import org.squeryl.dsl._
import org.squeryl.PrimitiveTypeMode._
import java.sql.Timestamp

class UserProfile(
	 val id: String,
	 val password: Option[String],
	 val info: Option[String],
	 val lastInterlocutor: Option[String],
	 val lastUpdate: Timestamp
	 ) extends KeyedEntity[String]
	 {
	
	//def id = login; 
	
	def this() = this("",Some(""),Some(""),Some(""),new Timestamp(0L));
	
	lazy val sendMessages = TalkDBSchema.fromUsersToMessages.left(this);
	
	lazy val receivedMessages = TalkDBSchema.fromUsersToMessages.left(this);
	
}

object UserProfile
{
  import TalkDBSchema._;
  
  def checkLogin(login:String, password:String):Boolean =
  {
    inTransaction {
    	Session.currentSession.setLogger(
    			  System.out.println(_)
    			);
    	from(userProfiles)(u => where(u.id===login)select(u.password)).headOption match {
    		case Some(p) => p == password
    		case None => 
    			if (password == passwordForNewUsers()) {
    				// create login.
    				val now = new Timestamp(System.currentTimeMillis());
    				userProfiles.insert(new UserProfile(id=login,
    						                            password=Some(passwordForNewUsers()),
    						                            info=None,
    						                            lastInterlocutor=None,
    						                            lastUpdate=now));
    				true;
    			} else {
    				false
    			}
    	}
    }	
  }
  
  def getUser(name:String):Option[UserProfile]=
  {
  	inTransaction {
  		from(listAllUsers)(u => where(u.id===name) select(u) ).headOption
  	}
  }
  
  def listAllUsers:Queryable[UserProfile]=
  {
  	inTransaction {
  		from(userProfiles)(u=>select(u))
  	}
  }
  
  def passwordForNewUsers():String = 
  {
  	val propertyName="defpasswd"
  	inTransaction {
  		from(properties)(p => where (p.name===propertyName)select(p)).headOption match {
  			case Some(x) => x.value
  			case None => val retval="qqq";
  			             properties insert (Property(propertyName, retval));
  			             retval
  		}
  	}
  }
  
}