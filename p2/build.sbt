
name:="p2"

version:="0.1"

scalaVersion:="2.9.1"

sbtVersion:="0.11.2"

seq(webSettings :_*)

libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "8.0.4.v20111024" % "container"

libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided"

