package com.knoldus.boot.sbt

import com.knoldus.scheduler.Scheduler

// $COVERAGE-OFF$Disabling highlighting by default until a workaround for https://issues.scala-lang.org/browse/SI-8596 is found
object BootStrap{

  def main(args: Array[String]): Unit = {
    Scheduler.schedule
  }

}
// $COVERAGE-ON$