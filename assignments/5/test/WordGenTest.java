import org.junit.Test;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WordGenTest {

    @Test
    public void testMain() throws Exception {

    }

    @Test
    public void testGetTableText() throws Exception {
        // default params
        assertThat(new WordGen("abcd").getTableText(), is(
            "ab→c:100%\n" +
            "bc→d:100%\n"
        ));

        assertThat(new WordGen("ababd").getTableText(), is(
            "ab→a:50%,d:50%\n" +
            "ba→b:100%\n"
        ));

        // change prefix length to one
        assertThat(new WordGen("abc", null, null, 1, 1, "").getTableText(), is(
            "a→b:100%\n" +
            "b→c:100%\n"
        ));

        // change suffix length to two
        assertThat(new WordGen("abc", null, null, 1, 2, "").getTableText(), is(
                "a→bc:100%\n"
        ));

        // change part separator words instead of characters
        assertThat(new WordGen("abc be abd", null, null, 1, 1, " ").getTableText(), is(
                "abc→be:100%\n" +
                "be→abd:100%\n"
        ));

        // use two words as prefix
        assertThat(new WordGen("abc be abd", null, null, 2, 1, " ").getTableText(), is(
                "abc be→abd:100%\n"
        ));
    }

    @Test
    public void testGenerateText() throws Exception {
        // default params
        assertThat(new WordGen("abcd").generateText(), is("abcd"));
        assertThat(new WordGen("ababd").generateText(), either(is("ababd")).or(is("abd")).or(is("ababa")));

        // different starting string
        assertThat(new WordGen("abcd", "bc", null).generateText(), is("bcd"));
        assertThat(new WordGen("abcd", "de", null).generateText(), is("de"));

        // change max parts
        assertThat(new WordGen("abcd", "ab", 2).generateText(), is("ab"));
        assertThat(new WordGen("abcd", "bc", 5).generateText(), is("bcd"));

        // use different separator
        assertThat(new WordGen("hi there", null, null, 1, 1, " ").generateText(), is("hi there"));
        assertThat(new WordGen("hi there hi hello", null, 2, 1, 1, " ").generateText(), either(is("hi there")).or(is("hi hello")));

    }
}