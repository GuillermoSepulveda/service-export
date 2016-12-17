package cl.ingemet.microservice.export.conf.swagger

import akka.http.scaladsl.server.Directives

/**
  * Created by PC-Morgoroth on 03-12-2016.
  */
trait SwaggerUIEdnpoint extends Directives {
  val swaggerUiRoute =
    path("swagger") { getFromResource("swagger/index.html") } ~
      getFromResourceDirectory("swagger")
}
