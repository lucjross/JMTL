package test.java.org.lucjross.jmtl.key;

import main.java.org.lucjross.jmtl.interval.Interval;
import main.java.org.lucjross.jmtl.interval.IntervalNumber;
import main.java.org.lucjross.jmtl.interval.IntervalQuality;
import main.java.org.lucjross.jmtl.key.Key;
import main.java.org.lucjross.jmtl.key.KeyQuality;
import main.java.org.lucjross.jmtl.pitch.Accidental;
import main.java.org.lucjross.jmtl.pitch.PitchClass;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lucas on 11/20/2014.
 */
public class KeyTest
{
    @Test
    public void testBasic()
    {
        try
        {
            PitchClass pc = PitchClass.B.alter(Accidental.SHARP);
            Key k = new Key(pc, KeyQuality.MAJ);
            Assert.fail();
        }
        catch (IllegalArgumentException e)
        {
            // ok
        }

        try
        {
            PitchClass pc = PitchClass.G.alter(Accidental.FLAT);
            Key k = new Key(pc, KeyQuality.MIN);
            Assert.fail();
        }
        catch (IllegalArgumentException e)
        {
            // ok
        }

        PitchClass fSharp = PitchClass.F.alter(Accidental.SHARP);
        Key fSharpMaj = new Key(fSharp, KeyQuality.MAJ);
        Assert.assertEquals(-1, fSharpMaj.flats());
        Assert.assertEquals(6, fSharpMaj.sharps());

        PitchClass f = PitchClass.F;
        Key fMin = new Key(f, KeyQuality.MIN);
        Assert.assertEquals(4, fMin.flats());
        Assert.assertEquals(-1, fMin.sharps());
    }

    @Test
    public void testModulation()
    {
        Key fMaj = new Key(PitchClass.F, KeyQuality.MAJ);
        Key dFlatMaj = new Key(PitchClass.D.alter(Accidental.FLAT), KeyQuality.MAJ);
        Interval M3 = new Interval(IntervalQuality.MAJOR, IntervalNumber.THIRD);
        Assert.assertEquals(dFlatMaj, fMaj.modulateBy(M3, Key.Modulation.DOWN));

        Key cFlatMaj = new Key(PitchClass.C.alter(Accidental.FLAT), KeyQuality.MAJ);
        Interval d5 = new Interval(IntervalQuality.P_DIM, IntervalNumber.FIFTH);
        Assert.assertEquals(cFlatMaj, fMaj.modulateBy(d5, Key.Modulation.UP));

        try
        {
            Key eMin = new Key(PitchClass.E, KeyQuality.MIN);
            Interval d3 = new Interval(IntervalQuality.MM_DIM, IntervalNumber.THIRD);
            Key gFlatMin = eMin.modulateBy(d3, Key.Modulation.UP);
            Assert.fail();
        }
        catch (IllegalArgumentException e)
        {
            // ok
        }
    }
}
