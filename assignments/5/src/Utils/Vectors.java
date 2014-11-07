package Utils;

import structure5.Vector;

/**
 * Created by saul on 10/22/14.
 */
public class Vectors {
    public static Integer sum(Vector<Integer> vector){
        int sum = 0; //start with 0
        for(int n : vector) { //this won't execute if no argument is passed
            sum += n; // this will repeat for all the arguments
        }
        return sum; //return the sum
    }
}
