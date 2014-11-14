package main.java.org.lucjross.jmtl.scaledegree;

/**
 * Created by lucas on 11/14/2014.
 */
public enum ScaleDegreeQuality {
    LOWERED("Lowered"),
    NATURAL(null),
    RAISED("Raised");

    private String name;

    private ScaleDegreeQuality(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
