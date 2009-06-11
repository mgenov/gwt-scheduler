package gwtscheduler.client.modules;

import gwtscheduler.client.interfaces.DateGenerator;
import gwtscheduler.client.interfaces.ViewController;
import gwtscheduler.client.interfaces.decoration.MultipleElementsDecorator;
import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.modules.views.DefaultUIRegistry;
import gwtscheduler.client.modules.views.UIManager;
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
    bind(UIManager.class).to(DefaultUIRegistry.class).in(Singleton.class);;
    bind(DateGenerator.class).to(GenericDateGenerator.class);

    // controllers
    bind(ViewController.class).annotatedWith(Day.class).to(DayController.class);
    bind(ViewController.class).annotatedWith(Week.class).to(WeekController.class);
    bind(ViewController.class).annotatedWith(Month.class).to(MonthController.class);

    bind(MultipleElementsDecorator.class).to(DateTimeLabelDecorator.class);

    // decorators
  }
}