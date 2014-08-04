package com.sillycat.akka.actor

import akka.actor.Actor
import com.typesafe.scalalogging.slf4j.Logging
import com.sillycat.akka.model.EventMessage

/**
 * Created by carl on 8/1/14.
 */
class EventMessageActor extends Actor with Logging {

  logger.info("Created a EventMessage Actor")

  def receive = {
    case item: EventMessage => {
      //handle the eventMessage
      logger.debug("I receive one object:" + item)
    }
    case array: List[EventMessage] => {
      //handle the list of message
      logger.debug("I receive array of objects:" + array)
    }
    case _ => logger.error("Received a message I don't understand.")
  }

}