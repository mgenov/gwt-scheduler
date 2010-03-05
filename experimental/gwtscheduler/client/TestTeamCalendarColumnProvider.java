package gwtscheduler.client;

import gwtscheduler.client.widgets.common.decorator.ColumnTitleProvider;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.columns.CalendarColumnsProvider;

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
//    columns.add(new TeamColumn(" "));
  }

  @Override
  public List<CalendarColumn> getColumns() {

    return columns;
  }

  public static class TeamColumn implements CalendarColumn {
    private final Team team;

    public TeamColumn(String name) {
      team = new Team(name);
    }

    @Override
    public String getTitle() {
      return team.getName();
    }

    @Override
    public Object getObject() {
      return team;
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
      if(column.getTitle().equals(title)){
        return  column;
      }
    }
    return null;
  }
}


