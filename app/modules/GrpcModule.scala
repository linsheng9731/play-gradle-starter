package modules

import com.google.inject.AbstractModule

/**
  * GrpcModule
  *
  * @author damon lin
  *         2019/3/10
  */
class GrpcModule extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[GrpcBootstrap]).asEagerSingleton()
  }

}
