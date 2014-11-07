import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class TableTest {

    @Test
    public void testAddAndChooseSuffix() throws Exception {
        Table t = new Table("");

        // first make sure that choosing a non inputted prefix will return null
        assertThat(t.chooseSuffix(new String[]{"a"}), is(nullValue()));

        // then make sure that once you associate as prefix with a suffix, that suffix
        // will be returned
        t.add(new String[]{"a"}, new String[]{"b"});
        assertThat(t.chooseSuffix(new String[]{"a"}), is(new String[]{"b"}));

        // then make a new suffix prefix pair and make sure the other is returned
        t.add(new String[]{"c"}, new String[]{"d"});
        assertThat(t.chooseSuffix(new String[]{"c"}), is(new String[]{"d"}));

        // finally add a whole lot of a different suffix to a prefix, and make sure
        // the new suffix is chosen now
        for (int _ = 0; _ < 10000; _++) {
            t.add(new String[]{"c"}, new String[]{"e"});
        }
        assertThat(t.chooseSuffix(new String[]{"c"}), is(new String[]{"e"}));

    }

    @Test
    public void testToString() throws Exception {
        Table t = new Table("");

        // an empty table should have an empty string
        assertThat(t.toString(), is(""));

        // adding one association should display that one
        t.add(new String[]{"a", "b"}, new String[]{"c"});
        assertThat(t.toString(), is("ab→c:100%\n"));
        // then if add another suffix to that prefix, it should show both
        t.add(new String[]{"a", "b"}, new String[]{"d"});
        assertThat(t.toString(), is("ab→c:50%,d:50%\n"));

        // now see if you a different association, it should display that as well
        t.add(new String[]{"d", "e"}, new String[]{"a"});
        assertThat(t.toString(), is("ab→c:50%,d:50%\nde→a:100%\n"));

        // try to see if space separated letters are joined properly
        Table tSpaces = new Table(" ");
        tSpaces.add(new String[]{"a", "b"}, new String[]{"c", "e"});
        tSpaces.add(new String[]{"a", "b"}, new String[]{"d", "d"});
        assertThat(tSpaces.toString(), is("a b→c e:50%,d d:50%\n"));

    }
}