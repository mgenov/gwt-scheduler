package gwtscheduler.client.teamexample;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.dragndrop.Draggable;
import gwtscheduler.client.dragndrop.Dragger;
import gwtscheduler.client.dragndrop.DropEvent;
import gwtscheduler.client.dragndrop.DropHandler;
import gwtscheduler.client.dragndrop.DropZone;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class Team implements Draggable{
  public interface Display extends DropZone {
    HasText getTeamName();
    void addCarName(String carName);
    void addTrackName(String carName);
  }

  private Display display;
  private List<Car> cars = new ArrayList<Car>();
  private List<Truck> trucks = new ArrayList<Truck>();

  public Team(Display display) {
    this.display = display;

    display.addDropHandler(new DropHandler(){
      @Override
      public void onDrop(DropEvent event) {
        Object o = event.getDroppedObject();
        if(o instanceof Car){
          Car car = (Car)o;
          addCar(car);
          event.getWidget().removeFromParent();
        } else if(o instanceof Truck){
          Truck truck = (Truck)o;
          addTruck(truck);
          event.getWidget().removeFromParent();
        }
      }
    });
  }

  public void setTeamName(String teamName){
    display.getTeamName().setText(teamName);
  }

  public String getTeamName(){
    return display.getTeamName().getText();
  }

  public void addCar(Car car){
    cars.add(car);
    display.addCarName(car.getName());
  }

  public void addTruck(Truck truck){
    trucks.add(truck);
    display.addTrackName(truck.getName());
  }

  public List<Car> getCars(){
    return cars;
  }

  public List<Truck> getTrucks(){
    return trucks;
  }

  @Override
  public void go(Dragger dragger, int left, int top) {
    dragger.add((Widget)display, this, left, top);
  }
}
