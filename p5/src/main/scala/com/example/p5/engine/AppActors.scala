package com.example.p5.engine

import akka.actor._;
import akka.config.Supervision._;
import akka.util.duration._;

import agents.AlarmActor;

object AppActors {

  def init: Unit = {
   // TODO: add cleanup handler
    val alarmActor = Actor.actorOf[AlarmActor];
    val factory = SupervisorFactory(
                    SupervisorConfig(
                        OneForOneStrategy(List(classOf[Exception]), 10, 10000),
                        Supervise(alarmActor,Permanent,false)::Nil	
                    )
                  );
    val supervisor = factory.newInstance;
    supervisor.start
    Scheduler.schedule(alarmActor,ClockMessage(1000),2,1,1.second.unit)
    optSupervisor = Some(supervisor)
  }
  
  def shutdown: Unit = {
    optSupervisor.foreach(_.shutdown);
    optSupervisor=None;
  }
  
  var optSupervisor: Option[Supervisor] = None;
}
