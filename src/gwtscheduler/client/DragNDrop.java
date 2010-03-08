package gwtscheduler.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import gwtscheduler.client.dragndrop.DragZone;
import gwtscheduler.client.dragndrop.DragZoneImpl;
import gwtscheduler.client.dragndrop.DragZoneView;
import gwtscheduler.client.teamexample.*;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragNDrop implements EntryPoint {
  public void onModuleLoad() {
//    AbsolutePanel absolutePanel = new AbsolutePanel();

//    absolutePanel.setPixelSize(800, 800);
    Team team1 = new Team(new TeamView(), "Team one");
    Team team2 = new Team(new TeamView(), "Team two");
    Team team3 = new Team(new TeamView(), "Team three");

    Car car1 = new Car(new CarView(), "Car 1");
    Car car2 = new Car(new CarView(), "Car 2");
    Car car3 = new Car(new CarView(), "Car 3");

    Truck truck1 = new Truck(new TruckView(), "Truck 1");
    Truck truck2 = new Truck(new TruckView(), "Truck 2");
    Truck truck3 = new Truck(new TruckView(), "Truck 3");

    Race race = new Race(new RaceView());

    DragZone dragger = new DragZoneImpl(new DragZoneView());
    dragger.setSize(800, 800);

    team1.go(dragger);
    team2.go(dragger);
    team3.go(dragger);

    VerticalPanel teamPanel = new VerticalPanel();
    team1.go(teamPanel);
    team2.go(teamPanel);
    team3.go(teamPanel);

    dragger.addWidget(teamPanel, 10, 10);
    dragger.registerDropZoneRoot(teamPanel);

    car1.go(dragger);
    car2.go(dragger);
    car3.go(dragger);

    truck1.go(dragger);
    truck2.go(dragger);
    truck3.go(dragger);

    VerticalPanel carPanel = new VerticalPanel();
    car1.go(carPanel);
    car2.go(carPanel);
    car3.go(carPanel);

    truck1.go(carPanel);
    truck2.go(carPanel);
    truck3.go(carPanel);

    dragger.addWidget(carPanel, 250, 10);

    VerticalPanel racePanel = new VerticalPanel();
    race.go(racePanel);

    dragger.addWidget(racePanel, 360, 10);
    dragger.registerDropZoneRoot(racePanel);

    dragger.go(RootPanel.get());
  }
}
