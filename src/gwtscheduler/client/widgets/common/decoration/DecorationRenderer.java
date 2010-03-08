package gwtscheduler.client.widgets.common.decoration;

import com.google.gwt.user.client.Element;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import org.goda.time.Interval;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface DecorationRenderer {
  void decorateVerticalTimeLine(Interval interval,HasMultipleDecorables<Element> decorables);

  void decorateHorizontalTitlesLine(Interval interval, HasMultipleDecorables<Element> display);
}
