package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.SimplePanel;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.utils.DOMUtils;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.decoration.HasMultipleDecorables;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;
import gwtscheduler.client.widgets.view.common.cell.BaseCell;
import gwtscheduler.client.widgets.view.common.events.ColumnClickEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarHeaderWidget extends Composite implements CalendarHeader.Display, WidgetResizeHandler, HasMultipleDecorables<Element> {
  /**
     * static ref to css
     */
    protected static final DayWeekCssResource CSS = Resources.dayWeekCss();


  private FlexTable header;

  private int columns;
  private int calendarWidth;
  private EventBus eventBus;

  /**
   * top view cells
   */
  protected List<Cell<Element>> topLabels;

  public CalendarHeaderWidget(int columns, int calendarWidth, EventBus eventBus) {
    this.columns = columns;
    this.calendarWidth = calendarWidth;
    this.eventBus = eventBus;
    buildCalendarHeader(columns);

    SimplePanel sp = new SimplePanel();
    sp.setStyleName(CSS.genericHeaderContainer());
    sp.add(header);
    initWidget(sp);
  }
  
  public void buildCalendarHeader(int columns){
    final FlexTable g = new FlexTable();
    g.addStyleName(CSS.genericContainer());

    topLabels = new ArrayList<Cell<Element>>(columns);

    for (int i = 0; i < columns; i++) {
      final BaseCell topCell = new BaseCell(0, i);

      //only top row is for labels
      topLabels.add(topCell);
      g.setWidget(0, i, topCell);
    }

    g.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        int columnIndex = g.getCellForEvent(event).getCellIndex();
        int rowIndex = g.getCellForEvent(event).getRowIndex();
        String title = g.getWidget(rowIndex, columnIndex).getElement().getInnerText();
        eventBus.fireEvent(new ColumnClickEvent(title, columnIndex));
      }
    });
     header = g;
  }

  public void removeCell(int columnIndex) {
    header.removeCell(0,columnIndex);
    topLabels.remove(columnIndex);
    columns--;
  }

  public void addCell(String title) {
    int pos = topLabels.size();
    final BaseCell topCell = new BaseCell(0, pos);
    //only top row is for labels
    topLabels.add(topCell);


    header.setWidget(0, pos, DOMUtils.wrapElement(topCell.getCellElement()));
    header.getFlexCellFormatter().setHorizontalAlignment(0, pos, HasHorizontalAlignment.ALIGN_CENTER);
    columns++;
  }

  @Override
  public WidgetResizeHandler getCalendarHeaderResizeHandler() {
    return this;  
  }

  @Override
  public HasMultipleDecorables getDecorables() {
    return this;
  }


  @Override
  public void onResize(WidgetResizeEvent event) {

   int width = event.width;

    if (width <= 0) {
      return;
    }

    int totalWidth = width - Constants.SCROLLBAR_WIDTH() - CSS.headerPaddingLeft();
    header.setPixelSize(totalWidth, -1);
    int availableWidth = getCellWidth(totalWidth);

    for (int i = 0;i<header.getCellCount(0);i++) {
       header.getCellFormatter().setWidth(0,i, availableWidth+"px");
    }

    for (Cell<Element> cell : topLabels) {
        BaseCell baseCell = (BaseCell)cell;
        baseCell.addStyleName(CSS.headerCell());
        baseCell.getElement().getStyle().setWidth(availableWidth - 2, Style.Unit.PX);
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
    int availW = (parentWidth / columns);
    return availW;
  }

  @Override
  public List<Cell<Element>> getDecorableElements() {
    return Collections.unmodifiableList(topLabels);
  }
}
