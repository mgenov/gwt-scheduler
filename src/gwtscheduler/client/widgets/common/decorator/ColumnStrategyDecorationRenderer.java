package gwtscheduler.client.widgets.common.decorator;

import com.google.gwt.user.client.Element;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.decoration.DecorationRenderer;
import gwtscheduler.client.widgets.common.decoration.HasMultipleDecorables;
import org.goda.time.Interval;
import org.goda.time.Period;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class ColumnStrategyDecorationRenderer implements DecorationRenderer{

  private DateTimeLabelDecorator decorator;
  private ColumnTitleProvider titleProvider;


  public ColumnStrategyDecorationRenderer(DateTimeLabelDecorator decorator, ColumnTitleProvider titleProvider) {
    this.decorator = decorator;
    this.titleProvider = titleProvider;
  }

  @Override
  public void decorateVerticalTimeLine(Interval interval, HasMultipleDecorables<Element> decorables) {
    titleProvider.setInterval(interval);
    Period day = new Period(0, 0, 0, 1, 0, 0, 0, 0);
    decorator.decorateVertical(interval.getStart(), day, decorables.getRowsDecorableElements());
  }

  @Override
  public void decorateHorizontalTitlesLine(Interval interval, HasMultipleDecorables<Element> decorables) {
     titleProvider.setInterval(interval);
     decorator.decorateHorizontal(titleProvider, decorables.getColumnsDecorableElements());
    
  }
}
