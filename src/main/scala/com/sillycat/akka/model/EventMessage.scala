package com.sillycat.akka.model

import org.joda.time.DateTime

/**
 * Created by carl on 8/1/14.
 */
case class EventMessage(id: Long,
  request: String,
  response: String,
  operator: String,
  eventDate: DateTime) extends Serializable
