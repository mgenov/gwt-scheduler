package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Represents a month row.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class MonthRow extends Composite implements WidgetResizeHandler {

  /** impl */
  @UiField
  FlowPanel impl;
  /** will hold grid data */
  @UiField
  MonthRowTable grid;
  /** number of cols */
  private final int columns;

  /** ui binder instance */
  private static MonthRowUiBinder uiBinder = GWT.create(MonthRowUiBinder.class);

  /** ui binder interface */
  interface MonthRowUiBinder extends UiBinder<Widget, MonthRow> {
  }

  /**
   * Default constructor.
   * @param cols the number of columns
   */
  public MonthRow(int cols) {
    columns = cols;
    grid = new MonthRowTable(columns);
    initWidget(uiBinder.createAndBindUi(this));
    setStyleName(Resources.monthCss().monthRow());
  }

  /**
   * @return
   */
  @UiFactory
  public MonthRowTable buildMonthRowTable() {
    return new MonthRowTable(columns);
  }

  public void onResize(WidgetResizeEvent event) {
    if (!isVisible()) {
      return;
    }
    resizeRows();
  }

  /**
   * Resizes the rows within this month row.
   */
  public void resizeRows() {
    int availableHeight = getElement().getOffsetHeight();
    grid.redrawRows(availableHeight);
  }

  /**
   * Gets an iterator for the decorable elements.
   * @return the iterator for the decorable elements
   */
  public List<Cell<Element>> getTitleElements() {
    return grid.getTitleElements();
  }

}
