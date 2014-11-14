package main.java.org.lucjross.jmtl.scaledegree;

/**
 * Created by lucas on 11/14/2014.
 */
public enum ScaleDegreeNumber {
    FIRST("First"),
    SECOND("Second"),
    THIRD("Third"),
    FOURTH("Fourth"),
    FIFTH("Fifth"),
    SIXTH("Sixth"),
    SEVENTH("Seventh");

    private String name;

    private ScaleDegreeNumber(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
