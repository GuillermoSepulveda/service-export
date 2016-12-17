package cl.ingemet.microservice.export.conf.swagger

import java.net.InetAddress

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import cl.ingemet.microservice.export.conf.Base
import cl.ingemet.microservice.export.endpoint.ApiEndpoint
import com.github.swagger.akka._
import com.github.swagger.akka.model.Info
import io.swagger.models.ExternalDocs
import io.swagger.models.auth.BasicAuthDefinition

import scala.reflect.runtime.{universe => ru}

class SwaggerDocService()(implicit val system: ActorSystem,implicit val actorMaterializer: ActorMaterializer) extends SwaggerHttpService with HasActorSystem with Base  {
  override implicit val actorSystem: ActorSystem = system
  override implicit val materializer: ActorMaterializer = actorMaterializer
  override val apiTypes = Seq(ru.typeOf[ApiEndpoint])
  override val host = InetAddress.getLocalHost.getHostAddress+":"+HTTP_PORT
  override val info = Info(version = "1.0")
  override val externalDocs = Some(new ExternalDocs("Core Docs", SWAGGER_CORE_DOCS))
  override val securitySchemeDefinitions = Map("basicAuth" -> new BasicAuthDefinition())
}