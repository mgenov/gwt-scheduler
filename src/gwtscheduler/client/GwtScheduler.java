package gwtscheduler.client;

import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.modules.views.MainView;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.navigation.TabPanelContainer;

import java.util.List;


/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class GwtScheduler implements MainView, BeforeSelectionHandler<Integer> {

  public interface Display {

    void selectTab(int i);

    void add(CalendarPresenter.Display display, String title);

    void addBeforeSelectionHandler(BeforeSelectionHandler<Integer> handler);
  }


  /**
   * presenters array
   */
  private List<CalendarPresenter> presenters;
  private Display display;


  public GwtScheduler(List<CalendarPresenter> presenters) {
    this.presenters = presenters;
//
//    presenters = new CalendarPresenter[calendarPresenters.size()];
//    int i = 0;
//    for (CalendarPresenter calendarPresenter : calendarPresenters) {
//      presenters[i] = calendarPresenter;
//      add(calendarPresenter);
//      i++;
//    }
  }

  public void bindDisplay(Display display) {
    this.display = display;

    display.addBeforeSelectionHandler(this);

    for (CalendarPresenter presenter : presenters) {
      add(presenter);
    }

  }


  @Override
  public Widget asWidget() {
    return (Widget) display;
  }

  @Override
  public void forceLayout() {
    for (CalendarPresenter p : presenters) {
      p.forceLayout();
    }
  }

  @Override
  public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {
    CalendarPresenter presenter = presenters.get(event.getItem());
    presenter.forceLayout();
  }

  /**
   * Adds a new view to it's display panel.
   *
   * @param presenter the controller
   */
  private void add(CalendarPresenter presenter) {
    display.add(presenter.getDisplay(), presenter.getTitle());
  }

  /**
   * Selects a tab.
   *
   * @param i the tab index
   */
  public void selectTab(int i) {
    display.selectTab(i);
  }


//   public static class  GwtTimeSchedulerProvider implements Provider<GwtScheduler>{
//
//     @Override
//     public GwtScheduler get() {
//       return null;
//     }
//   }
//
//   public static class  GwtColumnSchedulerProvider implements Provider<GwtScheduler>{
//
//     @Override
//     public GwtScheduler get() {
//       return null;
//     }
//   }
//
//
//  interface AppointmentProvider {
//
//    Set<AbstractAppointment> getAppointments();
//
//  }
//
//  interface HeaderTitleProvider {
//    String[] titles();
//  }
//
//
//  interface CalendarStrategy {
//
//    AppointmentProvider getAppointments();
//
//    HeaderTitleProvider getHeaderTitleProvider();
//
//  }
//
//  class TeamCalendarStrategy implements CalendarStrategy{
//    private TeamAppointmentProvider appointmentProvider;
//
//    public TeamCalendarStrategy(TeamAppointmentProvider appointmentProvider) {
//      this.appointmentProvider = appointmentProvider;
//    }
//
//    @Override
//    public AppointmentProvider getAppointments() {
//      return appointmentProvider;
//    }
//
//    @Override
//    public HeaderTitleProvider getHeaderTitleProvider() {
//      return null;
//    }
//  }
//  class Team {
//    Integer id;
//    String name;
//
//  }
//
//  class TeamAppointmentProvider implements AppointmentProvider {
//    private Set<Team> teams;
//
//    public TeamAppointmentProvider(Set<Team> teams) {
//      this.teams = teams;
//    }
//
//    @Override
//    public Set<AbstractAppointment> getAppointments() {
//
//
//
//      return null;
//    }
//  }
//
//  class TeamHeaderTitleProvider implements HeaderTitleProvider {
//    private Set<Team> teams;
//
//    public TeamHeaderTitleProvider(Set<Team> teams) {
//      this.teams = teams;
//    }
//
//    @Override
//    public String[] titles() {
//      String[] titles=  new String[teams.size()];
//      int i = 0;
//      for (Team t : teams) {
//        titles[i] = t.name;
//        i++;
//      }
//      return titles;
//    }
//
//  }

}
