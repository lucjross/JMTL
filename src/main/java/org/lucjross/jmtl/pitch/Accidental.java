package main.java.org.lucjross.jmtl.pitch;

/**
 * Created by lucas on 11/17/2014.
 */
public enum Accidental
{
    DOUBLE_FLAT(-2),
    FLAT(-1),
    NATURAL(0),
    SHARP(1),
    DOUBLE_SHARP(2);

    private final int modifier;

    private Accidental(int modifier)
    {
        this.modifier = modifier;
    }

    public int getModifier()
    {
        return modifier;
    }
}
