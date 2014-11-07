import org.junit.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;


public class FrequencyListTest {


    @Test
    public void testAdd() throws Exception {
        FrequencyList fl = new FrequencyList("");

        // adding one new prefix
        String[] value = new String[]{"a", "b"};
        fl.add(value);
        // should be in the list of suffixes
        assertTrue(fl.getSuffixes().contains(value));
        // the count of that suffix should be one
        assertThat(fl.getSuffixCount(value), is(1));

        // adding another of the original should give a count of two
        fl.add(value);
        assertThat(fl.getSuffixCount(value), is(2));

        // now add a different suffix
        String[] secondValue = new String[]{"a", "c"};
        fl.add(secondValue);
        // and make sure it contains that one
        assertTrue(fl.getSuffixes().contains(secondValue));
        // and its count is one
        assertEquals(1, fl.getSuffixCount(secondValue));

        // now add the same new secondValue, but copy it to make
        // sure this is still recorded
        fl.add(secondValue.clone());
        assertEquals(2, fl.getSuffixCount(secondValue));
    }

    @Test
    public void testChoose() throws Exception {
        FrequencyList fl = new FrequencyList("");

        // make sure value chosen is the only value in the list
        String[] value = new String[]{"a", "b"};
        fl.add(value);
        assertThat(fl.choose(), is(value));

        // now see if I add another, make sure it is either of those
        String[] secondValue = new String[]{"a", "c"};
        fl.add(secondValue);
        assertThat(fl.choose(), anyOf(is(value), is(secondValue)));

        // now add a whole lot of the second one to make sure it will select that one now
        for (int _ = 0; _ < 10000; _++) {
            fl.add(secondValue);
        }
        assertThat(fl.choose(), is(secondValue));

    }

    @Test
    public void testToString() throws Exception {
        FrequencyList fl = new FrequencyList("");
        fl.add(new String[]{"a", "b"});
        assertThat(fl.toString(), is("ab:100%"));

        fl.add(new String[]{"a", "c"});
        assertThat(fl.toString(), is("ab:50%,ac:50%"));

        fl.add(new String[]{"a", "c"});
        assertThat(fl.toString(), is("ac:67%,ab:33%"));

        FrequencyList flWithSpaces = new FrequencyList(" ");
        flWithSpaces.add(new String[]{"a", "b"});
        flWithSpaces.add(new String[]{"a", "c"});
        assertThat(flWithSpaces.toString(), is("a b:50%,a c:50%"));

        // make sure new lines are escaped
        fl = new FrequencyList("");
        fl.add(new String[]{"a", "\n"});
        assertThat(fl.toString(), is("a\\n:100%"));


    }


}