import structure5.*;

public class Spells {
  
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    
    
    if (args.length == 0) {
      System.out.println("Specify a spell at the command line"); 
      System.exit(0);
    }
    
    // build the list of magic spells we know
    Association<String, String> spells[] = new Association[10];
    spells[0] = new Association<String, String>("Reparo","Fixes damaged object"); 
    spells[1] = new Association<String, String>("Evanesco","Makes something vanish"); 
    spells[2] = new Association<String, String>("Expelliarmus","Disarm opponent");
    spells[3] = new Association<String, String>("Accio","Summon object");
    spells[4] = new Association<String, String>("Alohomora","Open a locked door");
    spells[5] = new Association<String, String>("Lumos","Illuminate wand");
    spells[6] = new Association<String, String>("Crucio","Inflict death with great pain"); 
    spells[7] = new Association<String, String>("Engorgio","Cause target to swell"); 
    spells[8] = new Association<String, String>("Immobulus","Stop an object’s motion");
    
    spells[9] = new Association<String, String>("Incendio","Start a fire");
    //spells[9] = new Association<String, String>(null,"Start a fire");
    
    for (int spellnum = 0; spellnum < spells.length; spellnum++) { 
      if (spells[spellnum].getKey().equals(args[0]))
        System.out.println(spells[spellnum].getValue());
    }
  }
}





