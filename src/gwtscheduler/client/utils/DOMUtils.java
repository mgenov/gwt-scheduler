package gwtscheduler.client.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

/**
 * All native code goes here.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class DOMUtils {

  /**
   * Gets the viewport width and height.
   * @return the viewport dimensions
   * @see http 
   *      ://andylangton.co.uk/articles/javascript/get-viewport-size-javascript
   * @see http://snipplr.com/view/5896/get-browser-viewport-width-and-height/
   */
  public static int[] getViewportDimensions() {
    return new int[] {JSNIUtils.getViewportWidth(), JSNIUtils.getViewportHeight()};
  }

  /**
   * Gets the offset left and top of a child in relation to a parent element.
   * @param parent the parent element
   * @param child the child element
   * @return an array with the left and top offsets
   */
  public static int[] getOffset(Element parent, Element child) {
    assert DOM.isOrHasChild(parent, child) : "The supplied element is not a child of the parent.";
    assert parent != child : "The parent element is the same as the child element.";
    int left = 0, top = 0;
    Element nextParent = (Element) child.getOffsetParent();
    while (nextParent != parent && nextParent != null) {
      left += nextParent.getOffsetLeft();
      top += nextParent.getOffsetTop();
      nextParent = (Element) nextParent.getOffsetParent();
    }
    if (nextParent == null) {
      GWT.log("DOMUtils: offset was traced to a null parent node!", null);
    }
    return new int[] {left, top};
  }
}
