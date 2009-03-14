package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.resources.css.DayWeekCss;
import gwtscheduler.client.resources.css.Resources;
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

        DayWeekCss commonCss = Resources.DayWeekCss();
        setStyleName(this.row % 2 == 0 ? commonCss.evenCell() : commonCss.oddCell());

        DebugUtils.textRight("" + this.row + "," + this.col, getElement());
    }

    /**
     * Overrides the default pixel size to take in account borders.
     * 
     * @see com.google.gwt.user.client.ui.UIObject#setPixelSize(int, int)
     */
    @Override
    public void setPixelSize(int w, int h) {
        DayWeekCss css = Resources.DayWeekCss();
        // width - border - padding
        // height - border
        super.setPixelSize(w - css.mediumBorderPx() - css.smallPaddingPx(), h - css.smallBorderPx());
    }
}
