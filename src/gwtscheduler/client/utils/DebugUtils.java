package gwtscheduler.client.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

/**
 * Utility class for debugging purposes
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class DebugUtils {

  /**
   * Adds a border to the element.
   * @param el the element
   */
  public static void addBgColor(Element el) {
    if (GWT.isClient()) {
      DOM.setStyleAttribute(el, "backgroundColor", "silver");
    }
  }

  /**
   * Adds a border to the element.
   * @param el the element
   */
  public static void addDebugBorder1(Element el) {
    if (GWT.isClient()) {
      DOM.setStyleAttribute(el, "border", "1px solid red");
    }
  }

  /**
   * Sets the inner text and aligns to the right
   * @param txt the text to set
   * @param element the element to set
   */
  public static void textRight(String txt, Element element) {
    element.setInnerText(txt);
    DOM.setStyleAttribute(element, "textAlign", "right");
  }
}
