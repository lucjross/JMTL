package main.java.org.lucjross.jmtl.pitch;

import java.util.TreeSet;

/**
 * Created by lucas on 11/14/2014.
 */
public class PitchSet extends TreeSet<Pitch>
{
    private final Pitch root;

    public PitchSet(Pitch root)
    {
        super();
        this.root = root;
    }

    public Pitch getRoot()
    {
        return root;
    }

//    public ClosedIntervalSequence toIntervalVector()
//    {
//        ClosedIntervalSequence iv = new ClosedIntervalSequence();
//        if (size() < 2)
//        {
//            return iv;
//        }
//
//        Iterator<Pitch> iter = iterator();
//        Pitch prev = iter.next();
//        Pitch curr;
//        while (iter.hasNext())
//        {
//            curr = iter.next();
//        }
//    }
}
