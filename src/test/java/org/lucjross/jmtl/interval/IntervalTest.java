package test.java.org.lucjross.jmtl.interval;

import main.java.org.lucjross.jmtl.interval.Interval;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucas on 11/14/2014.
 */
public class IntervalTest
{
    @Test
    public void test()
    {
        Set<Set<Interval>> equivalencies = new HashSet<>();
        for (Interval interval : Interval.values())
        {
            Set<Interval> equivalents = interval.getEnharmonicEquivalents();
            equivalencies.add(equivalents);
        }
        Assert.assertEquals(equivalencies.size(), Interval.ENHARMONIC_EQUIVALENCIES.size());
    }

    @Test
    public void testToSimple()
    {
        Assert.assertEquals(Interval.FOURTH, Interval.FOURTH.toSimple());
        Assert.assertEquals(Interval.AUG_THIRD, Interval.AUG_TENTH.toSimple());
        Assert.assertEquals(Interval.MINOR_SECOND, Interval.MINOR_NINTH.toSimple());
    }

    @Test
    public void testToInversion()
    {
        Assert.assertEquals(Interval.FOURTH, Interval.FIFTH.toInversion());
        Assert.assertEquals(Interval.AUG_FOURTH, Interval.DIM_FIFTH.toInversion());
        Assert.assertEquals(Interval.MINOR_SECOND, Interval.MAJOR_SEVENTH.toInversion());
        Assert.assertEquals(Interval.DIM_SECOND, Interval.AUG_SEVENTH.toInversion());
        Assert.assertEquals(Interval.MINOR_SEVENTH, Interval.MAJOR_SECOND.toInversion());
        Assert.assertEquals(Interval.MINOR_NINTH, Interval.MAJOR_FOURTEENTH.toInversion());
        Assert.assertEquals(Interval.DIM_TENTH, Interval.AUG_THIRTEENTH.toInversion());
    }
}
