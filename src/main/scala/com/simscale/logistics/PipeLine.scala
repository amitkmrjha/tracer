package com.simscale.logistics

import akka.NotUsed
import akka.actor.typed.{ActorRef, ActorSystem}
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.ActorRef
import akka.stream.OverflowStrategy
import akka.stream.scaladsl.{Sink, Source}
import akka.stream.typed.scaladsl.ActorSource
import com.simscale.logistics.OutPut.Message
import com.simscale.logistics.actor.Tracer.ProcessLog
import com.simscale.logistics.actor.{Buncher, Tracer}
import com.simscale.logistics.domain.LogEntry


object PipeLine extends App {
  implicit val system: ActorSystem[_] = ActorSystem(Behaviors.empty, "PipeLine")
  val outPutRef = OutPut.outSource.collect {case Message(msg) => msg
    }.to(Sink.foreach(x => println(s"${x}"))).run()

 val managerActor =  system.systemActorOf(Tracer(outPutRef),"tracer")
 for (ln <- scala.io.Source.stdin.getLines) {
   val line = ln.trim
   if(line.size>0)
  managerActor ! ProcessLog(LogEntry.toLogEntry(line))
 }

}


