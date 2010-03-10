package gwtscheduler.client.events;

import gwtscheduler.client.widgets.common.event.ColumnEvent;
import gwtscheduler.common.event.Event;
import org.goda.time.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class MyTest {

  public void testHumanDistribution() {
    List<Event> events = new ArrayList<Event>();

    TeamEventVisitor visitor = new TeamEventVisitor();
    for (Event e : events) {
      visitor.visit(e);
    }
  }

  class Team {

  }

  interface EventVisitor {
    void visit(Event event);

    void visit(ColumnEvent event);
  }

  class TeamEventVisitor implements EventVisitor {

    @Override
    public void visit(Event event) {
      
    }

    @Override
    public void visit(ColumnEvent event) {
      
    }
  }

  class TeamEvent implements Event {
    private final Interval interval;

    public TeamEvent(Interval interval) {

      this.interval = interval;
    }

    @Override
    public Interval getInterval() {
      return interval;
    }

    @Override
    public void setInterval(Interval interval) {
    }

    @Override
    public String getTitle() {
      return null;
    }

  }

}
