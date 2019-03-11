package modules

import com.google.inject.AbstractModule

/**
  * ConfigModule
  *
  * @author damon lin
  *         2019/3/11
  */
class ConfigModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[PlayConfig]).asEagerSingleton()
  }
}