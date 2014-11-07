package Utils;

import structure5.Association;
import structure5.Vector;

import java.util.Arrays;

/**
 * So the default `contains` and `indexOf` methods of vectors uses the `equals` method
 * to check all their items. however, when you pass in an associaiton that has an array
 * it will never be equal to another association with the same array as its key,
 * because (http://stackoverflow.com/a/8777266/907060):
 *   > It's a famous problem: .equals() for arrays is badly broken, just don't use it, ever.
 *
 * So instead i had to make a `contains` and `find` static method for
 * vectors with associations of lists in them.
 */
public class VectorOfAssociationsOfArrays {
    public static  <K, V> boolean contains(Vector<Association<K[], V>> vector, K[] key) {
        for (Association<K[], V> association : vector) {
            if (Arrays.equals(association.getKey(), key)) {
                return true;
            }
        }
        return false;
    }

    public static <K, V> Association<K[], V> find(Vector<Association<K[], V>> vector, K[] key) {
        for (Association<K[], V> association : vector) {
            if (Arrays.equals(association.getKey(), key)) {
                return association;
            }
        }
        assert false;
        return null;
    }


    // then these methods are only specific to vectors of associations, but use the
    // proceeding methods, so are typecast to use associations of arrays

    public static <K, V> V findValueFromKey(Vector<Association<K[], V>> vector, K[] key) {
        return find(vector, key).getValue();
    }

    public static <K, V> V findValueFromKeyOrAddDefault(Vector<Association<K[], V>> vector, K[] key, V defaultValue) {
        if (contains(vector, key)) {
            return find(vector, key).getValue();
        }
        Association<K[], V> newAssociation = new Association<K[], V>(key, defaultValue);
        vector.add(newAssociation);
        return newAssociation.getValue();
    }

    public static <K, V> void setValueForKey(Vector<Association<K[], V>> vector, K[] key, V value) {
        find(vector, key).setValue(value);
    }

}
