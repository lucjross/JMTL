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
//    @Test
//    public void test()
//    {
//        Set<Set<Interval>> equivalencies = new HashSet<>();
//        for (Interval interval : Interval.values())
//        {
//            Set<Interval> equivalents = interval.getEnharmonicEquivalents();
//            equivalencies.add(equivalents);
//        }
//        Assert.assertEquals(equivalencies.size(), Interval.ENHARMONIC_EQUIVALENCIES.size());
//    }

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
    public void testInversion()
    {
        Assert.assertEquals(new Interval(IntervalQuality.P_AUG, IntervalNumber.FOURTH),
                new Interval(IntervalQuality.P_DIM, IntervalNumber.FIFTH).inversion());
        Assert.assertEquals(new Interval(IntervalQuality.P_DIM, IntervalNumber.OCTAVE),
                new Interval(IntervalQuality.P_AUG, IntervalNumber.UNISON).inversion());
        Assert.assertEquals(new Interval(IntervalQuality.P_DIM, IntervalNumber.UNISON),
                new Interval(IntervalQuality.P_AUG, IntervalNumber.OCTAVE).inversion());
        Assert.assertEquals(new Interval(IntervalQuality.P_DIM, IntervalNumber.FOURTH),
                new Interval(IntervalQuality.P_AUG, IntervalNumber.FIFTH).inversion());
        Assert.assertEquals(new Interval(IntervalQuality.MM_DIM, IntervalNumber.SEVENTH),
                new Interval(IntervalQuality.MM_AUG, IntervalNumber.SECOND).inversion());
        Assert.assertEquals(new Interval(IntervalQuality.MINOR, IntervalNumber.NINTH),
                new Interval(IntervalQuality.MAJOR, IntervalNumber.FOURTEENTH).inversion());
        Assert.assertEquals(new Interval(IntervalQuality.MM_DIM, IntervalNumber.TENTH),
                new Interval(IntervalQuality.MM_AUG, IntervalNumber.THIRTEENTH).inversion());
        Assert.assertEquals(new Interval(IntervalQuality.P_AUG, IntervalNumber.OCTAVE),
                new Interval(IntervalQuality.P_DIM, IntervalNumber.FIFTEENTH).inversion());
    }
}
