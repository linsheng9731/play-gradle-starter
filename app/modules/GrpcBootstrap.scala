package modules

import javax.inject.Inject
import akka.actor.ActorSystem
import akka.http.scaladsl.UseHttp2.{Always, Never}
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.{Http2, HttpConnectionContext}
import akka.stream.{ActorMaterializer, Materializer}
import example.myapp.helloworld.grpc._
import service.grpc.GreeterServiceImpl

import scala.concurrent.{ExecutionContext, Future}


/**
  * GrpcBootstrap
  *
  * @author damon lin
  *         2019/3/10
  */
class GrpcBootstrap @Inject()(system: ActorSystem) {

  implicit val sys: ActorSystem = system
  implicit val mat: Materializer = ActorMaterializer()
  implicit val ec: ExecutionContext = sys.dispatcher

  val services: Seq[HttpRequest => Future[HttpResponse]] = Seq(
    GreeterServiceHandler(new GreeterServiceImpl())
  )

  println("init akka grpc successful!")

  // Bind service handler servers to localhost:8080
  val bindings = Future.sequence {
    services
      .zip(Seq(8080))
      .map {
        case (service, port) =>
          Http2().bindAndHandleAsync(
            service,
            interface = "127.0.0.1",
            port = port,
            connectionContext = HttpConnectionContext(http2 = Always))
      }
  }

  // report successful binding
  bindings.foreach { bs =>
    bs.foreach { binding =>
      println(s"gRPC server bound to: ${binding.localAddress}")
    }
  }

  bindings

}
