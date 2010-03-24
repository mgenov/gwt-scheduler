package dragndrop.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import dragndrop.client.core.DragZone;
import dragndrop.client.core.Zones;
import dragndrop.client.teamexample.*;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragNDrop implements EntryPoint {
  public void onModuleLoad() {
    // Generate presenters and widgets that will be showed on the screen
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

    /**
     * Create drag zone with size 800x800 px.
     */
    DragZone dragZone = Zones.getDragZone();
    dragZone.setSize(800, 800);

    // make teams draggable. Team widgets implements DropZone interface. Presenter only attach DropHandler to the widget.
    dragZone.add(team1,team2,team3);

    // makeDraggable teams to the panel where they will stay
    VerticalPanel teamPanel = new VerticalPanel();
    team1.go(teamPanel);
    team2.go(teamPanel);
    team3.go(teamPanel);

    // attach that panel over drag zone. Or to some other panel. But have on mind that dragging frame is visible over a drag zone.
    dragZone.add(teamPanel, 10, 10);
    dragZone.addDropZoneRoot(teamPanel); // register teamPanel that have drop zones attached to him.

    // make cars draggable
    dragZone.add(car1, car2, car3);

    // make trucks draggable
    truck1.go(dragZone);
    truck2.go(dragZone);
    truck3.go(dragZone);

    // attach cars and trucks to the panel
    VerticalPanel carPanel = new VerticalPanel();
    car1.go(carPanel);
    car2.go(carPanel);
    car3.go(carPanel);

    truck1.go(carPanel);
    truck2.go(carPanel);
    truck3.go(carPanel);

    dragZone.add(carPanel, 250, 10); // attach car panel to the drag zone view.

    VerticalPanel racePanel = new VerticalPanel();
    race.go(racePanel);

    dragZone.add(racePanel, 360, 10);
    dragZone.addDropZoneRoot(racePanel);
    dragZone.setFrameStyle("dragFrame");

    dragZone.go(RootPanel.get()); // attach drag zone to the root panel or another panel.
  }
}
