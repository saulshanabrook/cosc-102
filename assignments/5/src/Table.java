import Utils.VectorOfAssociationsOfArrays;
import structure5.Association;
import structure5.Vector;

import static Utils.Strings.escapeString;
import static Utils.Strings.join;


public class Table {
    Vector<Association<String[], FrequencyList>> data;
    private String partSeparator;

    public Table(String partSeparator) {
        this.partSeparator = partSeparator;
        this.data = new Vector<Association<String[], FrequencyList>>();

    }

    public void add(String[] prefix, String[] suffix) {
        VectorOfAssociationsOfArrays.findValueFromKeyOrAddDefault(data, prefix, new FrequencyList(partSeparator)).add(suffix);
    }

    public String[] chooseSuffix(String[] prefix) {
        if (VectorOfAssociationsOfArrays.contains(data, prefix)) {
            return VectorOfAssociationsOfArrays.findValueFromKey(data, prefix).choose();
        }
        return null;
    }


    public String toString() {
        StringBuilder outputString = new StringBuilder();
        for (Association<String[], FrequencyList> association : data) {
            outputString.append(escapeString(join(partSeparator, association.getKey())));
            outputString.append("→");
            outputString.append(association.getValue());
            outputString.append("\n");
        }
        return outputString.toString();
    }
}
