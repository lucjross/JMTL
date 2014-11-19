package test.java.org.lucjross.jmtl.chord;

import main.java.org.lucjross.jmtl.chord.ChordInversion;
import main.java.org.lucjross.jmtl.interval.Interval;
import main.java.org.lucjross.jmtl.interval.IntervalNumber;
import main.java.org.lucjross.jmtl.interval.IntervalQuality;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lucas on 11/18/2014.
 */
public class ChordInversionTest
{
    @Test
    public void testForIntervals()
    {
        Interval[] ambiguous1 = new Interval[] {
                new Interval(IntervalQuality.MAJOR, IntervalNumber.SIXTH)
        };
        Assert.assertNull(ChordInversion.forIntervals(ambiguous1));

        Interval[] ambiguous2 = new Interval[] {
                new Interval(IntervalQuality.MINOR, IntervalNumber.SIXTH),
                new Interval(IntervalQuality.MAJOR, IntervalNumber.THIRD)
        };
        Assert.assertNull(ChordInversion.forIntervals(ambiguous2));

        Interval[] Mm7 = new Interval[] {
                new Interval(IntervalQuality.PERFECT, IntervalNumber.FIFTH),
                new Interval(IntervalQuality.MAJOR, IntervalNumber.SIXTH),
                new Interval(IntervalQuality.P_DIM, IntervalNumber.FIFTH)
        };
        Assert.assertEquals(ChordInversion.ROOT_POSITION,
                ChordInversion.forIntervals(Mm7));

        Interval[] Mm7_noThird = new Interval[] {
                new Interval(IntervalQuality.MAJOR, IntervalNumber.TENTH),
                new Interval(IntervalQuality.P_DIM, IntervalNumber.FIFTH)
        };
        Assert.assertEquals(ChordInversion.ROOT_POSITION,
                ChordInversion.forIntervals(Mm7_noThird));

        Interval[] MM65 = new Interval[] {
                new Interval(IntervalQuality.MINOR, IntervalNumber.SIXTH),
                new Interval(IntervalQuality.MAJOR, IntervalNumber.SEVENTH),
                new Interval(IntervalQuality.MINOR, IntervalNumber.SIXTH)
        };
        Assert.assertEquals(ChordInversion.FIRST_INVERSION,
                ChordInversion.forIntervals(MM65));

        Interval[] MM65_noFifth = new Interval[] {
                new Interval(IntervalQuality.MINOR, IntervalNumber.SIXTH),
                new Interval(IntervalQuality.MAJOR, IntervalNumber.SEVENTH)
        };
        Assert.assertEquals(ChordInversion.FIRST_INVERSION,
                ChordInversion.forIntervals(MM65_noFifth));

        Interval[] hd43 = new Interval[] {
                new Interval(IntervalQuality.P_AUG, IntervalNumber.FOURTH),
                new Interval(IntervalQuality.MINOR, IntervalNumber.SEVENTH),
                new Interval(IntervalQuality.PERFECT, IntervalNumber.FOURTH)
        };
        Assert.assertEquals(ChordInversion.SECOND_INVERSION,
                ChordInversion.forIntervals(hd43));

        Interval[] hd42_noThird = new Interval[] {
                new Interval(IntervalQuality.MINOR, IntervalNumber.SIXTH),
                new Interval(IntervalQuality.P_AUG, IntervalNumber.FOURTH)
        };
        Assert.assertEquals(ChordInversion.THIRD_INVERSION,
                ChordInversion.forIntervals(hd42_noThird));

        Interval[] invalid1 = new Interval[] {
                new Interval(IntervalQuality.MAJOR, IntervalNumber.SECOND),
                new Interval(IntervalQuality.MINOR, IntervalNumber.NINTH)
        };
        Assert.assertNull(ChordInversion.forIntervals(invalid1));

        Interval[] invalid2 = new Interval[] {
                new Interval(IntervalQuality.P_AUG, IntervalNumber.FOURTH),
                new Interval(IntervalQuality.MINOR, IntervalNumber.SEVENTH),
                new Interval(IntervalQuality.PERFECT, IntervalNumber.FOURTH),
                new Interval(IntervalQuality.MINOR, IntervalNumber.SECOND)
        };
        Assert.assertNull(ChordInversion.forIntervals(invalid2));

        Interval[] invalid3 = new Interval[] {
                new Interval(IntervalQuality.PERFECT, IntervalNumber.FIFTH),
                new Interval(IntervalQuality.PERFECT, IntervalNumber.FIFTH)
        };
        Assert.assertNull(ChordInversion.forIntervals(invalid3));
    }
}
