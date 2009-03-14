package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.DebugUtils;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * Serves as a row label.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
class DayWeekCell extends Widget {

    /** Cell identifiers */
    private int row, col;

    /**
     * Creates a new label.
     * 
     * @param id the cell id
     * @param label the label
     */
    public DayWeekCell(int row, int col, String label) {
        this.row = row;
        this.col = col;
        Element div = DOM.createDiv();
        setElement(div);

        DayWeekCssResource css = Resources.dayWeekCss();
        setStyleName(this.row % 2 == 0 ? css.evenCell() : css.oddCell());

        DebugUtils.textRight("" + this.row + "," + this.col, getElement());
    }

    /**
     * Overrides the default pixel size to take in account borders.
     * 
     * @see com.google.gwt.user.client.ui.UIObject#setPixelSize(int, int)
     */
    @Override
    public void setPixelSize(int w, int h) {
        DayWeekCssResource css = Resources.dayWeekCss();
        // width - border - padding
        // height - border
        super.setPixelSize(w - css.mediumBorderPx() - css.smallPaddingPx(), h - css.smallBorderPx());
    }
}
