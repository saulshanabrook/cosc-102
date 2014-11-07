import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static Utils.Preconditions.checkNotNull;
import static Utils.Strings.join;


public class WordGen {
    private Table table;
    private String startingString;
    private Integer maxParts;
    private int prefixLength;
    private int suffixLength;
    private String partSeparator;


    // generated from `corpus` and `part`
    private String[] corpusParts;

    public static void main(String[] inputs) throws IOException {
        String corpus_ = Utils.IO.getTextFromFile(inputs[0]);
        String startingString = null;
        Integer maxParts = null;
        switch (inputs.length) {
            case 2:
                startingString = inputs[1];
                break;
            case 3:
                startingString = inputs[1];
                maxParts = Integer.parseInt(inputs[2]);
                break;
        }
        System.out.println("Generating with default arguments\n");
        WordGen w = new WordGen(corpus_, startingString, maxParts);
        System.out.print(w.getTableText());
        System.out.println();
        System.out.print(w.generateText());

        System.out.println("\n\nNow trying with words instead of characters\n");
        w = new WordGen(corpus_, startingString, maxParts, 1, 1, " ");
        System.out.print(w.getTableText());
        System.out.println();
        System.out.print(w.generateText());

    }

    public WordGen(String corpus) {
        this(corpus, null, null);
    }

    public WordGen(String corpus, String startingString, Integer maxParts) {
        this(corpus, startingString, maxParts, 2, 1, "");
    }

    public WordGen(String corpus, String startingString, Integer maxParts, int prefixLength, int suffixLength, String partSeparator) {
        this.table = new Table(partSeparator);
        this.startingString = startingString;
        this.prefixLength = prefixLength;
        this.suffixLength = suffixLength;
        this.partSeparator = checkNotNull(partSeparator);
        this.corpusParts = splitText(checkNotNull(corpus));
        this.maxParts = maxParts != null ? maxParts : corpusParts.length;
        analyzeCorpus();
    }

    public String getTableText() {
        return table.toString();
    }

    public String generateText() {
        ArrayList<String> generatedParts = new ArrayList<String>();

        String[] newParts = startingString != null ? splitText(startingString) : Arrays.copyOfRange(corpusParts, 0, prefixLength);

        while (newParts != null &&  generatedParts.size() < maxParts) {
            generatedParts.addAll(Arrays.asList(newParts));

            String[] prefix = generatedParts.subList(generatedParts.size() - prefixLength, generatedParts.size()).toArray(new String[prefixLength]);
            newParts = table.chooseSuffix(prefix);
        }

        return joinSomeCorpusParts(generatedParts.toArray(new String[generatedParts.size()]));
    }


    private void analyzeCorpus() {
        // we want to iterate through all parts of the corpus, whether they are letters or characters
        for (int index = 0; index <= corpusParts.length - (suffixLength + prefixLength); index++) {
            int prefixStart = index;
            int prefixEnd = prefixStart + prefixLength;
            int suffixStart = prefixEnd;
            int suffixEnd = suffixStart + suffixLength;

            String[] prefixParts = Arrays.copyOfRange(corpusParts, prefixStart, prefixEnd);
            String[] suffixParts = Arrays.copyOfRange(corpusParts, suffixStart, suffixEnd);

            table.add(prefixParts, suffixParts);
        }
    }


    private String[] splitText(String text) {
        String[] splitText = text.split(partSeparator);

        // when splitting on "", will add an extra "" to the beginning, so we have to remove that
        if (partSeparator.equals("")) {
            return Arrays.copyOfRange(splitText, 1, splitText.length);
        }
        return splitText;
    }

    private String joinSomeCorpusParts(String[] someParts ) {
        return join(partSeparator, someParts);
    }
}
