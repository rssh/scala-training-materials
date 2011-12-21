
name:="p5"

version:="0.5"

scalaVersion:="2.9.1"

sbtVersion:="0.11.2"

seq(webSettings :_*)

libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "8.0.4.v20111024" % "container"

libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.6.1" % "test"

libraryDependencies += "se.scalablesolutions.akka" % "akka-actor" % "1.2"

libraryDependencies += "org.scala-tools" %% "scala-stm" % "0.4"

libraryDependencies += "org.scala-tools.testing" %% "scalacheck" % "1.9"

libraryDependencies += "org.squeryl" %% "squeryl" % "0.9.5-SNAPSHOT"

libraryDependencies += "com.h2database" % "h2" % "1.2.127"

libraryDependencies += "com.jolbox" % "bonecp" % "0.7.1.RELEASE"





