package com.example.p5.persistence

import org.squeryl._
import java.sql.Timestamp

class TalkMessage( 
		            val id: Long,
		            val from:  String,
		            val to:  String,
		            val message: String,
		            val when: Timestamp)
		         extends KeyedEntity[Long]   
{
		

   lazy val fromUser = TalkDBSchema.fromUsersToMessages.right(this);
   
   lazy val toUser = TalkDBSchema.toUsersToMessages.right(this);
	
}

//case class Message1(
//		) 