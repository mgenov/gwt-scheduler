package gwtscheduler.client.utils;

/**
 * All native code goes here.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class DOMUtils {

  /**
   * Gets the viewport width and height.
   * 
   * @return the viewport dimensions
   * @see http://andylangton.co.uk/articles/javascript/get-viewport-size-javascript
   * @see http://snipplr.com/view/5896/get-browser-viewport-width-and-height/
   */
  public static int[] getViewportDimensions() {
    return new int[] {
        JSNIUtils.getViewportWidth(), JSNIUtils.getViewportHeight()};
  }
}
