package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.IEventNavigationListener;
import gwtscheduler.client.modules.views.ICalendarController;

import com.google.gwt.user.client.ui.Widget;

/**
 * Abstract class for view controllers.
 * 
 * @author malp
 */
public abstract class AbstractViewController<T extends Widget> implements ICalendarController,
		IEventNavigationListener {

	private T view;

	/**
	 * Default constructor.
	 */
	protected AbstractViewController() {
		view = createView();
	}

	/**
	 * @return
	 */
	protected T getView() {
		return view;
	}

	/**
	 * This method is responsibel for creating the view.
	 */
	protected abstract T createView();

	public IEventNavigationListener getNavigationListener() {
		return this;
	}

	public Widget getViewWidget() {
		return view;
	}

}
