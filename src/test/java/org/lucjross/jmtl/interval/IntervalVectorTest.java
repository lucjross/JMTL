package test.java.org.lucjross.jmtl.interval;

import main.java.org.lucjross.jmtl.interval.Interval;
import main.java.org.lucjross.jmtl.interval.IntervalNumber;
import main.java.org.lucjross.jmtl.interval.IntervalQuality;
import main.java.org.lucjross.jmtl.interval.IntervalVector;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lucas on 11/18/2014.
 */
public class IntervalVectorTest
{
    @Test
    public void testNew()
    {
        List<Interval> openMm7 = new LinkedList<>();
        openMm7.add(new Interval(IntervalQuality.PERFECT, IntervalNumber.FIFTH));
        openMm7.add(new Interval(IntervalQuality.MAJOR, IntervalNumber.SIXTH));
        openMm7.add(new Interval(IntervalQuality.P_DIM, IntervalNumber.FIFTH));

        List<Interval> closedMm7 = new LinkedList<>();
        closedMm7.add(new Interval(IntervalQuality.MAJOR, IntervalNumber.THIRD));
        closedMm7.add(new Interval(IntervalQuality.MINOR, IntervalNumber.THIRD));
        closedMm7.add(new Interval(IntervalQuality.MINOR, IntervalNumber.THIRD));

        IntervalVector Mm7 = new IntervalVector(openMm7);
        Assert.assertArrayEquals(closedMm7.toArray(), Mm7.toArray());



        Interval[] openM = new Interval[] {
                new Interval(IntervalQuality.MAJOR, IntervalNumber.TENTH),
                new Interval(IntervalQuality.MINOR, IntervalNumber.THIRD),
                new Interval(IntervalQuality.PERFECT, IntervalNumber.FOURTH)
        };

        Interval[] closedM = new Interval[] {
                new Interval(IntervalQuality.MAJOR, IntervalNumber.THIRD),
                new Interval(IntervalQuality.MINOR, IntervalNumber.THIRD)
        };

        IntervalVector M = new IntervalVector(openM);
        Assert.assertArrayEquals(closedM, M.toArray());

    }
}
