package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.decoration.HasMultipleDecorables;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.widgets.ViewportPanel;
import gwtscheduler.client.widgets.view.common.cell.BaseCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.gen2.table.override.client.FlexTable;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Composite view class for days. Has an upper label and a grid.
 */
public abstract class AbstractCompositeDaysPanel extends Composite implements HasMultipleDecorables<Element> {

  /** static ref to css */
  protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /** widget impl */
  protected VerticalPanel impl;
  /** day view */
  protected AbstractDayPanel mainView;
  /** top view */
  protected Widget topView;
  /** top view cells */
  protected List<Cell<Element>> topLabels;

  /** viewport widget */
  private ViewportPanel vmain;

  /**
   * Default constructor.
   */
  public AbstractCompositeDaysPanel() {
    impl = new VerticalPanel();

    mainView = createDayView();
    vmain = new ViewportPanel();
    vmain.add(mainView, mainView.getResizeHandler());

    topView = createTopView(mainView.getColumns());

    impl.add(topView);
    impl.add(vmain);

    initWidget(impl);
    // we'll delegate the resize to the viewport panel
    addHandler(new WidgetResizeHandler() {
      public void onResize(WidgetResizeEvent event) {
        vmain.doDeferredResize();
      }
    }, WidgetResizeEvent.getType());
  }

  /**
   * Creates the top view widget.
   * @param columns the number of columns
   * @return the top view widget
   */
  protected Widget createTopView(int columns) {
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

  public List<Cell<Element>> getDaysDecorableElements() {
    return Collections.unmodifiableList(topLabels);
  }

  public List<Cell<Element>> getWithinDayDecorableElements() {
    return Collections.unmodifiableList(mainView.getTitleDecorables());
  }

  public List<Cell<Element>> getMultipleDecorableElements() {
    return Collections.unmodifiableList(mainView.getMainDecorables());
  }

  /**
   * Creates the day view widget.
   * @return the day view widget
   */
  protected abstract AbstractDayPanel createDayView();
}
