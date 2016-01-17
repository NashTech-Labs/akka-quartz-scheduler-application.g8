package com.knoldus.boot.sbt

import akka.actor.ActorSystem
import com.knoldus.scheduler.Scheduler
import com.typesafe.config.ConfigFactory

object BootStrap{

  def main(args: Array[String]): Unit = {
    val _system: ActorSystem = ActorSystem.create("KnoldusScheduler", ConfigFactory.load)
    Scheduler.schedule(_system)
  }

}
