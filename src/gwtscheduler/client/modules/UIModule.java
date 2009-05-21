package gwtscheduler.client.modules;

import gwtscheduler.client.interfaces.IDateFactory;
import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.modules.views.IUIRegistry;
import gwtscheduler.client.modules.views.IViewController;
import gwtscheduler.client.utils.GenericDateFactory;
import gwtscheduler.client.widgets.view.DayController;
import gwtscheduler.client.widgets.view.MonthController;
import gwtscheduler.client.widgets.view.WeekController;

import com.google.gwt.inject.client.AbstractGinModule;

/**
 * Defines the ui Ioc User Interface Module.
 * 
 * @author malp
 */
public class UIModule extends AbstractGinModule {

  protected void configure() {
    // examples

    // public DefaultCardGrid(@BackOfCard Provider<Image> backOfCard,
    // @Rows int rows, @Columns int columns) {

    // bindConstant().annotatedWith(Rows.class).to(2);
    // bindConstant().annotatedWith(Columns.class).to(5);
    // bind(Integer.class).annotatedWith(NumberOfCards.class).toProvider(NumberOfCardsProvider.class);

    bind(IUIRegistry.class).to(UIRegistry.class);
    bind(IDateFactory.class).to(GenericDateFactory.class);
    
    //controllers
    bind(IViewController.class)
          .annotatedWith(Day.class)
          .to(DayController.class);
    bind(IViewController.class)
          .annotatedWith(Week.class)
          .to(WeekController.class);
    bind(IViewController.class)
          .annotatedWith(Month.class)
          .to(MonthController.class);
    
    //decorators
  }
}