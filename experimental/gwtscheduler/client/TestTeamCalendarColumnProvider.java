package gwtscheduler.client;

import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.columns.CalendarColumnsProvider;
import gwtscheduler.client.widgets.view.event.Event;
import gwtscheduler.common.util.Period;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class TestTeamCalendarColumnProvider implements CalendarColumnsProvider {


  private List<CalendarColumn> columns;

  public TestTeamCalendarColumnProvider() {
    columns = new ArrayList<CalendarColumn>();
    columns.add(new TeamColumn("t1"));
    columns.add(new TeamColumn("t2"));
    columns.add(new TeamColumn("t3"));
    columns.add(new TeamColumn("t4"));
    columns.add(new TeamColumn("t5"));
    columns.add(new TeamColumn("t5testt5"));
//    columns.makeDraggable(new TeamColumn(" "));
  }

  @Override
  public List<CalendarColumn> getColumns() {

    return columns;
  }

  @Override
  public void updateColumns(Period interval, List<CalendarColumn> columns) {
//    DateTime current = interval.getStart();
//    for (CalendarColumn column : columns) {
//      column.setObject(current);
//      current = current.plusDays(1);
  }


  public static class TeamColumn implements CalendarColumn<Team> {
    private final Team team;

    public TeamColumn(String name) {
      team = new Team(name);
    }

    @Override
    public String getTitle() {
      return team.getName();
    }

    @Override
    public Team getObject() {
      return team;
    }

    @Override
    public void setObject(Team team) {
    }

    @Override
    public boolean isEventForColumn(Event event) {
      if (((String) event.getColumnId()).equals((String) getId())) {
        return true;
      } else {
        return false;
      }
    }

    public Object getId() {
      return getTitle();
    }
  }

  public static class Team {
    private String name;

    public Team(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  public CalendarColumn getColumn(String title) {
    for (CalendarColumn column : columns) {
      if (column.getTitle().equals(title)) {
        return column;
      }
    }
    return null;
  }
}


