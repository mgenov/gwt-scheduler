package gwtscheduler.common.event;

/**
 * Defines a date representation.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface IDate extends Comparable<IDate> {

	public static final int MINUTE = 1;
	public static final int SECOND = 2;
	public static final int MILLISECOND = 3;

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
	 * Returns the difference between this date and another date.
	 * 
	 * @param otherDate the other date to compare
	 * @return the difference
	 */
	int diff(IDate otherDate);

	/**
	 * Returns the difference between this date and another date.
	 * 
	 * @param otherDate the other date to compare
	 * @param unit the difference unit
	 * @return the difference
	 */
	int diff(IDate otherDate, TimeUnit unit);

	/**
	 * Gets the underlying delegate.
	 * 
	 * @return the delegate
	 */
	Object delegate();
}
