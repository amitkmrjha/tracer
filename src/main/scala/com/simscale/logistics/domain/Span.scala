package com.simscale.logistics.domain

import java.time.Instant

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.syntax._
import io.circe.{Decoder, Encoder}

case class Span(service:String, start: Instant,end:Instant,calls:List[Span] = List.empty,span:String)
object Span {


  implicit val basicProjectDecoder: Decoder[Span] = deriveDecoder
  implicit val basicProjectEncoder: Encoder[Span] = deriveEncoder

  /**
   * Form a linked forest, given a function for determining child links.
   */
  private def link(node: LogEntry, logs:List[LogEntry]): Option[Span] = {
    import scala.collection.mutable
    val seen = mutable.HashSet.empty[LogEntry]
    val completed = mutable.HashSet.empty[LogEntry]
    val roots = mutable.Map.empty[LogEntry, Span]
    def visit(node: LogEntry): Unit = {
      if (!seen(node)) {
        seen(node) = true;
        val linked = getLinkedChild(node,logs)
        linked foreach visit
        val children = linked flatMap roots.remove
        roots += node -> Span(node.serviceName,node.startTimeStamp,node.endTimeStamp,children,node.span)
        completed(node) = true
      } else if (!completed(node)) {
        throw new RuntimeException("Cycle found at: " + node)
      }
    }
     visit(node)
    roots.values.toList.headOption
  }

  def toJsonLogEntry(logs:List[LogEntry]):String = {
    getRootEntry(logs) match {
      case Some(rootE) =>processTree(rootE,logs)
      case None => OrphanTrace(logs.head.trace).asJson.toString()
    }
  }


 private def processTree(root:LogEntry, logs:List[LogEntry]):String = {
   link(root,logs) match{
     case Some(s) => ValidTrace(root.trace,s).asJson.toString()
     case None => s"Unable to determine trace path for ${root.trace}"
   }
  }

  private def getLinkedChild(entry:LogEntry,logs:List[LogEntry]):List[LogEntry] = {
    logs.filter(l => l.callerSpan == entry.span)
  }

  private def getRootEntry(logs:List[LogEntry]):Option[RootEntry] = {
    logs.collectFirst {
      case r:RootEntry=> r
    } match {
      case Some(root) => Option(root)
      case _ =>None
    }
  }
}
