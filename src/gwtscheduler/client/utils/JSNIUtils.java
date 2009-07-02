package gwtscheduler.client.utils;

/**
 * All native code goes here.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class JSNIUtils {

  /**
   * Gets the viewport width.
   * @return the viewport width
   */
  public static native int getViewportWidth() /*-{
         var e = $wnd , a = 'inner';
         if ( !( 'innerWidth' in $wnd ) ) {
         	a = 'client';
         	e = $doc.documentElement || $doc.body;
         }
         return e[ a+'Width' ];
       }-*/;

  /**
   * Gets the viewport height.
   * @return the viewport height
   */
  public static native int getViewportHeight() /*-{
         var e = $wnd , a = 'inner';
         if ( !( 'innerWidth' in $wnd ) ) {
         	a = 'client';
         	e = $doc.documentElement || $doc.body;
         }
         return e[ a+'Height' ];
       }-*/;
}
