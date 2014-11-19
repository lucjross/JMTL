package test.java.org.lucjross.jmtl.interval;

import main.java.org.lucjross.jmtl.interval.Interval;
import main.java.org.lucjross.jmtl.interval.IntervalNumber;
import main.java.org.lucjross.jmtl.interval.IntervalQuality;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lucas on 11/14/2014.
 */
public class IntervalTest
{
    @Test
    public void testToSimple()
    {
        Assert.assertEquals(new Interval(IntervalQuality.PERFECT, IntervalNumber.FOURTH),
                new Interval(IntervalQuality.PERFECT, IntervalNumber.FOURTH).toSimple());
        Assert.assertEquals(new Interval(IntervalQuality.MM_AUG, IntervalNumber.THIRD),
                new Interval(IntervalQuality.MM_AUG, IntervalNumber.TENTH).toSimple());
        Assert.assertEquals(new Interval(IntervalQuality.MINOR, IntervalNumber.SECOND),
                new Interval(IntervalQuality.MINOR, IntervalNumber.NINTH).toSimple());
    }

    @Test
    public void testToInversion()
    {
        Assert.assertEquals(new Interval(IntervalQuality.P_AUG, IntervalNumber.FOURTH),
                new Interval(IntervalQuality.P_DIM, IntervalNumber.FIFTH).toInversion());
        Assert.assertEquals(new Interval(IntervalQuality.P_DIM, IntervalNumber.OCTAVE),
                new Interval(IntervalQuality.P_AUG, IntervalNumber.UNISON).toInversion());
        Assert.assertEquals(new Interval(IntervalQuality.P_DIM, IntervalNumber.UNISON),
                new Interval(IntervalQuality.P_AUG, IntervalNumber.OCTAVE).toInversion());
        Assert.assertEquals(new Interval(IntervalQuality.P_DIM, IntervalNumber.FOURTH),
                new Interval(IntervalQuality.P_AUG, IntervalNumber.FIFTH).toInversion());
        Assert.assertEquals(new Interval(IntervalQuality.MM_DIM, IntervalNumber.SEVENTH),
                new Interval(IntervalQuality.MM_AUG, IntervalNumber.SECOND).toInversion());
        Assert.assertEquals(new Interval(IntervalQuality.MINOR, IntervalNumber.NINTH),
                new Interval(IntervalQuality.MAJOR, IntervalNumber.FOURTEENTH).toInversion());
        Assert.assertEquals(new Interval(IntervalQuality.MM_DIM, IntervalNumber.TENTH),
                new Interval(IntervalQuality.MM_AUG, IntervalNumber.THIRTEENTH).toInversion());
        Assert.assertEquals(new Interval(IntervalQuality.P_AUG, IntervalNumber.OCTAVE),
                new Interval(IntervalQuality.P_DIM, IntervalNumber.FIFTEENTH).toInversion());
    }

    @Test
    public void testAddAndSubtract()
    {
        Interval i = new Interval(IntervalQuality.MAJOR, IntervalNumber.SEVENTH);

        Assert.assertEquals(i.add(new Interval(IntervalQuality.MAJOR, IntervalNumber.SECOND)),
                new Interval(IntervalQuality.P_AUG, IntervalNumber.OCTAVE));
        Assert.assertEquals(i.add(new Interval(IntervalQuality.MINOR, IntervalNumber.THIRD)),
                new Interval(IntervalQuality.MAJOR, IntervalNumber.NINTH));

        Assert.assertEquals(i.subtract(new Interval(IntervalQuality.MAJOR, IntervalNumber.SECOND)),
                new Interval(IntervalQuality.MAJOR, IntervalNumber.SIXTH));
        Assert.assertEquals(i.subtract(new Interval(IntervalQuality.MINOR, IntervalNumber.THIRD)),
                new Interval(IntervalQuality.P_AUG, IntervalNumber.FIFTH));

        try
        {
            i.add(new Interval(IntervalQuality.MAJOR, IntervalNumber.TENTH));
            Assert.fail();
        }
        catch (IllegalArgumentException e)
        {
            // ok
        }

        try
        {
            i.subtract(new Interval(IntervalQuality.PERFECT, IntervalNumber.OCTAVE));
            Assert.fail();
        }
        catch (IllegalArgumentException e)
        {
            // ok
        }
    }
}
