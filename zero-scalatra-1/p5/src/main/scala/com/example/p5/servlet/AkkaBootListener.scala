package com.example.p5.servlet

import akka.util._;
import akka.actor._;  
//  here only local
//import akka.remote._;

import javax.servlet._;
import com.example.p5.engine._;


class AkkaBootListener extends ServletContextListener
{

 lazy val loader = new AkkaLoader;

  def contextInitialized(e: ServletContextEvent):Unit =
  {
    loader.boot(true, new BootableActorLoaderService { } );
    AppActors.init;
  }

  def contextDestroyed(e: ServletContextEvent):Unit =
  {
    AppActors.shutdown;
    loader.shutdown;
  }
  
  
  
}
