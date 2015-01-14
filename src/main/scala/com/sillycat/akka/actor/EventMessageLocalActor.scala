package com.sillycat.akka.actor

import com.typesafe.scalalogging.slf4j.Logging
import akka.actor.Actor
import akka.actor.{ ActorLogging, Actor }
import akka.util.Timeout
import akka.pattern.ask
import scala.concurrent.Await
import scala.concurrent.duration._
import com.sillycat.akka.model.EventMessage

/**
 * Created by carl on 8/11/14.
 */
class EventMessageLocalActor(path: String) extends Actor with Logging {

  val remoteActor = context.actorSelection(path)

  implicit val timeout = Timeout(5 seconds)

  def receive = {
    case message: EventMessage => {
      //val future = (remoteActor ? message).mapTo[EventMessage]
      //this will wait and see the response
      //val result = Await.result(future, timeout.duration)
      //logger.info("Message received from server -> {}", result)
      remoteActor ! message
    }

    case message: String => {
      remoteActor ! message
    }
  }

}
