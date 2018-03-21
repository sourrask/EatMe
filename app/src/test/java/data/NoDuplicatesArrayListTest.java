package data;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by s158270 on 15-3-2018.
 */
public class NoDuplicatesArrayListTest {
    //we use a string for the generic type
    NoDuplicatesArrayList ndal;

    Name s1;
    Name s2;
    Name s3;
    Name s4;
    Name s5;
    Name s6;

    @Before
    public void setUp() {
        ndal = new NoDuplicatesArrayList();

        s1 = new Name("Banaan");
        s2 = new Name("Boom");
        s3 = new Name("Lamp");
        s4 = new Name("vogelHuisje");
        s5 = new Name("haarbaNd");
        s6 = new Name("hond");

    }

    //tries adding names. When names are already in it, it should return false
    @Test
    public void testAdd1() {
        Name n1 = new Name("BAnaAN");
        Name n2 = new Name("BoOm");
        Name n3 = new Name("BoOm");

        assertTrue(ndal.add(n2));
        assertFalse(ndal.add(n2));
        assertTrue(ndal.add(n1));
        assertFalse(ndal.add(n2));
        assertFalse(ndal.add(n3));
        assertFalse(ndal.add(n1));

        assertEquals(2, ndal.size());
        assertEquals(n2, ndal.get(0));
        assertEquals(n1, ndal.get(1));
    }

    //tries adding two new lists. if just a single element is already present in the whole
    //list added, false is returned
    @Test
    public void testAddAll1() {

        List x1 = new NoDuplicatesArrayList();
        List x2 = new NoDuplicatesArrayList();
        ndal.add(s1);
        ndal.add(s2);
        ndal.add(s3);

        x1.add(s2);
        x1.add(s3);
        x1.add(s4);

        x2.add(s5);
        x2.add(s6);

        //add list where one element is already present
        assertFalse(ndal.addAll(x1));
        assertEquals(4, ndal.size());
        assertEquals(s4, ndal.get(3));

        //add list where no element are present yet
        assertTrue(ndal.addAll(x2));
        assertEquals(6, ndal.size());
        assertEquals(s6, ndal.get(5));
    }

    @Test
    public void validGet1() {
        doAdd();
        assertEquals(s3, ndal.get("lAMp"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void invalidGet1() {
        doAdd();
        ndal.get("kaas");
    }

    @Test (expected = NullPointerException.class)
    public void invalidGet2() {
        doAdd();
        ndal.get(null);
    }

    @Test
    public void validContainsTest1() {
        doAdd();
        assertTrue(ndal.contains("hond"));
        assertFalse(ndal.contains("curry"));
    }

    @Test (expected = NullPointerException.class)
    public void invalidContainsTest1() {
        doAdd();
        ndal.contains(null);
    }


    private void doAdd() {
        ndal.add(s1);
        ndal.add(s2);
        ndal.add(s3);
        ndal.add(s4);
        ndal.add(s5);
        ndal.add(s6);
    }
}