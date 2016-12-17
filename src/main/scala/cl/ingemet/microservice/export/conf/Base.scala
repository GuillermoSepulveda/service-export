package cl.ingemet.microservice.export.conf

import com.typesafe.config.{Config, ConfigFactory}

/**
  * Created by PC-Morgoroth on 04-12-2016.
  */
trait Base {
  lazy val config : Config = ConfigFactory.load("application.conf")
  val HTTP_PORT = config.getString("port").toInt
  val SWAGGER_CORE_DOCS = Option(config.getString("swagger_core_doc")).getOrElse("")
  val BREAKER_TIMEOUT = config.getString("breaker.timeout").toInt
}
