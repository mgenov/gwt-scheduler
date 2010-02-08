package gwtscheduler.client.modules;

import net.customware.gwt.presenter.client.EventBus;
import gwtscheduler.client.interfaces.navigation.DateGenerator;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.modules.views.UIManager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules( {UIModule.class, AppModule.class})
public interface AppInjector extends Ginjector {

  /**
   * Gets the UI Registry.
   * @return the UI Registry
   */
  UIManager getUIRegistry();

  /**
   * Gets a new date generator instance.
   * @return the date generator
   */
  DateGenerator getDateGenerator();

  /**
   * Gets the application configuration.
   * @return the app config
   */
  AppConfiguration getConfiguration();

  /**
   *Gets the event bus.
   */
  EventBus getEventBus();

  /**
   * Proxy class for acessing injector.
   * @author Miguel Ping
   * @version $Revision: $
   * @since 1.0
   */
  public static class GIN {
    /** ref for injector, lazy init */
    private static AppInjector injector;

    /**
     * Caches and gets the IUIInjector instance.
     * @return the injector instance
     */
    public static synchronized AppInjector getInjector() {
      if (injector == null) {
        injector = GWT.create(AppInjector.class);
      }
      return injector;
    }

  }
}
