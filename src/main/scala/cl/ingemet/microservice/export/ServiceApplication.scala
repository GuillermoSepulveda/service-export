package cl.ingemet.microservice.export

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.RouteConcatenation
import akka.stream.ActorMaterializer
import cl.ingemet.microservice.export.conf.Base
import cl.ingemet.microservice.export.conf.swagger.{SwaggerDocService, SwaggerUIEdnpoint}
import cl.ingemet.microservice.export.endpoint.ApiEndpoint
import cl.ingemet.microservice.orden_compra.conf.CorsSupport

/**
  * Created by PC-Morgoroth on 07-12-2016.
  */
object ServiceApplication extends App with RouteConcatenation with SwaggerUIEdnpoint with CorsSupport with Base{

  implicit val actorSystem = ActorSystem("system")
  implicit val executor = actorSystem.dispatcher
  implicit val actorMaterializer = ActorMaterializer()

  val routes =
    corsHandler(new ApiEndpoint().route) ~
    swaggerUiRoute ~
    new SwaggerDocService().routes

  Http().bindAndHandle(routes, "0.0.0.0", HTTP_PORT)
}
