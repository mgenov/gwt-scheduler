package gwtscheduler.client.utils;

/**
 * All native code goes here.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class JSNIUtils {
    /**
     * This code is shamelessly borrowed from http://code.google.com/p/gwtspreadsheetinput/source/browse/trunk/gwtspreadsheetinput-gwt/src/main/java/com/gwtspreadsheetinput/gwt/client/DynamicGrid.java#359. 
     * Which was in turn shamelessly borrowed from http://www.alexandre-gomes.com/?p=115.
     */
    public static native int getScrollBarWidth() /*-{
        var inner = document.createElement('p');
        inner.style.width = "100%";
        inner.style.height = "200px";
    
        var outer = document.createElement('div');
        outer.style.position = "absolute";
        outer.style.top = "-1000px";
        outer.style.left = "-1000px";
        outer.style.visibility = "hidden";
        outer.style.width = "200px";
        outer.style.height = "150px";
        outer.style.overflow = "hidden";
        outer.appendChild (inner);
    
        document.body.appendChild (outer);
        var w1 = inner.offsetWidth;
        outer.style.overflow = 'scroll';
        var w2 = inner.offsetWidth;
        if (w1 == w2) w2 = outer.clientWidth;
        
        document.body.removeChild (outer);
        return (w1 - w2);

}-*/;
}
