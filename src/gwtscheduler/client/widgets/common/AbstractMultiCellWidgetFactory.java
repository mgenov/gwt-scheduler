package gwtscheduler.client.widgets.common;


/**
 * Abstract class for multi cell widgets such as lasso elements and events.
 * @author malp
 */
public abstract class AbstractMultiCellWidgetFactory {

  /**
   * Gets the width for the supplied colspan.
   * @param subject the lasso subject
   * @param colspan the number of cols
   * @return
   */
  protected int getWidth(ComplexGrid subject, int colspan) {
    assert colspan > 0 : "Dimension should not be negative";
    assert colspan <= subject.getColNum() : "Number of columns excede subject's colnum";
    return (int) (subject.getWidth() * ((float) colspan / subject.getColNum()));
  }

  /**
   * Gets the height for the supplied rowspan.
   * @param subject the lasso subject
   * @param rowspan the number of rows
   * @return the height in pixels
   */
  protected int getHeight(ComplexGrid subject, int rowspan) {
    assert rowspan > 0 : "Dimension should not be negative";
    assert rowspan <= subject.getColNum() : "Number of rows excede subject's rownum";
    return (int) (subject.getHeight() * ((float) rowspan / subject.getRowNum()));
  }

}
