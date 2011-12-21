package com.example.p5.engine

sealed trait AppMessage { }

case class ClockMessage(val resolutionInMillis:Long) extends AppMessage;

case class AlarmMessage(val millis:Long, val what:String, val whom:String) extends AppMessage;
