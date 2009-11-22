package gwtscheduler.client.modules;

import gwtscheduler.client.interfaces.CalendarPresenter;
import gwtscheduler.client.interfaces.EventWidgetFactory;
import gwtscheduler.client.interfaces.LassoElementFactory;
import gwtscheduler.client.interfaces.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.interfaces.navigation.DateGenerator;
import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.modules.views.DefaultUIRegistry;
import gwtscheduler.client.modules.views.UIManager;
import gwtscheduler.client.utils.GenericDateGenerator;
import gwtscheduler.client.widgets.decorator.DateTimeLabelDecorator;
import gwtscheduler.client.widgets.decorator.MonthLabelDecorator;
import gwtscheduler.client.widgets.view.DayPresenter;
import gwtscheduler.client.widgets.view.MonthPresenter;
import gwtscheduler.client.widgets.view.WeekPresenter;
import gwtscheduler.client.widgets.view.common.factory.GenericEventWidgetFactory;
import gwtscheduler.client.widgets.view.common.factory.GenericLassoElementFactory;
import gwtscheduler.client.widgets.view.dayweek.AbstractDaysCalendar;
import gwtscheduler.client.widgets.view.dayweek.DayCalendar;
import gwtscheduler.client.widgets.view.dayweek.WeekCalendar;
import gwtscheduler.client.widgets.view.month.MonthCalendar;
import gwtscheduler.client.widgets.view.month.MonthDisplay;
import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * Defines the ui Ioc User Interface Module.
 * @author malp
 */
public class UIModule extends AbstractGinModule {

  protected void configure() {
    //general
    bind(UIManager.class).to(DefaultUIRegistry.class).in(Singleton.class);
    bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
    
    
    bind(DateGenerator.class).to(GenericDateGenerator.class);
    bind(EventWidgetFactory.class).to(GenericEventWidgetFactory.class).in(Singleton.class);
    bind(LassoElementFactory.class).to(GenericLassoElementFactory.class).in(Singleton.class);

    // controllers
    bind(CalendarPresenter.class).annotatedWith(Day.class).to(DayPresenter.class);
    bind(CalendarPresenter.class).annotatedWith(Week.class).to(WeekPresenter.class);
    bind(CalendarPresenter.class).annotatedWith(Month.class).to(MonthPresenter.class);

    // decorators
    bind(MultipleElementsIntervalDecorator.class).annotatedWith(Day.class).to(DateTimeLabelDecorator.class);
    bind(MultipleElementsIntervalDecorator.class).annotatedWith(Week.class).to(DateTimeLabelDecorator.class);
    bind(MultipleElementsIntervalDecorator.class).annotatedWith(Month.class).to(MonthLabelDecorator.class);
    
    //bind the view to the ctrl
    bind(AbstractDaysCalendar.class).annotatedWith(Week.class).to(WeekCalendar.class);
    bind(AbstractDaysCalendar.class).annotatedWith(Day.class).to(DayCalendar.class);
    bind(MonthDisplay.class).annotatedWith(Month.class).to(MonthCalendar.class);

  }
}