package test.java.org.lucjross.jmtl.pitch;

import main.java.org.lucjross.jmtl.interval.Interval;
import main.java.org.lucjross.jmtl.interval.IntervalNumber;
import main.java.org.lucjross.jmtl.interval.IntervalQuality;
import main.java.org.lucjross.jmtl.pitch.Accidental;
import main.java.org.lucjross.jmtl.pitch.Pitch;
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
        Pitch gSharp = Pitch.G.alter(Accidental.SHARP);
        Assert.assertEquals(Pitch.E, gSharp.raiseBy(m6));

        Interval d4 = new Interval(IntervalQuality.P_DIM, IntervalNumber.FOURTH);
        Assert.assertEquals(Pitch.C, gSharp.raiseBy(d4));

        Interval M9 = new Interval(IntervalQuality.MAJOR, IntervalNumber.NINTH);
        Assert.assertEquals(Pitch.A.alter(Accidental.SHARP), gSharp.raiseBy(M9));

        Pitch aFlat = Pitch.A.alter(Accidental.FLAT);
        Assert.assertEquals(Pitch.C, aFlat.lowerBy(m6));

        Assert.assertEquals(Pitch.E, aFlat.lowerBy(d4));

        Assert.assertEquals(Pitch.G.alter(Accidental.FLAT), aFlat.lowerBy(M9));


        
        /* nonsensical operations */

        Pitch bDoubleSharp = Pitch.B.alter(Accidental.DOUBLE_SHARP);
        try
        {
            bDoubleSharp.raiseBy(M9);
            Assert.fail();
        }
        catch (IllegalArgumentException e)
        {
            // ok
        }

        Pitch fDoubleFlat = Pitch.F.alter(Accidental.DOUBLE_FLAT);
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
