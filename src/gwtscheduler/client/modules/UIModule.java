package gwtscheduler.client.modules;

import gwtscheduler.client.interfaces.CalendarPresenter;
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
import gwtscheduler.client.widgets.view.common.factory.GenericLassoElementFactory;
import gwtscheduler.client.widgets.view.dayweek.AbstractDaysView;
import gwtscheduler.client.widgets.view.dayweek.DayView;
import gwtscheduler.client.widgets.view.dayweek.WeekView;
import gwtscheduler.client.widgets.view.month.MonthView;
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
    bind(AbstractDaysView.class).annotatedWith(Week.class).to(WeekView.class);
    bind(AbstractDaysView.class).annotatedWith(Day.class).to(DayView.class);
    bind(MonthDisplay.class).annotatedWith(Month.class).to(MonthView.class);

  }
}