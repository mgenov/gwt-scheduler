package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.decoration.HasMultipleDecorables;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.utils.DOMUtils;
import gwtscheduler.client.utils.DebugUtils;
import gwtscheduler.client.widgets.AdaptableWindowPanel;
import gwtscheduler.client.widgets.view.common.cell.BaseCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.gen2.table.override.client.FlexTable;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Composite view class for days. Has an upper label and a grid.
 */
public abstract class MultipleDaysCalendar extends Composite implements HasMultipleDecorables<Element> {

  /** static ref to css */
  protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /** widget impl */
  protected VerticalPanel impl;
  /** day view */
  protected MultipleDaysPanel mainView;
  /** top view */
  protected Widget topHeader;
  /** top view cells */
  protected List<Cell<Element>> topLabels;

  /** viewport widget */
  private AdaptableWindowPanel vmain;

  /**
   * Default constructor.
   */
  public MultipleDaysCalendar() {
    impl = new VerticalPanel();

    mainView = createDaysPanel();
    vmain = new AdaptableWindowPanel();
    //    vmain.add(mainView, mainView.getResizeHandler());
    vmain.add(mainView);
    vmain.addResizeHandler(mainView.getResizeHandler());

    //css positioning
    vmain.getElement().getStyle().setProperty("position", "relative");
    mainView.getElement().getStyle().setProperty("position", "absolute");
    mainView.getElement().getStyle().setPropertyPx("top", 0);
    mainView.getElement().getStyle().setPropertyPx("left", 0);

    topHeader = createTopHeader(mainView.getColumns());

    impl.add(topHeader);
    impl.add(vmain);
    initWidget(impl);

    // we'll delegate the resize to the viewport panel
    addHandler(new WidgetResizeHandler() {
      public void onResize(WidgetResizeEvent event) {
        vmain.doDeferredResize();
        onSelection();
      }
    }, WidgetResizeEvent.getType());
  }

  public void onSelection() {
    //TODO fixme this is not working properly
    GWT.log("onSelection called - good time to make some calcs", null);
    Label l = new Label("xxx");
    vmain.add(l);

    Cell<Element> cell = mainView.getMainDecorables().get(10);
    int[] pos = DOMUtils.getOffset(mainView.getElement(), cell.getCellElement());
    int left = pos[0];
    int top = pos[1];
    l.getElement().getStyle().setProperty("position", "absolute");
    l.getElement().getStyle().setPropertyPx("left", left);
    l.getElement().getStyle().setPropertyPx("top", top);
    DebugUtils.addBgColor(l.getElement());
  }

  /**
   * Creates the top view widget.
   * @param columns the number of columns
   * @return the top view widget
   */
  protected Widget createTopHeader(int columns) {
    FlexTable g = new FlexTable();
    g.addStyleName(CSS.genericContainer());
    g.setWidth("100%");
    g.getCellFormatter().setWidth(0, 0, CSS.titleColumnWidthPx() + "px");
    g.getCellFormatter().setWidth(0, columns + 2, Constants.SCROLLBAR_WIDTH + "px");

    topLabels = new ArrayList<Cell<Element>>(columns);
    for (int i = 0; i < columns; i++) {
      Cell<Element> topCell = new BaseCell(0, i);
      topCell.getCellElement().setInnerHTML(0 + ", " + i);//debug

      topLabels.add(topCell);
      g.setElement(0, 1 + i, topCell.getCellElement());
      g.getFlexCellFormatter().setHorizontalAlignment(0, 1 + i, HasHorizontalAlignment.ALIGN_CENTER);
    }
    return g;
  }

  public List<Cell<Element>> getColumnsDecorableElements() {
    return Collections.unmodifiableList(topLabels);
  }

  public List<Cell<Element>> getRowsDecorableElements() {
    return Collections.unmodifiableList(mainView.getTitleDecorables());
  }

  public List<Cell<Element>> getContentDecorableElements() {
    return Collections.unmodifiableList(mainView.getMainDecorables());
  }

  /**
   * Creates the day view widget.
   * @return the day view widget
   */
  protected abstract MultipleDaysPanel createDaysPanel();
}
