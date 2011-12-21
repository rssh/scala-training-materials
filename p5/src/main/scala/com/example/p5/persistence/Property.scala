package com.example.p5.persistence

import org.squeryl._

class Property(val name:String, val value:String) extends KeyedEntity[String]
{
  def id = name ;	
}