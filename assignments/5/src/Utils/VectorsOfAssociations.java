package Utils;

import structure5.Association;
import structure5.Vector;

import java.util.Random;


public class VectorsOfAssociations {
    private static final Random random = new Random();

    public static <K, V> Vector<K> getKeys(Vector<Association<K, V>> vector) {
        Vector<K> returnVector = new Vector<K>();
        for (Association<K, V> association : vector) {
            returnVector.add(association.getKey());
        }
        return returnVector;
    }

    public static <K, V> Vector<V> getValues(Vector<Association<K, V>> vector) {
        Vector<V> returnVector = new Vector<V>();
        for (Association<K, V> association : vector) {
            returnVector.add(association.getValue());
        }
        return returnVector;
    }

    // http://stackoverflow.com/a/4463613/907060
    public static <K> K selectKeyBasedOnValueWeights(Vector<Association<K, Integer>> vector) {
        int sumOfValues = Vectors.sum(getValues(vector));
        int randomStoppingPoint = random.nextInt(sumOfValues);

        int partialSum = 0;
        for (Association<K, Integer> association: vector) {
            int weight = association.getValue();
            partialSum += weight;

            if (randomStoppingPoint <= partialSum) {
                return association.getKey();
            }
        }
        assert false;
        return null;
    }

    // selection sort copied from http://rosettacode.org/wiki/Sorting_algorithms/Selection_sort#Java
    public static <K> void sortBasedOnValues(Vector<Association<K, Integer>> vector) {
        for(int currentPlace = 0; currentPlace < vector.size() - 1; currentPlace++){
            int largest = 0;
            int smallestAt = currentPlace + 1;
            for(int check = currentPlace; check < vector.size(); check++){
                if(vector.get(check).getValue() > largest){
                    smallestAt = check;
                    largest = vector.get(check).getValue();
                }
            }
            Association<K, Integer> temp = vector.get(currentPlace);
            vector.set(currentPlace, vector.get(smallestAt));
            vector.set(smallestAt, temp);
        }
    }
}
