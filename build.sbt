// BASIC METADATA //

organization := "com.versal"

name := "kawauso"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.1"

// FIREOTTER //

resolvers += "sonatype-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies += "com.versal" %% "fireotter" % "0.1.0-SNAPSHOT" % "test"

// SCALATEST //

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"
