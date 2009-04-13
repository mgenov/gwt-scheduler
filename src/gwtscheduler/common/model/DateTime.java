package gwtscheduler.common.model;

import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;

import java.util.Date;

/**
 * Simple date class.
 */
@SuppressWarnings("deprecation")
public class DateTime implements IDate, Comparable<IDate> {

	/** Internal date field. */
	private Date delegate;

	/**
	 * Default constructor.
	 */
	public DateTime() {
		delegate = new Date();
	}

	public IDate copy() {
		DateTime dt = new DateTime();
		dt.delegate.setTime(delegate.getTime());
		return dt;
	}

	public Object delegate() {
		return delegate;
	}

	public int days() {
		return delegate.getDate();
	}

	public int hours() {
		return delegate.getHours();
	}

	public int minutes() {
		return delegate.getMinutes();
	}

	public int month() {
		return delegate.getMonth();
	}

	public int seconds() {
		return delegate.getSeconds();
	}

	public int year() {
		return delegate.getDate();
	}

	public IDate addHours(int amount) {
		delegate.setHours(delegate.getHours() + amount);
		return this;
	}

	public IDate addDays(int amount) {
		delegate.setDate(delegate.getDate() + amount);
		return this;
	}

	public IDate addMonths(int amount) {
		delegate.setMonth(delegate.getMonth() + amount);
		return this;
	}

	public IDate addYears(int amount) {
		delegate.setYear(delegate.getYear() + amount);
		return this;
	}

	public ITimePeriod diff(IDate otherDate) {
		long diff = Math.abs(delegate.getTime() - ((DateTime) otherDate).delegate.getTime());
		return new TimePeriod(diff);
	}

	public int compareTo(IDate o) {
		return delegate.compareTo((Date) o.delegate());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DateTime other = (DateTime) obj;
		if (delegate == null) {
			if (other.delegate != null) {
				return false;
			}
		} else if (!delegate.equals(other.delegate)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((delegate == null) ? 0 : delegate.hashCode());
		return result;
	}

	/**
	 * Truncates this date instance.
	 * 
	 * @param field the field to truncate
	 * @return this instance
	 */
	protected IDate truncate(int field) {
		boolean done = false;
		long time = delegate.getTime();
		long displacement = 0;
		if (MILLISECOND == field) {
			done = true;
		}
		if (!done && SECOND == field) {
			displacement = 60 * 1000;
			done = true;
		}
		if (!done && MINUTE == field) {
			displacement = 60 * 1000 * 60;
			done = true;
		}
		if (!done && HOUR == field) {
			displacement = 60 * 1000 * 60 * 60;
			done = true;
		}
		// some calcs
		time = time - (time % displacement);
		delegate.setTime(time);
		return this;
	}

}
