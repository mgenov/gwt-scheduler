package gwtscheduler.client.widgets.view.day;

import com.google.gwt.user.client.ui.Widget;

import gwtscheduler.client.widgets.view.common.AbstractCompositeDayView;
import gwtscheduler.client.widgets.view.common.AbstractDayView;
import gwtscheduler.client.widgets.view.common.HorizontalGridFill;

/**
 *
 */
public class CompositeDayView extends AbstractCompositeDayView {

	@Override
	protected AbstractDayView createDayView() {
		return new DayView();
	}

    @Override
    protected Widget createTopView(int columns) {
        return new HorizontalGridFill(1, columns);
//        return labels;
//        return super.createTopView(columns);
    }
	
	

}
