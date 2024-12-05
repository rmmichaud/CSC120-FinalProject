import java.util.ArrayList;
import java.util.Hashtable;

public class Hood {
    ArrayList<String> labInventory;
    String lab;
    String hoodNumber;
    Hashtable<String, ArrayList<String>> hoods;

    public Hood(ArrayList<String> labInventory, String lab, String hoodNumber) {
        this.hoodNumber = hoodNumber;
        this.lab = lab;
        this.labInventory = labInventory;
        String hoodIdentifier = lab + " " + hoodNumber;
        this.hoods = new Hashtable<String, ArrayList<String>>();
        this.hoods.put(hoodIdentifier, labInventory);
    }

    Hashtable<String, ArrayList<String>> getHood () {
        return this.hoods;
    }
}

