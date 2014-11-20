package main.java.org.lucjross.jmtl.key;

import main.java.org.lucjross.jmtl.interval.Interval;
import main.java.org.lucjross.jmtl.interval.IntervalNumber;
import main.java.org.lucjross.jmtl.interval.IntervalQuality;
import main.java.org.lucjross.jmtl.pitch.PitchClass;

/**
 * Created by lucas on 11/14/2014.
 */
public class Key {
    private final PitchClass tonic;

    private final KeyQuality quality;

    private static final Interval P5 = new Interval(
            IntervalQuality.PERFECT, IntervalNumber.FIFTH);

    public Key(PitchClass tonic, KeyQuality quality)
    {
        if (sharps(tonic, quality) == -1 && flats(tonic, quality) == -1)
        {
            throw new IllegalArgumentException(tonic.toString());
        }

        this.tonic = tonic;
        this.quality = quality;
    }

    private static int sharps(PitchClass tonic, KeyQuality quality)
    {
        PitchClass pitchClass =
                quality == KeyQuality.MAJ ? PitchClass.C : PitchClass.A;
        for (int i = 0; i < PitchClass.PITCH_CLASSES.size() + 1; i++)
        {
            if (tonic.equals(pitchClass))
            {
                return i;
            }
            pitchClass = pitchClass.raiseBy(P5);
        }
        return -1;
    }

    public int sharps()
    {
        return sharps(tonic, quality);
    }

    private static int flats(PitchClass tonic, KeyQuality quality)
    {
        PitchClass pitchClass =
                quality == KeyQuality.MAJ ? PitchClass.C : PitchClass.A;
        for (int i = 0; i < PitchClass.PITCH_CLASSES.size() + 1; i++)
        {
            if (tonic.equals(pitchClass))
            {
                return i;
            }
            pitchClass = pitchClass.lowerBy(P5);
        }
        return -1;
    }

    public int flats()
    {
        return flats(tonic, quality);
    }

    public Key modulateBy(Interval interval, Modulation modulation)
    {
        PitchClass pitchClass =
                modulation == Modulation.UP ? tonic.raiseBy(interval) : tonic.lowerBy(interval);
        return new Key(pitchClass, quality);
    }

    public static enum Modulation
    {
        UP,
        DOWN
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Key key = (Key) o;

        if (quality != key.quality)
        {
            return false;
        }
        if (tonic != null ? !tonic.equals(key.tonic) : key.tonic != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = tonic != null ? tonic.hashCode() : 0;
        result = 31 * result + (quality != null ? quality.hashCode() : 0);
        return result;
    }
}
