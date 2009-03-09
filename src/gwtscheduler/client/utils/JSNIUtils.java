package gwtscheduler.client.utils;

import com.google.gwt.user.client.Element;

/**
 * All native code goes here.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class JSNIUtils {
    
    /**
     * @param el
     * @return
     */
    public static native Element getOffsetParent(Element el)/*-{
          return el.offsetParent;
    }-*/;
}
