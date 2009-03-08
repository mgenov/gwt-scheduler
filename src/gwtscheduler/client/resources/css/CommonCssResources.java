package gwtscheduler.client.resources.css;

import com.google.gwt.libideas.resources.client.ImmutableResourceBundle;

/**
 * Defines common css resources.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface CommonCssResources extends ImmutableResourceBundle {

    /**
     * This is the main stylesheet.
     * 
     * @return the main stylesheet object
     */
    @Resource("gwtscheduler/client/resources/css/common.css")
    public CommonCss css();
}
