package com.simscale.logistics.actor

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.{Behaviors, TimerScheduler}
import com.simscale.logistics.OutPut.{Message, Protocol}
import com.simscale.logistics.domain.{LogEntry, Span}

import scala.concurrent.duration.FiniteDuration

object Buncher {

  sealed trait Command

  final case class RecordLogEntry(requestId: String, traceId: String,value: LogEntry) extends Command

  final case class Batch(messages: String)

  private case object Timeout extends Command

  private case object TimerKey

  def apply(target: ActorRef[Protocol], after: FiniteDuration,traceId:String): Behavior[Command] = {
    Behaviors.withTimers(timers => new Buncher(timers, target, after,traceId).idle())
  }
}

class Buncher(
               timers: TimerScheduler[Buncher.Command],
               target: ActorRef[Protocol],
               after: FiniteDuration,
               traceId:String) {
  import Buncher._

  private def idle(): Behavior[Command] = {
    Behaviors.receiveMessage[Command] { message =>
      timers.startSingleTimer(TimerKey, Timeout, after)
      active(Vector(message))
    }
  }
  private def shutDown(): Behavior[Command] = {
    Behaviors.stopped
  }

  def active(buffer: Vector[Command]): Behavior[Command] = {
    Behaviors.receiveMessage[Command] {
      case Timeout =>
        target ! Message(traceTree(getLogRecord(buffer)))
        shutDown()
      case m @ RecordLogEntry(rId, `traceId`,v) =>
        val newBuffer = buffer :+ m
        timers.startSingleTimer(TimerKey, Timeout, after)
          active(newBuffer)
      case m @ RecordLogEntry(rId, tId,v) =>
        active(buffer)
    }
  }

  private def getLogRecord(buffer: Vector[Command]) : List[RecordLogEntry] = {
    buffer.collect{case x:RecordLogEntry => x }.toList
  }

  private def traceTree(logs:List[RecordLogEntry]):String = {
    val logsE = logs.map(x => x.value)
    val json = Span.toJsonLogEntry(logsE)
    //println(s"${json}")
    json
  }
}