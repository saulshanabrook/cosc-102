import Utils.VectorOfAssociationsOfArrays;
import structure5.Association;
import structure5.Vector;

import java.text.DecimalFormat;
import java.util.Random;

import static Utils.VectorOfAssociationsOfArrays.setValueForKey;
import static Utils.VectorsOfAssociations.sortBasedOnValues;

public class FrequencyList {
    private static Random random = new Random();
    private static DecimalFormat percentFormat = new DecimalFormat("###%");

    private Vector<Association<String[], Integer>> data = new Vector<Association<String[], Integer>>();
    private String joinString;
    private int totalCount = 0;

    public FrequencyList(String joinString) {
        this.joinString = joinString;
    }

    public void add(String[] suffix) {

        Integer count = VectorOfAssociationsOfArrays.findValueFromKeyOrAddDefault(data, suffix, 0);
        setValueForKey(data, suffix, count + 1);
        totalCount += 1;

    }

    public String[] choose() {
        return Utils.VectorsOfAssociations.selectKeyBasedOnValueWeights(data);
    }
    public String toString(){
        sortBasedOnValues(data);
        StringBuilder outputString = new StringBuilder();

        for (Association<String[], Integer> entry : data) {
            String suffix = Utils.Strings.escapeString(Utils.Strings.join(joinString, entry.getKey()));

            outputString.append(suffix);
            outputString.append(":");
            int count = entry.getValue();
            float percent = (float)count / (float)totalCount;
            outputString.append(percentFormat.format(percent));
            outputString.append(",");
        }
        // remove the last comma
        outputString.deleteCharAt(outputString.length() - 1);
        return outputString.toString();
    }

    public Vector<String[]> getSuffixes() {
        return Utils.VectorsOfAssociations.getKeys(data);
    }


    public int getSuffixCount(String[] suffix) {
        return VectorOfAssociationsOfArrays.findValueFromKey(data, suffix);
    }
}
