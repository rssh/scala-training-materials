package com.example.p1

class Person2(val firstName:String, val lastName: String);


object Person2
{
  def apply(firstName:String,lastName:String) = new Person2(firstName,lastName);

  def unapply(p:Person2):Option[Pair[String,String]] =
                       Some((p.firstName,p.lastName))
};
