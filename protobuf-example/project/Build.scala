import sbt._
import Keys._
import scalabuff.ScalaBuffPlugin._

object MyBuild extends Build {


  lazy val root = Project("main", file("."), 
                          settings = Defaults.defaultSettings ++ 
                                     scalabuffSettings ++
                                     Seq(scalaVersion := "2.10.0",
                                         name := "protobuf-example",
                                         version := "0.1",
                                         libraryDependencies ++= Seq(
                                             "net.databinder.dispatch" %% "dispatch-core" % "0.10.0"
                                         )
                                     )
                         ).configs(ScalaBuff) 

}
