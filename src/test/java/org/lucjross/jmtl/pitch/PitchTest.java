package test.java.org.lucjross.jmtl.pitch;

import main.java.org.lucjross.jmtl.interval.Interval;
import main.java.org.lucjross.jmtl.interval.IntervalNumber;
import main.java.org.lucjross.jmtl.interval.IntervalQuality;
import main.java.org.lucjross.jmtl.pitch.Accidental;
import main.java.org.lucjross.jmtl.pitch.PitchClass;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lucas on 11/14/2014.
 */
public class PitchTest {



    @Test
    public void testRaiseAndLower()
    {
        Interval m6 = new Interval(IntervalQuality.MINOR, IntervalNumber.SIXTH);
        PitchClass gSharp = PitchClass.G.alter(Accidental.SHARP);
        Assert.assertEquals(PitchClass.E, gSharp.raiseBy(m6));

        Interval d4 = new Interval(IntervalQuality.P_DIM, IntervalNumber.FOURTH);
        Assert.assertEquals(PitchClass.C, gSharp.raiseBy(d4));

        Interval M9 = new Interval(IntervalQuality.MAJOR, IntervalNumber.NINTH);
        Assert.assertEquals(PitchClass.A.alter(Accidental.SHARP), gSharp.raiseBy(M9));

        PitchClass aFlat = PitchClass.A.alter(Accidental.FLAT);
        Assert.assertEquals(PitchClass.C, aFlat.lowerBy(m6));

        Assert.assertEquals(PitchClass.E, aFlat.lowerBy(d4));

        Assert.assertEquals(PitchClass.G.alter(Accidental.FLAT), aFlat.lowerBy(M9));


        
        /* nonsensical operations */

        PitchClass bDoubleSharp = PitchClass.B.alter(Accidental.DOUBLE_SHARP);
        try
        {
            bDoubleSharp.raiseBy(M9);
            Assert.fail();
        }
        catch (IllegalArgumentException e)
        {
            // ok
        }

        PitchClass fDoubleFlat = PitchClass.F.alter(Accidental.DOUBLE_FLAT);
        Interval M3 = new Interval(IntervalQuality.MAJOR, IntervalNumber.THIRD);
        try
        {
            fDoubleFlat.lowerBy(M3);
            Assert.fail();
        }
        catch (IllegalArgumentException e)
        {
            // ok
        }
    }
}
