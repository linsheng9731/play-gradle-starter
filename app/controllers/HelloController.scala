package controllers

import akka.actor.ActorSystem
import akka.grpc.GrpcClientSettings
import akka.stream.ActorMaterializer
import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import example.myapp.helloworld.grpc.{GreeterService, GreeterServiceClient, HelloRequest}

/**
  * HelloController
  *
  * @author damon lin
  *         2019/3/8
  */
@Singleton
class HelloController @Inject()(
                                cc: ControllerComponents
                               )(implicit assetsFinder: AssetsFinder) extends AbstractController(cc) {

  def hello(): Action[AnyContent] = Action.async { _ ⇒

    implicit val sys = ActorSystem("HelloWorldClient")
    implicit val mat = ActorMaterializer()
    implicit val ec = sys.dispatcher

    // Take details how to connect to the service from the config.
    val clientSettings = GrpcClientSettings.fromConfig(GreeterService.name)
    // Create a client-side stub for the service
    val finalSetting = clientSettings.withTls(false).withServiceProtocol("tcp")
    val client: GreeterService = GreeterServiceClient(finalSetting)

    sys.log.info("Performing request")
    val reply = client.sayHello(HelloRequest("Alice"))
    reply.map(r => Ok(r.message))

  }

  def index()=  Action {
    Ok(views.html.index("Your new application is ready."))
  }

}