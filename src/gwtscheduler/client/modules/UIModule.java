package gwtscheduler.client.modules;

import gwtscheduler.client.interfaces.EventWidgetFactory;
import gwtscheduler.client.interfaces.LassoElementFactory;
import gwtscheduler.client.interfaces.ViewController;
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
import gwtscheduler.client.widgets.view.DayController;
import gwtscheduler.client.widgets.view.MonthController;
import gwtscheduler.client.widgets.view.WeekController;
import gwtscheduler.client.widgets.view.common.GenericEventWidgetFactory;
import gwtscheduler.client.widgets.view.common.GenericLassoElementFactory;

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
    bind(EventWidgetFactory.class).to(GenericEventWidgetFactory.class).in(Singleton.class);
    bind(LassoElementFactory.class).to(GenericLassoElementFactory.class).in(Singleton.class);

    // controllers
    bind(ViewController.class).annotatedWith(Day.class).to(DayController.class);
    bind(ViewController.class).annotatedWith(Week.class).to(WeekController.class);
    bind(ViewController.class).annotatedWith(Month.class).to(MonthController.class);

    // decorators
    bind(MultipleElementsIntervalDecorator.class).annotatedWith(Day.class).to(DateTimeLabelDecorator.class);
    bind(MultipleElementsIntervalDecorator.class).annotatedWith(Week.class).to(DateTimeLabelDecorator.class);
    bind(MultipleElementsIntervalDecorator.class).annotatedWith(Month.class).to(MonthLabelDecorator.class);

  }
}