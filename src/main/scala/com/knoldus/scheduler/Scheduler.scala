package com.knoldus.scheduler

import com.knoldus.actors.LocalActorRefFactory
import com.knoldus.configuration.Configuration._
import com.knoldus.messages.{Message1, Message2}
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension

trait Scheduler {

  val localActorRefFactory: LocalActorRefFactory

  def schedule: Unit = {
    val _system = localActorRefFactory.system
    val firstReceiver = localActorRefFactory getReceiver NAME_FIRST_ACTOR
    val secondReceiver = localActorRefFactory getReceiver NAME_SECOND_ACTOR

    QuartzSchedulerExtension(_system).schedule(CRON_FIRST_EXPRESSION, firstReceiver, Message1)
    QuartzSchedulerExtension(_system).schedule(CRON_SECOND_EXPRESSION, secondReceiver, Message2)
  }

}

object Scheduler extends Scheduler{
  val localActorRefFactory: LocalActorRefFactory = LocalActorRefFactory
}
