package cl.ingemet.microservice.export.endpoint

import javax.ws.rs.Path

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives
import akka.pattern.CircuitBreaker
import cl.ingemet.microservice.export.conf.{Base, JsonSupport}
import cl.ingemet.microservice.export.service.{ApiService, ExportObject}
import io.swagger.annotations._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.util.{Failure, Success}

/**
  * Created by guillermo on 17-12-16.
  */
@Api(value = "service/export/", description = "Microservicio de Exportacion de Documentos.")
@Path("service/ordencompra/")
class ApiEndpoint(implicit val system: ActorSystem,implicit val ec: ExecutionContext) extends Directives with ApiService with JsonSupport with Base {

  val breaker = new CircuitBreaker(system.scheduler,
    maxFailures = 1,
    callTimeout = BREAKER_TIMEOUT.seconds,
    1.second
  )

  val route = pathPrefix("service") { pathPrefix("export") {
    pdf
  }}

  @ApiOperation(value = "Genera Archivo PDF", notes = "", nickname = "generate PDF", httpMethod = "POST")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "body", value = "objeto requerido", required = true,
      dataType = "cl.ingemet.microservice.export.service.ExportObject", paramType = "body")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "Retorna PDF", response = classOf[String]),
    new ApiResponse(code = 500, message = "Internal server error")
  ))
  def pdf = post {
    requestEntityPresent {
      entity(as[ExportObject]) { body =>
        complete(body)
      }
    }
  }
}
