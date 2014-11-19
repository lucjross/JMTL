package test.java.org.lucjross.jmtl.interval;

import main.java.org.lucjross.jmtl.interval.Interval;
import main.java.org.lucjross.jmtl.interval.IntervalNumber;
import main.java.org.lucjross.jmtl.interval.IntervalQuality;
import main.java.org.lucjross.jmtl.interval.ClosedIntervalSequence;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lucas on 11/18/2014.
 */
public class ClosedIntervalSequenceTest
{
    @Test
    public void testNew()
    {
        Interval[] openMm7 = new Interval[] {
                new Interval(IntervalQuality.PERFECT, IntervalNumber.FIFTH),
                new Interval(IntervalQuality.MAJOR, IntervalNumber.SIXTH),
                new Interval(IntervalQuality.P_DIM, IntervalNumber.FIFTH)
        };

        Interval[] closedMm7 = new Interval[] {
                new Interval(IntervalQuality.MAJOR, IntervalNumber.THIRD),
                new Interval(IntervalQuality.MINOR, IntervalNumber.THIRD),
                new Interval(IntervalQuality.MINOR, IntervalNumber.THIRD)
        };

        ClosedIntervalSequence Mm7 = new ClosedIntervalSequence(openMm7);
        Assert.assertArrayEquals(closedMm7, Mm7.toArray());

        Interval[] openM = new Interval[] {
                new Interval(IntervalQuality.MAJOR, IntervalNumber.TENTH),
                new Interval(IntervalQuality.MINOR, IntervalNumber.THIRD),
                new Interval(IntervalQuality.PERFECT, IntervalNumber.FOURTH)
        };

        Interval[] closedM = new Interval[] {
                new Interval(IntervalQuality.MAJOR, IntervalNumber.THIRD),
                new Interval(IntervalQuality.MINOR, IntervalNumber.THIRD)
        };

        ClosedIntervalSequence M = new ClosedIntervalSequence(openM);
        Assert.assertArrayEquals(closedM, M.toArray());

    }
}
