package data;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by s158270 on 15-3-2018.
 */
public class ListSPHandlerTest {
    @Test
    public void validSplitOnChar1() {
        List<String> l = ListSPHandler.splitOnChar(",banaan,aAp,beREnKLAuW,zeepbel,", ',');
        assertEquals(l.get(0), ("banaan"));
        assertEquals(l.get(1), ("aAp"));
        assertEquals(l.get(2), ("beREnKLAuW"));
        assertEquals(l.get(3), ("zeepbel"));
    }

    @Test
    public void validSplitOnChar2() {
        List<String> l = ListSPHandler.splitOnChar("abraklaboepasta", 'a');
        assertEquals(l.get(0), ("br"));
        assertEquals(l.get(1), ("kl"));
        assertEquals(l.get(2), ("boep"));
        assertEquals(l.get(3), ("st"));
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void validSplitOnChar3() {
        //banaan and zeepbel are not fetched, since theyre not surrounded by ','
        List<String> l = ListSPHandler.splitOnChar("banaan,aAp,beREnKLAuW,zeepbel", ',');
        assertEquals(l.get(0), ("aAp"));
        assertEquals(l.get(1), ("beREnKLAuW"));
        l.get(2);
    }

    @Test (expected = NullPointerException.class)
    public void INvalidSplitOnChar1() {
        List<String> l = ListSPHandler.splitOnChar(null, ',');
    }
}