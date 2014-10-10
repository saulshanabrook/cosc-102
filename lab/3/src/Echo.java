/**
 * Created by saul on 10/2/14.
 */
public class Echo {

    public static enum FormatType {UPPER, LOWER, TITLE, NORMAL, COUNT};

    public static FormatType currentFormat = FormatType.NORMAL;

    public static void main(String[] args) {
        for (String arg: args ) {
            if (arg.equals("-n")) {
                currentFormat = FormatType.NORMAL;
                continue;
            }
            if (arg.equals("-u")) {
                currentFormat = FormatType.UPPER;
                continue;
            }
            if (arg.equals("-l")) {
                currentFormat = FormatType.LOWER;
                continue;
            }
            if (arg.equals("-t")) {
                currentFormat = FormatType.TITLE;
                continue;
            }
            if (arg.equals("-c")) {
                currentFormat = FormatType.COUNT;
                continue;
            }
            if (arg.equals("-r")) {
                System.out.println();
                continue;
            }
            if (arg.startsWith("--")) {
                arg = arg.substring(1, arg.length());
            }
            printText(arg);
        }
        System.out.println();
    }

    public static void printText(String text ) {
        switch (currentFormat) {
            case NORMAL:
                break;
            case UPPER:
                text = text.toUpperCase();
                break;
            case LOWER:
                text = text.toLowerCase();
                break;
            case TITLE:
                text = toTitleCase(text);
                break;
            case COUNT:
                text = String.valueOf(text.length());
        }
        System.out.print(text + " ");
    }

    // from http://stackoverflow.com/a/1086134/907060
    // modified to capitlize first letter
    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (!Character.isLetter(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }
}