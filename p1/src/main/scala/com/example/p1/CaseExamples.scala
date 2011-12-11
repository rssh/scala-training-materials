package com.example.p1

object CaseExamples {

  
  	def checkPerson(o:AnyRef):Unit =
	{
      o match {
    	  case Person2(firstName, lastName) => System.err.println("person2, firstName=%s,lastName=%s".format(firstName, lastName));
    	  case p@Person1(_, _) => System.err.println("person1, p="+p.toString);
    	  case _ => System.err.println("Unkown type");
      }
	}
	

  
  
}