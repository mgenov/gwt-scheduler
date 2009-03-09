package gwtscheduler.client.utils;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

/**
 * All native code goes here.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class DOMUtils {

    /**
     * Gets the element client width.
     * 
     * @param el the element
     * @return the client width
     */
    public static int getClientWidth(Element el) {
        return DOM.getElementPropertyInt(el, "clientWidth");
    }

    /**
     * Gets the element client height.
     * 
     * @param el the element
     * @return the client height
     */
    public static int getClientHeight(Element el) {
        return DOM.getElementPropertyInt(el, "clientHeight");
    }

    /**
     * @param el
     * @return
     */
    public static Element getOffsetParent(Element el) {
        return JSNIUtils.getOffsetParent(el);
    }
}
