package gwtscheduler.client.resources.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.libideas.client.StyleInjector;

/**
 * "Shortcut" class for general resources. Will lazy {@link GWT#create(Class)} the resources as needed on first call.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public final class Resources {

    /** Holds the common css resource */
    private static CommonCssResources commonCss;

    /**
     * Injects all stylesheets.
     */
    public static void injectAllStylesheets() {
        StyleInjector.injectStylesheet(Resources.DayWeekCss().getText());
        StyleInjector.injectStylesheet(Resources.MonthCss().getText());
    }

    /**
     * Maybe initializes the css resources.
     */
    private static synchronized void maybeInitialize() {
        if (commonCss == null) {
            commonCss = GWT.create(CommonCssResources.class);
        }
    }

    /**
     * Gets the common css resource.
     * 
     * @return the common css resource
     */
    public static final DayWeekCss DayWeekCss() {
        maybeInitialize();
        return commonCss.dayWeekCss();
    }

    /**
     * Gets the common css resource.
     * 
     * @return the common css resource
     */
    public static final MonthCss MonthCss() {
        maybeInitialize();
        return commonCss.monthCss();
    }

}
