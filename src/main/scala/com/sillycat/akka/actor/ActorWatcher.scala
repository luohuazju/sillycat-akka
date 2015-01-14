package com.sillycat.akka.actor

import akka.actor.{ Terminated, Actor, ActorRef }
import com.typesafe.scalalogging.slf4j.Logging

/**
 * Created by carl on 1/14/15.
 */
class ActorWatcher(watched: ActorRef) extends Actor with Logging {

  context.watch(watched)

  def receive = {
    case Terminated(watched) => {
      logger.info("The watched actor was terminated: " + watched.toString())
      context.system.shutdown()
    }
    case _ => logger.info("ActorWatcher got a message not intended for it!")
  }
}
