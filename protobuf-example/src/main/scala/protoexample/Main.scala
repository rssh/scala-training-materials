package protoexample

import org.freeradius._
import scala.xml._


trait XMLWriter[T] {
  def toXML(t: T): NodeSeq
}



case class Point2(val x: Int, val y: Int)

case class Point3(val x: Int, val y: Int, val z: Int)



object Main
{

 def main(args: Array[String]):Unit =
 {
   Console.println("Hello!")
   
   
 }
 
 
 
 def generatePacket(): RequestData =
 {
   RequestData(protocolVersion=2, 
               state=ProcessingState.POSTAUTH, 
               vps=Vector(ValuePair(attribute=53,stringValue=Some("aa"))))
 }

}
