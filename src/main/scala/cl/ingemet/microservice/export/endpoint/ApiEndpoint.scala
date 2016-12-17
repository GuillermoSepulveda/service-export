package cl.ingemet.microservice.export.endpoint

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives
import cl.ingemet.microservice.export.conf.{Base, JsonSupport}
import cl.ingemet.microservice.export.service.ApiService

import scala.concurrent.ExecutionContext

/**
  * Created by guillermo on 17-12-16.
  */
class ApiEndpoint(implicit val system: ActorSystem,implicit val ec: ExecutionContext) extends Directives with ApiService with JsonSupport with Base {

}
