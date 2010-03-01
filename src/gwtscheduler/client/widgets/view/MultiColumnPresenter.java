package gwtscheduler.client.widgets.view;

import com.google.inject.Inject;
import com.google.inject.Provider;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.annotation.ColumnView;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.widgets.common.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.widgets.common.decorator.ColumnTitleProvider;
import gwtscheduler.client.widgets.view.common.AbstractCalendarPresenter;
import gwtscheduler.client.widgets.view.dayweek.AbstractDaysView;
import org.goda.time.Duration;
import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class MultiColumnPresenter extends AbstractCalendarPresenter<AbstractDaysView> {

  public static class  MultyColumnPresenterProvider implements Provider<MultiColumnPresenter>{
    private AppConfiguration cfg;
    private AbstractDaysView display;
    private MultipleElementsIntervalDecorator decorator;
    private EventBus eventBus;
    private ColumnTitleProvider columnTitleProvider;

    @Inject
    public MultyColumnPresenterProvider(AppConfiguration cfg,@ColumnView ColumnTitleProvider columnTitleProvider,  @ColumnView AbstractDaysView display, @ColumnView MultipleElementsIntervalDecorator decorator, EventBus eventBus) {
      this.cfg = cfg;
      this.columnTitleProvider = columnTitleProvider;
      this.display = display;
      this.decorator = decorator;
      this.eventBus = eventBus;
    }

    @Override
    public MultiColumnPresenter get() {
      MultiColumnPresenter multiColumn = new MultiColumnPresenter(cfg,decorator, columnTitleProvider, eventBus);
      return multiColumn;
    }
  }


  private AppConfiguration cfg;
  private ColumnTitleProvider columnTitleProvider;
//  private AbstractDaysView view;
  private MultipleElementsIntervalDecorator decorator;
  private EventBus eventBus;

  /**
   * holds the number of rows within a day
   */
  private final int rows;
  private Display display;
  private String tabLabel;
  private int columns;

  /**
   * Default constructor.
   *
   * @param columnTitleProvider
   * @param eventBus the event bus
   */
  public MultiColumnPresenter(AppConfiguration cfg, @ColumnView MultipleElementsIntervalDecorator decorator, ColumnTitleProvider columnTitleProvider, EventBus eventBus) {
    super(eventBus);
    this.cfg = cfg;
    this.columnTitleProvider = columnTitleProvider;
//    this.view = view;
    this.decorator = decorator;
    this.eventBus = eventBus;
    rows =  cfg.rowsInDay();
  }

  @Override
  public void bindDispaly(Display display) {
    this.display = display;
  }

  public String getTabLabel() {
    return tabLabel;
  }

  public void setTabLabel(String tabLabel) {
    this.tabLabel = tabLabel;
  }

  @Override
  public Instant getInstantForCell(int[] start) {
    return null;
  }

  @Override
  protected Duration getDurationPerCells(int count) {
    return null;
  }

  public int getRowNum() {
    return rows;
  }

  public int getColNum() {
    return columns;
  }

  public void setColNum(int columns) {
    this.columns = columns;
  }

  public Interval onNavigateNext() {
    return null;
  }

  public Interval onNavigatePrevious() {
    return null;
  }

  public Interval onNavigateTo(ReadableDateTime date) {
    return null;
  }
}
