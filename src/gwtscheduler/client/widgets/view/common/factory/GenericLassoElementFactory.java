package gwtscheduler.client.widgets.view.common.factory;

import static gwtscheduler.client.resources.css.MainCss.LassoElement;
import gwtscheduler.client.interfaces.LassoElementFactory;
import gwtscheduler.client.interfaces.LassoSubject;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Creates event widgets.
 * @author malp
 */
public class GenericLassoElementFactory extends AbstractMultiCellWidgetFactory implements LassoElementFactory {

  @Override
  public Widget createLassoElement(LassoSubject subject, int[] from, int[] to) {
    Label ew = new Label();
    ew.addStyleName(LassoElement);

    int w = getWidth(subject, to[1] - from[1] + 1);
    int h = getHeight(subject, to[0] - from[0] + 1);
    ew.setPixelSize(w, h);

    return ew;
  }

  /**
   * @param subject
   * @param size
   * @return
   */
  protected int getWidth(LassoSubject subject, int size) {
    assert size > 0 : "Dimension should not be negative";
    return (int) (subject.getWidth() * ((float) size / subject.getColNum()));
  }

  /**
   * @param subject
   * @param size
   * @return
   */
  protected int getHeight(LassoSubject subject, int size) {
    assert size > 0 : "Dimension should not be negative";
    return (int) (subject.getHeight() * ((float) size / subject.getRowNum()));
  }

}
