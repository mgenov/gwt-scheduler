package gwtscheduler.client.utils;

import gwtscheduler.client.interfaces.Cell;

import java.util.Collection;

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
   * Creates a timer that keeps updating each one of the collection elements
   * regularly with their offset position.
   * @param parent the parent to calculate the offset
   * @param els the collection
   * @param scheduleTime the time for the timer repeating schedule
   */
  public static void trackPosition(final Element parent, final Collection<Cell<Element>> els) {
    for (Cell<Element> cell : els) {
      int[] pos = DOMUtils.getOffset(parent, cell.getCellElement());
      Element el = cell.getCellElement();
      el.setInnerText("left: " + pos[0] + ", top: " + pos[1]);
    }
  }

  /**
   * Adds a border to the element.
   * @param el the element
   */
  public static void addBgColor(Element el) {
    DOM.setStyleAttribute(el, "backgroundColor", "silver");
  }

  /**
   * Adds a border to the element.
   * @param el the element
   */
  public static void addDebugBorder(Element el) {
    DOM.setStyleAttribute(el, "border", "1px solid red");
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
