package gwtscheduler.client.modules;

import gwtscheduler.client.utils.GenericDateGenerator;
import gwtscheduler.client.widgets.common.event.GenericLassoElementFactory;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.view.common.factory.LassoElementFactory;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * Defines the ui Ioc User Interface Module.
 * @author malp
 */
public class UIModule extends AbstractGinModule {

  protected void configure() {
    //general
//    bind(MainView.class).to(DateViewsTabPanel.class).in(Singleton.class);
//    bind(UIManager.class).to(DefaultUIRegistry.class).in(Singleton.class);
    bind(EventBus.class).in(Singleton.class);

    bind(DateGenerator.class).to(GenericDateGenerator.class);
    bind(LassoElementFactory.class).to(GenericLassoElementFactory.class).in(Singleton.class);
//
//    // controllers
//    bind(CalendarPresenter.class).annotatedWith(Day.class).to(DayPresenter.class);
//    bind(CalendarPresenter.class).annotatedWith(Week.class).to(WeekPresenter.class);
//    bind(CalendarPresenter.class).annotatedWith(Month.class).to(MonthPresenter.class);
//
//    // decorators
//    bind(MultipleElementsIntervalDecorator.class).annotatedWith(Day.class).to(DateTimeLabelDecorator.class);
//    bind(MultipleElementsIntervalDecorator.class).annotatedWith(Week.class).to(DateTimeLabelDecorator.class);
//    bind(MultipleElementsIntervalDecorator.class).annotatedWith(Month.class).to(MonthLabelDecorator.class);
//
//    //bind the view to the ctrl
//    bind(AbstractDaysView.class).annotatedWith(Week.class).to(WeekView.class);
//    bind(AbstractDaysView.class).annotatedWith(Day.class).to(DayView.class);
//    bind(MonthDisplay.class).annotatedWith(Month.class).to(MonthView.class);

  }
}