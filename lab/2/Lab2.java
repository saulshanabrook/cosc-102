import org.junit.Test;
import org.junit.Assert;

public class Lab2 {

    public static void main (String[] args) {
        //org.junit.runner.JUnitCore.main("Lab2");
        // You may find this site useful
        //http://www.epochconverter.com/date-and-time/daynumbers-by-year.php?year=2013
    }

    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(Date.MONTHS.length, Date.DAYS_IN_MONTH.length);
    }


    @Test
    public void testToString() throws Exception {
        Assert.assertEquals("JAN-1-2014", new Date(1, 1, 2014, Date.Format.US).toString());
        Assert.assertEquals("1-JAN-2014", new Date(1, 1, 2014, Date.Format.EN).toString());
    }

    @Test
    public void testToStringLong() throws Exception {
        Assert.assertEquals("January-1-2014", new Date(1, 1, 2014, Date.Format.US).toStringLong());
        Assert.assertEquals("1-January-2014", new Date(1, 1, 2014, Date.Format.EN).toStringLong());

    }

    @Test
    public void testIsLeapYear() throws Exception {
        Assert.assertTrue(new Date(1, 1, 2000).isLeapYear());
        Assert.assertFalse(new Date(1, 1, 2001).isLeapYear());
    }

    @Test
    public void testDayNumber() throws Exception {
        Assert.assertEquals(2, new Date(2, 1, 1980).dayNumber());
        Assert.assertEquals(83, new Date(23, 3, 1980).dayNumber());
    }

    @Test
    public void testDaysRemaining() throws Exception {
        Assert.assertEquals(355, new Date(10, 1, 2013).daysRemaining());
        Assert.assertEquals(283, new Date(23, 3, 2016).daysRemaining());
    }

    @Test
    public void testAdvanceDay() throws Exception {
        Date d = new Date(1, 1, 2014);
        d.advanceDay();
        Assert.assertEquals(new Date(2, 1, 2014), d);

        d = new Date(31, 1, 2014);
        d.advanceDay();
        Assert.assertEquals(new Date(1, 2, 2014), d);

        d = new Date(31, 12, 2014);
        d.advanceDay();
        Assert.assertEquals(new Date(1, 1, 2015), d);

        // now lets make sure leap years work
        d = new Date(28, 2, 2000);
        d.advanceDay();
        Assert.assertEquals(new Date(29, 2, 2000), d);
        d.advanceDay();
        Assert.assertEquals(new Date(1, 3, 2000), d);

        d = new Date(28, 2, 2014);
        d.advanceDay();
        Assert.assertEquals(new Date(1, 3, 2014), d);
    }

    @Test
    public void testCompare() throws Exception {
        // same dates should return 0
        Assert.assertEquals(0, new Date(1, 1, 2014).compare(new Date(1, 1, 2014)));

        // if the initial is before the argument date should return -1
        Assert.assertEquals(-1, new Date(1, 1, 2014).compare(new Date(2, 1, 2014)));
        Assert.assertEquals(-1, new Date(20, 1, 2014).compare(new Date(1, 2, 2014)));
        Assert.assertEquals(-1, new Date(10, 1, 2014).compare(new Date(1, 2, 2014)));

        // if the initial is after the argument date, should return 1
        Assert.assertEquals(1, new Date(2, 1, 2014).compare(new Date(1, 1, 2014)));
        Assert.assertEquals(1, new Date(1, 2, 2014).compare(new Date(20, 1, 2014)));
        Assert.assertEquals(1, new Date(1, 1, 2015).compare(new Date(1, 11, 2013)));

    }

    @Test
    public void testEquals() throws Exception {
        Assert.assertEquals(new Date(1, 1, 2014), new Date(1, 1, 2014));
        Assert.assertNotEquals(new Date(1, 1, 2014), new Date(1, 1, 2015));
        Assert.assertNotEquals(new Date(1, 1, 2014), new Date(1, 2, 2014));
        Assert.assertNotEquals(new Date(1, 1, 2014), new Date(2, 1, 2014));
    }

    @Test
    public void testDaysTo() throws Exception {
        // same date should be zero
        Assert.assertEquals(0, new Date(1, 1, 2014).daysTo(new Date(1, 1, 2014)));

        // month day and year should all work
        Assert.assertEquals(1, new Date(1, 1, 2014).daysTo(new Date(2, 1, 2014)));
        Assert.assertEquals(31, new Date(1, 1, 2014).daysTo(new Date(1, 2, 2014)));
        Assert.assertEquals(365, new Date(1, 1, 2014).daysTo(new Date(1, 1, 2015)));
        Assert.assertEquals(365, new Date(1, 1, 2014).daysTo(new Date(1, 1, 2015)));

        // leap years should work
        Assert.assertEquals(2, new Date(28, 2, 2000).daysTo(new Date(1, 3, 2000)));
        Assert.assertEquals(1, new Date(28, 2, 2001).daysTo(new Date(1, 3, 2001)));


        // used http://www.wolframalpha.com/input/?i=days+from+9%2F18%2F1900+to+9%2F18%2F2000 to find actual answer
        Assert.assertEquals(36525, new Date(18, 9, 1900).daysTo(new Date(18, 9, 2000)));

    }

}



class Date {

    //Each month names
    public static final String[] MONTHS  =
            {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    //Number of days in each month
    public static final int[] DAYS_IN_MONTH =
            {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private static final int LEAP_YEAR_MONTH = 2;
    private static final int DAYS_IN_LEAP_MONTH = 29;

    private static final int DAYS_IN_YEAR = 365;
    private static final int DAYS_IN_LEAP_YEAR = 366;

    // used the "generate equals and hasCode" function of IDEA to create these
    // from http://howtodoinjava.com/2012/10/09/working-with-hashcode-and-equals-methods-in-java/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Date date = (Date) o;

        if (day != date.day) return false;
        if (month != date.month) return false;
        if (year != date.year) return false;
        if (style != date.style) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = day;
        result = 31 * result + month;
        result = 31 * result + year;
        result = 31 * result + style.hashCode();
        return result;
    }

    //Enumerated types for format
    public static enum Format {US, EN}

    //Instance variables to hold the values of a date
    private int day, month, year;

    //Specify format to print date

    private Format style;


    //COMPLETE THE 2 CONSTRUCTORS
    // Constructor with a format parameter
    Date(int d, int m, int y, Format initFormat) {
        day = d;
        month = m;
        year = y;
        style = initFormat;
    }

    //Constructor with day, month and year parameters
    public Date(int d, int m, int y)   {
        this(d, m, y, Format.US);
    }


    //  Returns the String for the date based on its format value
    //with the month abbreviated
    public String toString() {
       return getString(true);
    }

    //Returns the String for the date based on its format value
    //with the month unabbreviated
    public String toStringLong() {
       return getString(false);
    }

    private String getString(boolean abrev) {
        String monthText = abrev ? monthText().substring(0, 3).toUpperCase() : monthText();
        switch (style){
            case US:
                return String.format("%s-%d-%d", monthText, day, year);
            case EN:
                return String.format("%d-%s-%d", day, monthText, year);
        }
        return null;

    }


    //Returns true if the date object is a leap year
    public boolean isLeapYear() {

        if (year % 4 != 0) {return false;}
        if (year % 100 != 0) {return true;}
        if (year % 400 != 0) {return false;}
        return true;
    }

    //Returns the day number in the year (out of 365/366) this date is.
    //   January 02, 1980   -> Day  2
    //   March 23, 2016     -> Day 83
    public int dayNumber() {
        Date lastDayOfLastYear= new Date(DAYS_IN_MONTH[MONTHS.length - 1], MONTHS.length, year - 1);
        return julianDay() - lastDayOfLastYear.julianDay();
    }

    //Returns the number of days remaining in the year from this date.
    //   January 10, 2013  -> 355 days remaining
    //   March 23, 2016    -> 283 days remaining
    public int daysRemaining()   {
        Date lastDayOfThisYear= new Date(DAYS_IN_MONTH[MONTHS.length - 1], MONTHS.length, year);
        return lastDayOfThisYear.julianDay() - julianDay();
    }

    //Advances this date forward by one day
    public void advanceDay() {
        if (lastDayOfMonth()) {
            if (lastMonthOfYear()) {
                year += 1;
                month = 1;
                day = 1;
            } else {
                month += 1;
                day = 1;
            }
        } else {
            day += 1;
        }
    }

    //Compares two dates to determine which comes earlier.
    //Returns
    //       -1 if this date is earlier than the argument date, d,
    //        1 if this date is later than d
    //        0 if the two dates are the same (logical equivalent)
    public int compare(Date d) {
        if (julianDay() < d.julianDay()) {return -1;}
        if (julianDay() > d.julianDay()) {return 1;}
        return 0;
    }


    //Returns the number of days between this date and the parameter date:
    //    as a positive number if this date comes after the parameter date
    //    as a negative number if this date comes before the parameter date.
    public int daysTo(Date d) {
        return d.julianDay() - julianDay();
    }


    // from http://stackoverflow.com/a/14278129/907060
    // http://en.wikipedia.org/wiki/Julian_day
    private int julianDay() {
        int a = (14 - month) / 12;
        int y = year + 4800 - a;
        int m = month + 12 * a - 3;
        int jdn = day + (153 * m + 2)/5 + DAYS_IN_YEAR*y + y/4 - y/100 + y/400 - 32045;
        return jdn;
    }

    private boolean lastDayOfMonth() {
        return day == getDaysInMonth();
    }

    private boolean lastMonthOfYear() {
        return month == MONTHS.length;
    }

    private int getDaysInMonth() {
        if ((month == LEAP_YEAR_MONTH) && isLeapYear()) {
            return DAYS_IN_LEAP_MONTH;
        }
        return DAYS_IN_MONTH[month - 1];
    }

    private String monthText() {
        return MONTHS[month - 1];
    }


}


