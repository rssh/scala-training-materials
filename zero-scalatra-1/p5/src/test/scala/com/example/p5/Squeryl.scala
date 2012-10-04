package com.example.p5;

import org.squeryl._;
import org.squeryl.adapters._;

object Squeryl
{
  def init:Unit={
   Class.forName("org.h2.Driver");
   SessionFactory.concreteFactory =
       Some(()=>
             Session.create(
                   java.sql.DriverManager.getConnection("jdbc:h2:test","sa",""),
                   new H2Adapter));
  }
}
