package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.utils.DOMUtils;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;
import gwtscheduler.client.widgets.view.common.cell.BaseCell;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarHeaderWidget extends Composite implements CalendarHeader.Display, WidgetResizeHandler {
  /**
     * static ref to css
     */
    protected static final DayWeekCssResource CSS = Resources.dayWeekCss();


  private FlexTable header;

  private int columns;

    /**
   * top view cells
   */
  protected List<Cell<Element>> topLabels;

  public CalendarHeaderWidget(int columns) {
    this.columns = columns;
    buildCalendarHeader(columns);

    initWidget(header);
  }
  
  public void buildCalendarHeader(int columns){
    FlexTable g = new FlexTable();
    g.addStyleName(CSS.genericContainer());
    g.setWidth("100%");
    g.getCellFormatter().setWidth(0, 0, CSS.titleColumnWidthPx() + "px");
//    g.getCellFormatter().setWidth(0, columns + 1, Constants.SCROLLBAR_WIDTH() + "px");

    topLabels = new ArrayList<Cell<Element>>(columns);

    for (int i = 0; i < columns; i++) {
      Cell<Element> topCell = new BaseCell(0, i+1);

      //only top row is for labels
      topLabels.add(topCell);

      g.setWidget(0, 1 + i, DOMUtils.wrapElement(topCell.getCellElement()));
      g.getFlexCellFormatter().setHorizontalAlignment(0, 1 + i, HasHorizontalAlignment.ALIGN_CENTER);
    }
     header = g;
  }

  public void removeCell(int columnIndex) {
    header.removeCell(0,columnIndex);
    topLabels.remove(columnIndex);
  }

  public void addCell(String title) {
    Cell<Element> topCell = new BaseCell(0, topLabels.size());
    //only top row is for labels
    topLabels.add(topCell);

    header.setWidget(0, topLabels.size(), DOMUtils.wrapElement(topCell.getCellElement()));
    header.getFlexCellFormatter().setHorizontalAlignment(0, topLabels.size(), HasHorizontalAlignment.ALIGN_CENTER);
    columns++;
  }

  @Override
  public WidgetResizeHandler getCalendarHeaderResizeHandler() {
    return this;  
  }

  public List<Cell<Element>> getTopLabels() {
    return topLabels;
  }

  @Override
  public void onResize(WidgetResizeEvent event) {

   int width = event.width;

    if (width <= 0) {
      return;
    }

    header.setPixelSize(width - Constants.SCROLLBAR_WIDTH(), -1);
    int availableWidth = getCellWidth(width - Constants.SCROLLBAR_WIDTH());

    for (int i=1;i<header.getCellCount(0);i++) {
       header.getCellFormatter().setWidth(0,i,""+availableWidth);
    }

    for (Cell<Element> cell : topLabels) {
        BaseCell baseCell = (BaseCell)cell;
        baseCell.setStyleName(CSS.headerCell());
    }
    
  }

  /**
   * Gets the title column width for the title column.
   * @return the title column width
   */
  final int getTitleColumnWidth() {
    return CSS.titleColumnWidthPx();
  }

  /**
   * Gets the title column width for the title column, including borders and
   * padding.
   * @return the title column offset width
   */
  int getTitleColumnOffsetWidth() {
    return getTitleColumnWidth();// + CSS.smallPaddingPx();
  }

  /**
   * Gets the default cell size for a non-title cell.
   * @param parentWidth the parent width
   * @return the cell size, without counting with borders or padding
   */
  int getCellWidth(int parentWidth) {
    int availW = ((parentWidth - getTitleColumnOffsetWidth()) / columns);
    return availW;
  }
}
