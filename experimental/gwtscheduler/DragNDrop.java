package gwtscheduler;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import gwtscheduler.client.DragNDropView;
import gwtscheduler.client.DragNDropPresenter;
import gwtscheduler.client.dragndrop.DraggerImpl;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragNDrop implements EntryPoint {
  public void onModuleLoad() {
    AbsolutePanel absolutePanel = new AbsolutePanel();
    absolutePanel.setPixelSize(500, 500);

    DragNDropView dragNDropWidget1 = new DragNDropView();
    DragNDropPresenter dragNDropPresenter1 = new DragNDropPresenter(dragNDropWidget1);

    DragNDropView dragNDropWidget2 = new DragNDropView();
    DragNDropPresenter dragNDropPresenter2 = new DragNDropPresenter(dragNDropWidget2);

    DraggerImpl dragger = new DraggerImpl(absolutePanel);
    dragger.registerDraggable(dragNDropWidget1, dragNDropPresenter1);
    dragger.registerDraggable(dragNDropWidget2, dragNDropPresenter2);

    absolutePanel.add(dragNDropWidget1, 50, 50);
    absolutePanel.add(dragNDropWidget2, 50, 100);

    RootPanel.get().add(absolutePanel);
  }
}
