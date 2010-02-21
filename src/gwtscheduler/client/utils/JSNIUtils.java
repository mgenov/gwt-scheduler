package gwtscheduler.client.utils;

/**
 * All native code goes here.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
class JSNIUtils {

  /**
   * @see http://www.alexandre-gomes.com/?p=115
   * @return
   */
  static native int getScrollbarWidth() /*-{
    var inner = document.createElement('p');
    inner.style.width = '100%';
    inner.style.height = '200px';

    var outer = document.createElement('div');
    outer.style.position = 'absolute';
    outer.style.top = '0px';
    outer.style.left = '0px';
    outer.style.visibility = 'hidden';
    outer.style.width = '200px';
    outer.style.height = '150px';
    outer.style.overflow = 'hidden';
    outer.appendChild (inner);

    document.body.appendChild (outer);
    var w1 = inner.offsetWidth;
    outer.style.overflow = 'scroll';
    var w2 = inner.offsetWidth;
    if (w1 == w2) w2 = outer.clientWidth;

    document.body.removeChild (outer);

    return (w1 - w2);
  }-*/;

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
  static native int getViewportHeight() /*-{
    var e = $wnd , a = 'inner';
    if ( !( 'innerWidth' in $wnd ) ) {
    a = 'client';
    e = $doc.documentElement || $doc.body;
    }
    return e[ a+'Height' ];
  }-*/;
}
