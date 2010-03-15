package gwtscheduler.client.modules;

import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.modules.config.DefaultAppConfiguration;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * General application module. Change at will.
 * @author malp
 */
public class AppModule extends AbstractGinModule {

  @Override
  protected void configure() {
//    bind(AppConfiguration.class).to(DefaultAppConfiguration.class).in(Singleton.class);
  }
}
