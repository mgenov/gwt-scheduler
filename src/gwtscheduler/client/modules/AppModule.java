package gwtscheduler.client.modules;

import gwtscheduler.client.modules.config.DefaultAppConfiguration;
import gwtscheduler.client.modules.config.AppConfiguration;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * General application module.
 * @author malp
 */
public class AppModule extends AbstractGinModule {

  @Override
  protected void configure() {
    bind(AppConfiguration.class).to(DefaultAppConfiguration.class).in(Singleton.class);
  }
}
