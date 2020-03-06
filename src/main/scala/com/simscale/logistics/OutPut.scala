package com.simscale.logistics
import akka.actor.typed.ActorRef
import akka.stream.OverflowStrategy
import akka.stream.scaladsl.{ Sink, Source }
import akka.stream.typed.scaladsl.ActorSource

object OutPut {

  trait Protocol
  case class Message(msg: String) extends Protocol
  case object Complete extends Protocol
  case class Fail(ex: Exception) extends Protocol

  val outSource: Source[Protocol, ActorRef[Protocol]] = ActorSource.actorRef[Protocol](completionMatcher = {
    case Complete =>
  }, failureMatcher = {
    case Fail(ex) => ex
  }, bufferSize = 8, overflowStrategy = OverflowStrategy.fail)

}
