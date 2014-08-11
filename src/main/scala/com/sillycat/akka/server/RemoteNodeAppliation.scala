package com.sillycat.akka.server

import akka.actor.{ Props, ActorSystem }
import akka.kernel.Bootable
import com.sillycat.akka.actor.EventMessageActor
import com.typesafe.config.ConfigFactory

class RemoteNodeAppliation extends Bootable {

  val actorSystem = ActorSystem("EventServiceSystem", ConfigFactory.load().getConfig("RemoteSys"))

  def startup = {
    actorSystem.actorOf(Props[EventMessageActor], name = "remoteActor")
  }

  def shutdown = {
    actorSystem.shutdown()
  }

}
