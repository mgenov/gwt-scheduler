package gwtscheduler.client.interfaces.events;

/**
 * Resize event for resize aware widgets.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class WidgetResizeEvent {

	public final int width;
	public final int height;

	/**
	 * Main constructor.
	 * 
	 * @param availableWidth the available width
	 * @param availableHeight available height
	 */
	public WidgetResizeEvent(int availableWidth, int availableHeight) {
		this.width = availableWidth;
		this.height = availableHeight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WidgetResizeEvent other = (WidgetResizeEvent) obj;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		return true;
	}
}
