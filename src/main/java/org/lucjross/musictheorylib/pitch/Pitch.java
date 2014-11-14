package main.java.org.lucjross.musictheorylib.pitch;

import java.util.*;

/**
 * Created by lucas on 11/14/2014.
 */
public enum Pitch {
    C,
        C_FLAT,
        C_SHARP,
        C_DOUBLESHARP,
    D,
        D_DOUBLEFLAT,
        D_FLAT,
        D_SHARP,
        D_DOUBLESHARP,
    E,
        E_DOUBLEFLAT,
        E_FLAT,
        E_SHARP,
    F,
        F_FLAT,
        F_SHARP,
        F_DOUBLESHARP,
    G,
        G_DOUBLEFLAT,
        G_FLAT,
        G_SHARP,
        G_DOUBLESHARP,
    A,
        A_DOUBLEFLAT,
        A_FLAT,
        A_SHARP,
        A_DOUBLESHARP,
    B,
        B_DOUBLEFLAT,
        B_FLAT,
        B_SHARP;

    public static final Set<Set<Pitch>> ENHARMONIC_EQUIVALENCIES;
    static
    {
        Set<Set<Pitch>> enharmonicEquivalencies = new HashSet<>(12);

        Set<Pitch> c_set = new HashSet<>();
        c_set.add(C);
        c_set.add(B_SHARP);
        c_set.add(D_DOUBLEFLAT);
        enharmonicEquivalencies.add(c_set);

        Set<Pitch> c_sharp_set = new HashSet<>();
        c_sharp_set.add(C_SHARP);
        c_sharp_set.add(D_FLAT);
        enharmonicEquivalencies.add(c_sharp_set);

        Set<Pitch> d_set = new HashSet<>();
        d_set.add(D);
        d_set.add(C_DOUBLESHARP);
        d_set.add(E_DOUBLEFLAT);
        enharmonicEquivalencies.add(d_set);

        Set<Pitch> d_sharp_set = new HashSet<>();
        d_sharp_set.add(D_SHARP);
        d_sharp_set.add(E_FLAT);
        enharmonicEquivalencies.add(d_sharp_set);

        Set<Pitch> e_set = new HashSet<>();
        e_set.add(E);
        e_set.add(D_DOUBLESHARP);
        e_set.add(F_FLAT);
        enharmonicEquivalencies.add(e_set);

        Set<Pitch> f_set = new HashSet<>();
        f_set.add(F);
        f_set.add(E_SHARP);
        f_set.add(G_DOUBLEFLAT);
        enharmonicEquivalencies.add(f_set);

        Set<Pitch> f_sharp_set = new HashSet<>();
        f_sharp_set.add(F_SHARP);
        f_sharp_set.add(G_FLAT);
        enharmonicEquivalencies.add(f_sharp_set);

        Set<Pitch> g_set = new HashSet<>();
        g_set.add(G);
        g_set.add(F_DOUBLESHARP);
        g_set.add(A_DOUBLEFLAT);
        enharmonicEquivalencies.add(g_set);

        Set<Pitch> g_sharp_set = new HashSet<>();
        g_sharp_set.add(G_SHARP);
        g_sharp_set.add(A_FLAT);
        enharmonicEquivalencies.add(g_sharp_set);

        Set<Pitch> a_set = new HashSet<>();
        a_set.add(A);
        a_set.add(G_DOUBLESHARP);
        a_set.add(B_DOUBLEFLAT);
        enharmonicEquivalencies.add(a_set);

        Set<Pitch> a_sharp_set = new HashSet<>();
        a_sharp_set.add(A_SHARP);
        a_sharp_set.add(B_FLAT);
        enharmonicEquivalencies.add(a_sharp_set);

        Set<Pitch> b_set = new HashSet<>();
        b_set.add(B);
        b_set.add(A_DOUBLESHARP);
        b_set.add(C_FLAT);
        enharmonicEquivalencies.add(b_set);

        ENHARMONIC_EQUIVALENCIES = Collections.unmodifiableSet(enharmonicEquivalencies);
    }

    public Set<Pitch> getEnharmonicEquivalents()
    {
        for (Set<Pitch> set : ENHARMONIC_EQUIVALENCIES)
        {
            if (set.contains(this))
            {
                return set;
            }
        }

        throw new EnumConstantNotPresentException(Pitch.class, this.name());
    }

//    public Pitch fromInterval(Interval interval)
//    {
//
//    }
}
