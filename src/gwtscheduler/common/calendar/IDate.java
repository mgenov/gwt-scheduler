package gwtscheduler.common.calendar;

import java.io.Serializable;

/**
 * Defines a date representation.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface IDate extends Comparable<IDate>, Serializable, CalendarConstants {

	/**
	 * Creates a copy of this date.
	 * 
	 * @return this date
	 */
	IDate copy();

	/**
	 * Adds or subtracts a number of days from the current date.
	 * 
	 * @param amount the amount to add or subtract
	 * @return the modified instance
	 */
	IDate addDays(int amount);

	/**
	 * Adds or subtracts a number of months from the current date.
	 * 
	 * @param amount the amount to add or subtract
	 * @return the modified instance
	 */
	IDate addMonths(int amount);

	/**
	 * Adds or subtracts a number of years from the current date.
	 * 
	 * @param amount the amount to add or subtract
	 * @return the modified instance
	 */
	IDate addYears(int amount);

	/**
	 * Adds or subtracts a number of hours from the current date.
	 * 
	 * @param amount the amount to add or subtract
	 * @return the modified instance
	 */
	IDate addHours(int i);

	/**
	 * Returns the difference between this date and another date.
	 * 
	 * @param otherDate the other date to compare
	 * @return the difference
	 */
	ITimePeriod diff(IDate otherDate);

	/**
	 * Gets the year.
	 * 
	 * @return the year
	 */
	int year();

	/**
	 * Gets the month.
	 * 
	 * @return the month
	 */
	int month();

	/**
	 * Gets the day.
	 * 
	 * @return the day
	 */
	int days();

	/**
	 * Gets the hour.
	 * 
	 * @return the hour
	 */
	int hours();

	/**
	 * Gets the minute.
	 * 
	 * @return the minute
	 */
	int minutes();

	/**
	 * Gets the second.
	 * 
	 * @return the second
	 */
	int seconds();

	/**
	 * Gets the underlying delegate.
	 * 
	 * @return the delegate
	 */
	Object delegate();

}
