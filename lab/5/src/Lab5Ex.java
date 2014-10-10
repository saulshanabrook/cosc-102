import structure5.Association;
import structure5.Vector;
// DO NOT USE import java.util.*;


public class Lab5Ex {

    // 0. Compile and run this code
    // Try different input (command line arguments) and understand the output

    // 1.a Use a Vector (Bailey's package one) instead of an array
    // Go down to do 1.b (before starting with 2)
    private Vector<Association<String, String>> spells;

    private static String seekedSpell;


    public void initialize() {
        // Build the list of magic spells we know
        // 1.b Change this lines of code to use the Vector you declared above (1.a)
        // you can directly add to the Vector (you can change it once you complete 2. if you want)
        spells = new Vector(10);
        spells.add(new Association<String, String>("Reparo", "Fixes damaged object"));
        spells.add(new Association<String, String>("Evanesco", "Makes something vanish"));
        spells.add(new Association<String, String>("Expelliarmus", "Disarm opponent"));
        spells.add(new Association<String, String>("Accio", "Summon object"));
        spells.add(new Association<String, String>("Alohomora", "Open a locked door"));
        spells.add(new Association<String, String>("Lumos", "Illuminate wand"));
        spells.add(new Association<String, String>("Crucio", "Inflict death with great pain"));
        spells.add(new Association<String, String>("Engorgio", "Cause target to swell"));
        spells.add(new Association<String, String>("Immobulus", "Stop an object�s motion"));
        //spells.add(new Association<String, String>("Incendio","Start a fire"));

    }

    // 2.a Modify the body of the method below so that it only inserts Association a
    // in the Vector if its key is not already present
    public void addUnique(Association<String, String> a) {
        boolean spellExistsAlready = false;

        for (Association spell : spells.values()) {
            if (spell.getKey().equals(a.getKey())) {
                spellExistsAlready = true;
            }
        }

        if (!spellExistsAlready) {
            spells.add(a);
        }
    }

    // 1.c Modify the method to print the content of the Vector
    // Needed to test the use of the Vector (step 1)
    // and the addUnique method (step 2)
    public void print() {
        for (Association spell : spells.values()) {

            System.out.println(spell.getKey() + " --> " + spell.getValue());
            if (spell.getKey().equals(seekedSpell))
                System.out.println("YOU ASKED " + seekedSpell + " --> " + spell.getValue());
        }
    }


    @SuppressWarnings("unchecked")
    public static void main(String[] args) {


        if (args.length == 0) {
            System.out.println("Specify a spell at the command line");
            System.exit(1);
        }

        seekedSpell = args[0];

        Lab5Ex exp = new Lab5Ex();
        exp.initialize();


        // 2.b Test the method your wrote above (2.b).
        // Attempt to add with your uniqueAdd an association that use a key that exists
        // Analyze the print out of the Vector: does your method work?

        exp.addUnique(new Association<String, String>("Lumos", "Start a fire"));
        exp.addUnique(new Association<String, String>("Reparo", "Nope"));
        exp.addUnique(new Association<String, String>("New spell", "yeah!"));

        exp.print();

    }
}
