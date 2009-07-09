package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.decoration.HasMultipleDecorables;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.DOMUtils;
import gwtscheduler.client.utils.DebugUtils;
import gwtscheduler.client.widgets.AdaptableWindowPanel;
import gwtscheduler.client.widgets.view.common.cell.BaseCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.gen2.table.override.client.FlexTable;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Defines the composite month view.
 */
public class MonthCalendar extends Composite implements HasMultipleDecorables<Element>, SelectionHandler<Integer> {

  /** static ref to css */
  protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /** widget impl */
  protected VerticalPanel impl;
  /** month view instance */
  protected MonthPanel monthView;
  /** top view cells */
  protected List<Cell<Element>> topLabels;

  /** adaptable viewport */
  private AdaptableWindowPanel vp;

  /**
   * Default constructor.
   */
  public MonthCalendar() {
    impl = new VerticalPanel();

    monthView = new MonthPanel();
    Widget topHeader = createTopHeader();

    vp = new AdaptableWindowPanel();
    DOM.setStyleAttribute(vp.getElement(), "overflowY", "hidden");
    DOM.setStyleAttribute(vp.getElement(), "position", "relative");
    vp.add(monthView, monthView);

    impl.add(topHeader);
    impl.add(vp);
    initWidget(impl);

    // we'll delegate the resize to the viewport panel
    addHandler(new WidgetResizeHandler() {
      public void onResize(WidgetResizeEvent event) {
        vp.doDeferredResize();
      }
    }, WidgetResizeEvent.getType());
    //also need the handler for the selection
    addHandler(this, SelectionEvent.getType());
  }

  @Override
  public void onSelection(SelectionEvent<Integer> event) {
    //TODO fixme this is not working properly
    GWT.log("onSelection called - good time to make some calcs", null);
    Label l = new Label("month");
    vp.add(l);

    Cell<Element> cell = getContentDecorableElements().get(10);
    int[] pos = DOMUtils.getOffset(getElement(), cell.getCellElement());
    int left = pos[0];
    int top = pos[1];
    l.getElement().getStyle().setProperty("position", "absolute");
    l.getElement().getStyle().setPropertyPx("left", left);
    l.getElement().getStyle().setPropertyPx("top", top);
    DebugUtils.addBgColor(l.getElement());
  }

  /**
   * Creates the top view.
   * @return the top view widget
   */
  private Widget createTopHeader() {
    FlexTable g = new FlexTable();
    g.addStyleName(CSS.genericContainer());
    g.setWidth("100%");

    topLabels = new ArrayList<Cell<Element>>(7);
    for (int i = 0; i < 7; i++) {
      Cell<Element> cell = new BaseCell(0, i);
      cell.getCellElement().setInnerHTML(0 + ", " + i);
      topLabels.add(cell);

      g.setElement(0, i, cell.getCellElement());
      g.getCellFormatter().setWidth(0, 0, ((float) 100 / 7) + "%");
      g.getFlexCellFormatter().setHorizontalAlignment(0, i, HasHorizontalAlignment.ALIGN_CENTER);
    }
    return g;
  }

  public List<Cell<Element>> getColumnsDecorableElements() {
    return Collections.unmodifiableList(topLabels);
  }

  public List<Cell<Element>> getContentDecorableElements() {
    return Collections.unmodifiableList(monthView.getMainElements());
  }

  public List<Cell<Element>> getRowsDecorableElements() {
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
