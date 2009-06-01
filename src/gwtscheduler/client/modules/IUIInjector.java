package gwtscheduler.client.modules;

import gwtscheduler.client.modules.views.IUIRegistry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(UIModule.class)
public interface IUIInjector extends Ginjector {

  /**
   * Gets the UI Registry.
   * @return the UI Registry
   */
  IUIRegistry getUIRegistry();

  /**
   * Proxy class for acessing injector.
   * @author Miguel Ping
   * @version $Revision: $
   * @since 1.0
   */
  public static class GIN {
    /** ref for injector, lazy init */
    private static IUIInjector injector;

    /**
     * Caches and gets the IUIInjector instance.
     * @return the injector instance
     */
    public static synchronized IUIInjector getInjector() {
      if (injector == null) {
        injector = GWT.create(IUIInjector.class);
      }
      return injector;
    }

  }

}
