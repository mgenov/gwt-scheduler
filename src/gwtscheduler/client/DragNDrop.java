package gwtscheduler.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import gwtscheduler.client.dragndrop.DragZoneImpl;
import gwtscheduler.client.teamexample.*;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragNDrop implements EntryPoint {
  public void onModuleLoad() {
//    AbsolutePanel absolutePanel = new AbsolutePanel();
//    absolutePanel.setPixelSize(500, 500);
//
//    DragNDropView dragNDropWidget1 = new DragNDropView();
//    DragNDropPresenter dragNDropPresenter1 = new DragNDropPresenter(dragNDropWidget1);
//
//    DragNDropView dragNDropWidget2 = new DragNDropView();
//    DragNDropPresenter dragNDropPresenter2 = new DragNDropPresenter(dragNDropWidget2);
//
//    DragZoneImpl dragger = new DragZoneImpl(absolutePanel);
//    dragNDropPresenter1.go(dragger, 50, 50);
//    dragNDropPresenter2.go(dragger, 50, 100);

    AbsolutePanel absolutePanel = new AbsolutePanel();
    absolutePanel.setPixelSize(800, 800);
    Team team1 = new Team(new TeamView());
    team1.setTeamName("Team one");

    Team team2 = new Team(new TeamView());
    team2.setTeamName("Team two");

    Team team3 = new Team(new TeamView());
    team3.setTeamName("Team three");

    Car car1 = new Car(new CarView(), "Car 1");
    Car car2 = new Car(new CarView(), "Car 2");
    Car car3 = new Car(new CarView(), "Car 3");

    Truck truck1 = new Truck(new TruckView(), "Truck 1");
    Truck truck2 = new Truck(new TruckView(), "Truck 2");
    Truck truck3 = new Truck(new TruckView(), "Truck 3");

    Race race = new Race(new RaceView());
    
    DragZoneImpl dragger = new DragZoneImpl(null);
    
    team1.go(dragger, 10, 10);
    team2.go(dragger, 10, 100);
    team3.go(dragger, 10, 190);

    car1.go(dragger, 250, 10);
    car2.go(dragger, 250, 100);
    car3.go(dragger, 250, 190);

    truck1.go(dragger, 250, 40);
    truck2.go(dragger, 250, 140);
    truck3.go(dragger, 250, 230);

    race.go(absolutePanel, 360, 10);

    RootPanel.get().add(absolutePanel);
  }
}
