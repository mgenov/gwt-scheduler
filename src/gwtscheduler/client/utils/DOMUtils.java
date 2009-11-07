package gwtscheduler.client.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

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
    return new int[] {
        JSNIUtils.getViewportWidth(), JSNIUtils.getViewportHeight()};
  }

  /**
   * Gets the offset left and top of a child in relation to a parent element.
   * @param parent the parent element
   * @param child the child element
   * @return an array with the left and top offsets
   */
  public static int[] getOffset(com.google.gwt.dom.client.Element parent,
      com.google.gwt.dom.client.Element child) {
    assert parent != child : "The parent element is the same as the child element.";
    assert parent != null : "The parent element cannot be null";
    assert parent.isOrHasChild(child) : "The supplied element is not a child of the parent.";

    int left = child.getOffsetLeft(), top = child.getOffsetTop();
    com.google.gwt.dom.client.Element nextParent = child.getParentElement();
    //using > while(nextParent != parent...) doesn't work
    while (nextParent != null && parent.isOrHasChild(nextParent)) {
      left += nextParent.getOffsetLeft();
      top += nextParent.getOffsetTop();

      nextParent = nextParent.getParentElement();
    }
    if (nextParent == null) {
      GWT.log("DOMUtils: offset was traced to a null parent node!", null);
    }
    return new int[] {left, top};
  }

  //http://snippets.dzone.com/posts/show/2995 //get scrollbar size

  /**
   * Wraps an element onto a widget.
   * @param el the element
   * @return the widget
   */
  public static Widget wrapElement(final Element el) {
    return new Widget() {
      {
        setElement(el);
      }
    };
  }
}
