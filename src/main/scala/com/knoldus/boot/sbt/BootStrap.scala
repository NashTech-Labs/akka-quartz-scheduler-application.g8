package com.knoldus.boot.sbt

import com.knoldus.scheduler.Scheduler

object BootStrap{

  def main(args: Array[String]): Unit = {
    Scheduler.schedule
  }

}
