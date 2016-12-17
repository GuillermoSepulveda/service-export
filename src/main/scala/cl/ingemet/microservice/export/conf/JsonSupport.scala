package cl.ingemet.microservice.export.conf

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

/**
  * Created by gsepulveda on 05-12-16.
  */
trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol  {
}
