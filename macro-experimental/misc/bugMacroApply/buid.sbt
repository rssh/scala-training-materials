name:="bugMacroApply"

organization:="com.github.rssh"

scalaVersion := "2.11.0-SNAPSHOT"

scalacOptions ++= Seq("-unchecked","-deprecation")

resolvers += Resolver.sonatypeRepo("snapshots")

addCompilerPlugin("org.scala-lang.plugins" % "macro-paradise" % "2.0.0-SNAPSHOT" cross CrossVersion.full)

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.0-SNAPSHOT"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"

version:="0.0.1"

