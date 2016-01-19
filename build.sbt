import _root_.sbt.Keys._
import scoverage.ScoverageKeys._

name := "Akka-Quartz-Scheduler-Application"

organization := "com.knoldus"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases"

val scoverageSettings = Seq(
  coverageExcludedPackages := "<empty>;controllers.javascript*;views.*;router;Reverse.*;assets.*;com.knoldus.actors.LocalActorRefFactory.*.*;com.knoldus.boot.*",
  coverageExcludedFiles := "",
  coverageMinimum := 80,
  coverageFailOnMinimum := true
)


libraryDependencies ++= Seq(
  "com.enragedginger"    %% "akka-quartz-scheduler"   % "1.4.0-akka-2.3.x",
  "org.scalatest"       %% "scalatest"                % "2.2.4"            % "test",
  "com.typesafe.akka"   %  "akka-testkit_2.11"        % "2.3.11",
  "ch.qos.logback"      %  "logback-classic"          % "1.1.3"
)
