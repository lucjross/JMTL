package main.java.org.lucjross.musictheorylib.interval;

/**
 * Created by lucas on 11/14/2014.
 */
public enum IntervalQuality
{
    DOUBLY_DIM(-2),
    DIM(-1),
    MAJOR(0),
    MINOR(0),
    AUG(1),
    DOUBLY_AUG(2),
    PERFECT(0);

    private final int halfStepsModifier;

    private IntervalQuality inversion;
    static
    {
        DOUBLY_DIM.inversion = DOUBLY_AUG;
        DIM.inversion = AUG;
        MAJOR.inversion = MINOR;
        MINOR.inversion = MAJOR;
        AUG.inversion = DIM;
        DOUBLY_AUG.inversion = DOUBLY_DIM;
        PERFECT.inversion = PERFECT;
    }

    private IntervalQuality(int halfStepsModifier)
    {
        this.halfStepsModifier = halfStepsModifier;
    }

    public int getHalfStepsModifier()
    {
        return halfStepsModifier;
    }

    public IntervalQuality getInversion()
    {
        return inversion;
    }
}
