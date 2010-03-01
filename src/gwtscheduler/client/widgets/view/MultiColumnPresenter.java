package gwtscheduler.client.widgets.view;

import com.google.inject.Inject;
import com.google.inject.Provider;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.annotation.ColumnView;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.widgets.common.decoration.MultipleElementsIntervalDecorator;
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

    @Inject
    public MultyColumnPresenterProvider(AppConfiguration cfg,  @ColumnView AbstractDaysView display, @ColumnView MultipleElementsIntervalDecorator decorator, EventBus eventBus) {
      this.cfg = cfg;
      this.display = display;
      this.decorator = decorator;
      this.eventBus = eventBus;
    }

    @Override
    public MultiColumnPresenter get() {
      MultiColumnPresenter multiColumn = new MultiColumnPresenter(cfg,display,decorator,eventBus);
      return multiColumn;
    }
  }


  private AppConfiguration cfg;
  private AbstractDaysView view;
  private MultipleElementsIntervalDecorator decorator;
  private EventBus eventBus;

  /**
   * holds the number of rows within a day
   */
  private final int rows;

  /**
   * Default constructor.
   *
   * @param display  the display
   * @param eventBus the event bus
   */
  public MultiColumnPresenter(AppConfiguration cfg,@ColumnView AbstractDaysView view, @ColumnView MultipleElementsIntervalDecorator decorator, EventBus eventBus) {
    super(view, eventBus);
    this.cfg = cfg;
    this.view = view;
    this.decorator = decorator;
    this.eventBus = eventBus;
    rows = cfg.getDayViewTopRows();
  }



  public String getTabLabel() {
    return null;
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
    return 0;
  }

  public int getColNum() {
    return 0;
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
