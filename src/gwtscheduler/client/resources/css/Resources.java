package gwtscheduler.client.resources.css;

import com.google.gwt.core.client.GWT;

/**
 * "Shortcut" class for general resources. Will lazy {@link GWT#create(Class)} the resources as needed on first call.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public final class Resources {

    /** holds the common css resource */
    private static CommonCssResources commonCss;

    /**
     * Gets the common css resource.
     * 
     * @return the common css resource
     */
    public static synchronized final CommonCss CommonCss() {
        if (commonCss == null) {
            commonCss = GWT.create(CommonCssResources.class);
        }
        return commonCss.css();
    }

}
