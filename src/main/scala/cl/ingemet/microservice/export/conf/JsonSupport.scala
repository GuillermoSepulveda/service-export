package cl.ingemet.microservice.export.conf

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import cl.ingemet.microservice.export.service.ExportObject
import spray.json.DefaultJsonProtocol

/**
  * Created by gsepulveda on 05-12-16.
  */
trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol  {
    implicit val exportObjectFormat = jsonFormat2(ExportObject)
}
