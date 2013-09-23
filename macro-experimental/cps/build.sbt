name:="stringtag"

organization:="com.github.rssh"

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-unchecked","-deprecation")

autoCompilerPlugins := true

addCompilerPlugin("org.scala-lang.plugins" % "continuations" % "2.10.2")

scalacOptions += "-P:continuations:enable"

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.10.2"

version:="0.0.1"

