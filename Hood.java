import java.util.ArrayList;
import java.util.Hashtable;
/**
 * Hood sets up all of the reaction hoods with their individual identifiers and inventories
 */
public class Hood {
    ArrayList<String> labInventory;
    String lab;
    String hoodNumber;
    Hashtable<String, ArrayList<String>> hoods;

    /**
     * constructor setting up the hood
     * @param labInventory
     * @param lab
     * @param hoodNumber
     */
    public Hood(ArrayList<String> labInventory, String lab, String hoodNumber) {
        this.hoodNumber = hoodNumber;
        this.lab = lab;
        this.labInventory = labInventory;
        String hoodIdentifier = lab + " " + hoodNumber;
        this.hoods = new Hashtable<String, ArrayList<String>>();
        this.hoods.put(hoodIdentifier, labInventory);
    }

    /**
     * hood getter method
     * @return
     */
    Hashtable<String, ArrayList<String>> getHood () {
        return this.hoods;
    }
}

