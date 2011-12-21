package com.example.p5.persistence

import org.squeryl._;
import org.squeryl.dsl._;
import org.squeryl.PrimitiveTypeMode._;
import org.squeryl.adapters._;

object TalkDBSchema extends Schema
{
	
	val messages = table[TalkMessage]();
	
	val userProfiles = table[UserProfile]();
	
	val properties = table[Property]();

	
	val fromUsersToMessages = oneToManyRelation(userProfiles,messages).via(
			                     (u,m)=>  u.id === m.xfrom
			                  );
	
	val toUsersToMessages = oneToManyRelation(userProfiles,messages).via(
			                     (u,m)=> u.id === m.xto 
			                  );
	
	def main(args:Array[String]):Unit =
	{
     System.out.println("args are:"+args.toList);
     val toCreate = (args.length > 0 && args(0).equals("--create")) 	
     Class.forName("org.h2.Driver");
     SessionFactory.concreteFactory =
       Some(()=>
                Session.create(
                       java.sql.DriverManager.getConnection("jdbc:h2:test","sa",""),
                       new H2Adapter));

      transaction {
        TalkDBSchema.printDdl;
        if (toCreate) {
        	System.out.println("creating schema");
        	TalkDBSchema.create;
        }
      }
	}
	
}