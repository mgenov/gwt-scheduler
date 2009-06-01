package gwtscheduler.client.modules;

import gwtscheduler.client.interfaces.IDateGenerator;
import gwtscheduler.client.interfaces.IDecorator;
import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.modules.views.IUIRegistry;
import gwtscheduler.client.modules.views.IViewController;
import gwtscheduler.client.utils.GenericDateGenerator;
import gwtscheduler.client.widgets.decorator.DateTimeLabelDecorator;
import gwtscheduler.client.widgets.view.DayController;
import gwtscheduler.client.widgets.view.MonthController;
import gwtscheduler.client.widgets.view.WeekController;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * Defines the ui Ioc User Interface Module.
 * @author malp
 */
public class UIModule extends AbstractGinModule {

  protected void configure() {
    //general
    bind(IUIRegistry.class).to(UIRegistry.class).in(Singleton.class);;
    bind(IDateGenerator.class).to(GenericDateGenerator.class);

    // controllers
    bind(IViewController.class).annotatedWith(Day.class).to(DayController.class);
    bind(IViewController.class).annotatedWith(Week.class).to(WeekController.class);
    bind(IViewController.class).annotatedWith(Month.class).to(MonthController.class);
    
    bind(IDecorator.class).to(DateTimeLabelDecorator.class);

    // decorators
  }
}