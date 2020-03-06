package com.simscale.logistics.actor

import akka.actor.typed.{ActorRef, Behavior, PostStop, Signal}
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import com.simscale.logistics.domain.LogEntry
import com.simscale.logistics.OutPut.Protocol

import scala.concurrent.duration._

object Tracer {

  def apply(outStream: ActorRef[Protocol]): Behavior[Command] =
    Behaviors.setup(context => new Tracer(context,outStream))

  sealed trait Command
  final case class ProcessLog(value: LogEntry) extends Command
  private final case class BuncherTerminated(buncherActor: ActorRef[Buncher.Command], traceId: String) extends Command
}

class Tracer(context: ActorContext[Tracer.Command],outStream: ActorRef[Protocol]) extends AbstractBehavior[Tracer.Command](context) {
  import Tracer._

  private var traceIdToBuncher = Map.empty[String, ActorRef[Buncher.Command]]
  private var logCounter = 0

  context.log.info("Tracer Actor {} started")
  override def onMessage(msg: Command): Behavior[Command] =
    msg match {
      case trackMsg @ ProcessLog(v) =>
        traceIdToBuncher.get(v.trace) match {
          case Some(bActor) =>
            logCounter += 1
            bActor ! Buncher.RecordLogEntry( logCounter.toString,v.trace,v)
          case None =>
            context.log.info("Creating trace actor for {}", trackMsg.value.trace)
            val newTActor = context.spawn(Buncher(outStream,15.second,v.trace), s"Bunch-${v.trace}")
            context.watchWith(newTActor, BuncherTerminated(newTActor, v.trace))
            traceIdToBuncher += v.trace -> newTActor
            newTActor ! Buncher.RecordLogEntry( logCounter.toString,v.trace,v)
        }
        this
      case BuncherTerminated( _, tId) =>
        context.log.info("Trace actor for {} has been terminated", tId)
        traceIdToBuncher -= tId
        this
    }

  override def onSignal: PartialFunction[Signal, Behavior[Command]] = {
    case PostStop =>
      context.log.info("Trace Manager stopped")
      this
  }
}

