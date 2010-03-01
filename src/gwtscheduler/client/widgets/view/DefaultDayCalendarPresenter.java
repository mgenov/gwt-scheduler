package gwtscheduler.client.widgets.view;

import gwtscheduler.client.widgets.view.common.AbstractCalendarPresenter;
import gwtscheduler.client.widgets.view.dayweek.AbstractDaysView;
import net.customware.gwt.presenter.client.EventBus;
import org.goda.time.Duration;
import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class DefaultDayCalendarPresenter extends AbstractCalendarPresenter<AbstractDaysView>{
  /**
   * Default constructor.
   *
   * @param display  the display
   * @param eventBus the event bus
   */
  protected DefaultDayCalendarPresenter(AbstractDaysView display, EventBus eventBus) {
    super(display, eventBus);
  }

  @Override
  public String getTabLabel() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Instant getInstantForCell(int[] start) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  protected Duration getDurationPerCells(int count) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public int getRowNum() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public int getColNum() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Interval onNavigateNext() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Interval onNavigatePrevious() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Interval onNavigateTo(ReadableDateTime date) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
