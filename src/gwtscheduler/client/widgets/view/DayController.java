package gwtscheduler.client.widgets.view;

import gwtscheduler.client.widgets.view.common.AbstractCompositeDaysPanel;
import gwtscheduler.client.widgets.view.common.AbstractDayPanel;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;
import gwtscheduler.common.calendar.Interval;

import com.google.inject.Singleton;

/**
 * Controller for days view.
 * @author malp
 */
@Singleton
public class DayController extends
    AbstractViewController<AbstractCompositeDaysPanel> {

  //  IDecorator<Element> titlesDecorator = new DateTimeLabelDecorator();

  @Override
  protected AbstractCompositeDaysPanel createView() {
    return new AbstractCompositeDaysPanel() {
      @Override
      protected AbstractDayPanel createDayView() {
        return new AbstractDayPanel() {

          @Override
          protected int getColumns() {
            return 1;
          }

          @Override
          protected int getRows() {
            return 48; // 24*2
          }

        };
      }
    };
  }

  public String getTabLabel() {
    return "Day";
  }

  public ITimePeriod onNavigateNext() {
    ITimePeriod tp = getFactory().next().period();
    //    IDate start = tp.start();
    //    Iterator<ICell<Element>> it = getViewWidget().getDecorablesIterator();
    //    while (it.hasNext()) {
    //      ICell<Element> cell = it.next();
    //      titlesDecorator.decorate(start, cell,cell.getCellElement());
    //      start.addDays(1);
    //    }
    return tp;
  }

  public ITimePeriod onNavigatePrevious() {
    return getFactory().previous().period();
  }

  public ITimePeriod onNavigateTo(IDate date) {
    if (!date.equals(getFactory().current())) {
      getFactory().init(Interval.DAY, date);
    }
    return getFactory().period();
  }

}
