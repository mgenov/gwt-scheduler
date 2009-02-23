package gwtscheduler.client.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

/**
 * Utility class for debugging purposes
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class DebugUtils {

    /**
     * Adds a border to the element.
     * 
     * @param el the element
     */
    public static void addBorder(Element el) {
        if (GWT.isClient()) {
            DOM.setStyleAttribute(el, "border", "1px solid red");
        }
    }
}
