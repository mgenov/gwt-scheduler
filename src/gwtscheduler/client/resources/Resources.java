package gwtscheduler.client.resources;

import gwtscheduler.client.resources.css.CommonCssResourceBundle;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.resources.css.MonthCssResource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import gwtscheduler.client.resources.css.SchedulerCssResource;

/**
 * "Shortcut" class for general resources. Will lazy {@link GWT#create(Class)}
 * the resources as needed on first call.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public final class Resources {

  /** Holds the common css resource */
  private static CommonCssResourceBundle commonCss;

  /**
   * Injects all stylesheets.
   */
  public static void injectAllStylesheets() {
    //TODO: concatenate css and then inject
    StyleInjector.injectAtStart(Resources.dayWeekCss().getText());
    StyleInjector.injectAtStart(Resources.monthCss().getText());
    StyleInjector.injectAtStart(Resources.schedulerCss().getText());
  }

  /**
   * Maybe initializes the css resources.
   */
  private static synchronized void maybeInitialize() {
    if (commonCss == null) {
      commonCss = GWT.create(CommonCssResourceBundle.class);
    }
  }

  /**
   * Gets the day week css resource.
   * @return the day week css resource
   */
  public static final DayWeekCssResource dayWeekCss() {
    maybeInitialize();
    return commonCss.dayWeekCss();
  }

  /**
   * Gets the month css resource.
   * @return the month css resource
   */
  public static final MonthCssResource monthCss() {
    maybeInitialize();
    return commonCss.monthCss();
  }

  /**
   * Gets the month css resource.
   * @return the month css resource
   */
  public static final SchedulerCssResource schedulerCss() {
    maybeInitialize();
    return commonCss.schedulerCss();
  }

}
