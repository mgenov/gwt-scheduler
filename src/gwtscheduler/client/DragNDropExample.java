package gwtscheduler.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragNDropExample implements EntryPoint {

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
//
//    EmployeeEntry john = new EmployeeEntry(new EmployeeEntryView());
//    john.setEmployeeName("John The Ripper");
//    john.go(absolutePanel, 70,120);
//
//
//    EmployeeEntryView jackView = new EmployeeEntryView();
//    EmployeeEntry jack = new EmployeeEntry(jackView);
//    jack.setEmployeeName("Bad Jack");
//
//    john.go(absolutePanel, 70, 90);
//    jack.go(absolutePanel, 65, 120);
//
//    DragZoneImpl dragger = new DragZoneImpl(absolutePanel);
//    dragger.registerDraggable(dragNDropWidget1, dragNDropPresenter1);
//    dragger.registerDraggable(dragNDropWidget2, dragNDropPresenter2);
////    dragger.registerDraggable(jackView, jack);
//    jack.go(dragger);
//
//    absolutePanel.add(dragNDropWidget1, 50, 50);
//    absolutePanel.add(dragNDropWidget2, 50, 100);
//
//
//    DragController dragController = new DragController(null);
//    jack.go(dragController);
//    john.go(dragController);
//
//
//
//
//    RootPanel.get().add(absolutePanel);
//  }
//
//  static class DragController implements HasWidgets{
//
//    interface Display {
//      public void add(Widget w);
//    }
//
//    private final Provider<Display> display;
//
//
//    public DragController(Provider<Display> display) {
//      this.display = display;
//    }
//
//    @Override
//    public void add(Widget w) {
//      Display nextDragDisplay = display.get();
//      // wrap the provided widget into nextDragDisplay
//
//      nextDragDisplay.add(w);
//
//
//
//    }
//
//    @Override
//    public void clear() {
//
//    }
//
//    @Override
//    public Iterator<Widget> iterator() {
//      return null;
//    }
//
//    @Override
//    public boolean remove(Widget w) {
//      return false;
//    }
//  }
//
//
//  static class Team {
//
//    public String getName() {
//      return "";
//    }
//  }
//
//  static class TeamFilter {
//
//    interface Display {
//
//    }
//
//    private final TitleDragZone[] teamNames;
//
//    private ArrayList<Team> teams = new ArrayList<Team>();
//
//    public TeamFilter(TitleDragZone[] teamNames) {
//      this.teamNames = teamNames;
//    }
//
//    public void bindDisplay(Display display) {
//
//      TitleDropHandler dropHandler = new TitleDropHandler();
//
//      for (TitleDragZone t : teamNames) {
//        t.addDropHandler(dropHandler);
//      }
//
//    }
//
//    class TitleDropHandler implements DropHandler {
//
//      @Override
//      public void onDrop(DropEvent event) {
//        TitleDragZone td = (TitleDragZone) event.getDroppedObject();
//        String title = td.getTitle();
//        Team t = getTeamByName(title);
//
//      }
//    }
//
//    private Team getTeamByName(String title) {
//      for (Team t : teams) {
//        if (t.getName().equals(title)) {
//          return t;
//        }
//      }
//
//      return null;
//    }
//
//
//  }
//
//  static class TitleDragZone {
//
//    interface Display extends DropZone{
//       HasText getDragTitle();
//    }
//
//    public TitleDragZone(String title, Display display) {
//
//    }
//
//    public void addDropHandler(TitleDropHandler dropHandler) {
//    }
//  }
//
//  static class TitleDragZoneView extends Draggable implements TitleDragZone.Display{
//
//    @Override
//    public void addDropHandler(DropHandler handler) {
//       addHandler(handler, DropEvent.TYPE);
//    }
//
//    @Override
//    public void addDragOverHandler(DragInHandler handler) {
//
//    }
//
//    @Override
//    public HasText getDragTitle() {
//      return null;
//    }
//  }
//
//  static class EmployeeEntry {
//
//    interface Display extends DropZone{
//       public HasText getEmployeeName();
//    }
//
//    private final Display display;
//    private String employeeName;
//    private int dropCount = 0;
//
//    public EmployeeEntry(final Display display) {
//      this.display = display;
//
//      display.addDropHandler(new DropHandler() {
//        @Override
//        public void onDrop(DropEvent event) {
//          display.getEmployeeName().setText(employeeName + ":" + dropCount);
//          dropCount++;
//        }
//      });
//    }
//
//    public void setEmployeeName(String name) {
//      this.employeeName = name;
//      display.getEmployeeName().setText(name);
//    }
//
//    public void go(AbsolutePanel parent, int left, int top) {
//      parent.add((Widget)display, left, top);
//    }
//
//    public Widget asWidget() {
//      return (Widget) display;
//    }
//
//    public void go(HasWidgets controller) {
//      controller.add((Widget)display);
//    }
//  }
//  static class EmployeeEntryView extends Draggable implements EmployeeEntry.Display {
//    private Label label = new Label("not set");
//
//
//    public EmployeeEntryView() {
//       initWidget(label);
//    }
//
//    private void initWidget(Label label) {
//    }
//
//    @Override
//    public void addDropHandler(DropHandler handler) {
//      addHandler(handler, DropEvent.TYPE);
//    }
//
//    @Override
//    public void addDragOverHandler(DragInHandler handler) {
//
//    }
//
//    @Override
//    public HasText getEmployeeName() {
//      return label;
//    }
  }

}

