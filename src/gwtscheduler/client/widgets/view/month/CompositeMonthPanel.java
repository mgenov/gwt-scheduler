package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.decoration.HasMultipleDecorables;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.widgets.ViewportPanel;
import gwtscheduler.client.widgets.view.common.cell.BaseCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.gen2.table.override.client.FlexTable;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Defines the composite month view.
 */
public class CompositeMonthPanel extends Composite implements HasMultipleDecorables<Element> {

  /** static ref to css */
  protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /** widget impl */
  protected VerticalPanel impl;
  /** month view instance */
  protected MonthPanel monthView;
  /** top view cells */
  protected List<Cell<Element>> topLabels;

  /**
   * Default constructor.
   */
  public CompositeMonthPanel() {
    impl = new VerticalPanel();

    monthView = new MonthPanel();
    Widget topView = createTopView();

    final ViewportPanel vp = new ViewportPanel();
    DOM.setStyleAttribute(vp.getElement(), "overflowY", "hidden");
    vp.add(monthView, monthView);

    impl.add(topView);
    impl.add(vp);
    initWidget(impl);
    // we'll delegate the resize to the viewport panel
    addHandler(new WidgetResizeHandler() {
      public void onResize(WidgetResizeEvent event) {
        vp.doDeferredResize();
      }
    }, WidgetResizeEvent.getType());
  }

  /**
   * Creates the top view.
   * @return the top view widget
   */
  private Widget createTopView() {
    FlexTable g = new FlexTable();
    g.addStyleName(CSS.genericContainer());
    g.setWidth("100%");

    topLabels = new ArrayList<Cell<Element>>(7);
    for (int i = 0; i < 7; i++) {
      Cell<Element> cell = new BaseCell(0, i);
      cell.getCellElement().setInnerHTML(0 + ", " + i);//debug
      topLabels.add(cell);

      g.setElement(0, i, cell.getCellElement());
      g.getCellFormatter().setWidth(0, 0, ((float) 100 / 7) + "%");
      g.getFlexCellFormatter().setHorizontalAlignment(0, i, HasHorizontalAlignment.ALIGN_CENTER);
    }
    return g;
  }

  public List<Cell<Element>> getDaysDecorableElements() {
    return Collections.unmodifiableList(topLabels);
  }

  public List<Cell<Element>> getMultipleDecorableElements() {
    return Collections.unmodifiableList(monthView.getMainElements());
  }

  public List<Cell<Element>> getWithinDayDecorableElements() {
    return null;
  }

  /**
   * Shows a given number of rows, hiding the others.
   * @param rowNum the number of rows
   */
  public void showRows(int rowNum) {
    monthView.showRows(rowNum);
  }

}
