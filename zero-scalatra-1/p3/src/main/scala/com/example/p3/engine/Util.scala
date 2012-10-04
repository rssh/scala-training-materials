package com.example.p3.engine


import scala.collection.mutable.ConcurrentMap
import java.util.concurrent.{ ConcurrentHashMap => JavaConcurrentHashMap }
import scala.collection.JavaConversions._


/**
 * utility object 
 */
object Util {

  def newConcurrentHashMap[K,V]: ConcurrentMap[K,V] = new JavaConcurrentHashMap[K,V]();
  
}