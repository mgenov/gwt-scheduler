package gwtscheduler.client;

import com.google.inject.Provider;
import gwtscheduler.client.widgets.common.event.AppointmentEvent;
import gwtscheduler.common.model.event.AbstractAppointment;

import java.util.Set;


/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class GwtScheduler {

  public interface Display{
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
