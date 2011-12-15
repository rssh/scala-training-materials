package com.example.p3.AllMessagesFutureSpec;

import com.example.p3.engine._;
import com.example.p3.engine.agents._;

import org.scalatest._;
import org.scalatest.matchers.ShouldMatchers;

class AllMessagesFeatureSpec extends FeatureSpec
                               with GivenWhenThen
                               with ShouldMatchers
{

  feature("Messages to all are passed to all humans ") {

       scenario("creation of 3 users which connected via 'all'") {
           
           given("3 users, registered in registry")
           val user1 = new HumanAgent("u1");
           val user2 = new HumanAgent("u2");
           val user3 = new HumanAgent("u3");
           TalkEngine.registry.add( user1 );
           TalkEngine.registry.add( user2 );
           TalkEngine.registry.add( user3 );
           when("message is send to all from one of them")
           val testMessage = "test-message-1"
           TalkEngine.processSend("u1","all",testMessage);
           then("2 others must see one in incoming")
           val all = TalkEngine.registry.find("all").get;
           val u2m = all.giveMessageFor(user2.name);
           assert(u2m != None);
           val u3m = all.giveMessageFor(user3.name);
           assert(u3m != None);
           u2m.get should include (testMessage)
           u3m.get should include (testMessage)
           and("with prefix as first login")
           u2m.get should startWith (user1.name)
       }


  }

  feature("Direct talks does not reflects in all ") {
       scenario("x talk with elize") (pending)
  }

}
