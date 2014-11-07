package Utils;


public class Strings {
    // adapted from http://stackoverflow.com/a/1515548/907060
    public static String join(String joiner, String[] array) {
        StringBuilder sb = new StringBuilder();

        for (int index=0; index < array.length; index++ ) {
            sb.append(array[index]);
            if (index != array.length - 1) {
                sb.append(joiner);
            }
        }

        return sb.toString();
    }

    // from http://stackoverflow.com/a/7888049/907060
    public static String escapeString(String s){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); i++)
            switch (s.charAt(i)){
                case '\n': sb.append("\\n"); break;
                case '\t': sb.append("\\t"); break;
                // ... rest of escape characters
                default: sb.append(s.charAt(i));
            }
        return sb.toString();
    }
}
