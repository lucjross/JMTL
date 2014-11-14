package test.java.org.lucjross.musictheorylib.pitch;

import main.java.org.lucjross.musictheorylib.pitch.Pitch;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucas on 11/14/2014.
 */
public class PitchTest {

    @Test
    public void test()
    {
        Set<Set<Pitch>> allEquivalencies = new HashSet<>();
        for (Pitch p : Pitch.values())
        {
            Set<Pitch> equivalents = p.getEnharmonicEquivalents();
            System.out.println(equivalents);
            allEquivalencies.add(equivalents);
        }
        Assert.assertEquals(allEquivalencies.size(), Pitch.ENHARMONIC_EQUIVALENCIES.size());
    }
}
