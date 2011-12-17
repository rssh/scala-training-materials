package com.example.p4


import com.example.p4.engine._;
import com.example.p4.engine.agents._;

import org.scalatest._;
import org.scalatest.matchers.ShouldMatchers;

class PrivateTalkFeatureSpec extends FeatureSpec
                               with GivenWhenThen
                               with ShouldMatchers
{

	feature("private talk") {
		scenario(" user A and B try to talk with each other " ) {
           given("2 users, registered in registry")
           val user1 = new HumanAgent("u1");
           val user2 = new HumanAgent("u2");
           TalkEngine.registry.add( user1 );
           TalkEngine.registry.add( user2 );
           TalkEngine.chooseAgent("u1","u2");
           TalkEngine.chooseAgent("u2","u1");
           when("message is send to all from one of them")
           val testMessage = "test-message-1"
           TalkEngine.dispatch("u1",testMessage);
           then("others must see one in incoming")
           val u2m = user1.giveMessageFor(user2.name);
           assert(u2m.isDefined);			
		}
	}
	
}