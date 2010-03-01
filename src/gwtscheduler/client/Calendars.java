package gwtscheduler.client;

import com.google.inject.Inject;
import com.google.inject.Provider;
import gwtscheduler.client.widgets.common.decorator.ColumnTitleProvider;
import gwtscheduler.client.widgets.view.MultiColumnPresenter;
import gwtscheduler.client.widgets.view.common.AbstractCalendarPresenter;
import gwtscheduler.client.widgets.view.dayweek.AbstractDaysView;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class Calendars {

  private Provider<MultiColumnPresenter> multiColumnPresenterProvider;
  private static AbstractCalendarPresenter<AbstractDaysView> calendar;


  public Calendars(Provider<MultiColumnPresenter> multiColumnPresenterProvider) {
    this.multiColumnPresenterProvider = multiColumnPresenterProvider;
  }

  public Calendars newMultiColumn(ColumnTitleProvider columnTitleProvider) {
    
    calendar = multiColumnPresenterProvider.get();
    return this;
  }

  public AbstractCalendarPresenter<AbstractDaysView> build(){
    return calendar;
  }
}
