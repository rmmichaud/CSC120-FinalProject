import java.util.*;
/**
 * creates all methods corresponding to the user moving between labs, adding items to carrying, etc. 
 */
public class Chemistt {
    String chemist;
    ArrayList<String> reaction;
    Integer baseHood;
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> acceptedItem;
    ArrayList<String> carrying = new ArrayList<>();
    Room shea;
    Room gorin;
    Room buck;
    Room strom;
    Room queeney;
    Room hallway;
    Room teaching;
    Hashtable<String, ArrayList<String>> hoods;
    Integer currentHood;
    Room currentRoom;
    Room baseRoom;
    String baseLocation;
    ArrayList<String> copyRxn;
    int placehold;

    /**
     * constructor - takes a lot of parameters because it creates the map within this class as well so it can check where the user 
     * can move. not sure if there was a better way to do this but probably 
     * @param reaction
     * @param baseHood
     * @param baseRoom
     * @param shea
     * @param gorin
     * @param buck
     * @param strom
     * @param queeney
     * @param teaching
     * @param hallway
     * @param hoods
     */
    public Chemistt (ArrayList<String> reaction, Integer baseHood, Room baseRoom, 
    Room shea, Room gorin, Room buck, Room strom, Room queeney, 
    Room teaching, Room hallway, Hashtable<String, ArrayList<String>> hoods) {
        this.baseHood = baseHood;
        this.reaction = reaction;
        this.copyRxn = reaction;
        this.currentHood = baseHood;
        this.baseHood = baseHood;
        this.baseRoom = baseRoom;
        this.currentRoom = baseRoom;
        this.shea = shea;
        this.gorin = gorin;
        this.buck = buck;
        this.strom = strom;
        this.queeney = queeney;
        this.hoods = hoods;
        this.hallway = hallway;
        this.teaching = teaching;
        this.baseLocation = this.baseRoom.getName() + " " + this.baseHood.toString();
    }

    /**
     * adds item to the user's base hood, removes them from the reaction array if they are reaction items, breaks if the reaction array is empty 
     * empties the carrying array
     * @return boolean
     */
    public boolean addtoHood() {
        System.out.println("Would you like to add the items you are carrying to your hood inventory? ");
        String response = scanner.nextLine().toLowerCase();
        if (yesNo(response)) {
            for (int k = 0; k < carrying.size(); k++){
                for (int h = 0; h < this.reaction.size(); h++) {
                    if (this.reaction.get(h).equals(carrying.get(k))) {
                        this.reaction.remove(h);
                        if(this.reaction.isEmpty()){
                            return false;
                        }
                    }
                } 
            }
            for (int k = 0; k <carrying.size();k++) {
                hoods.get(baseLocation).add(carrying.get(k)); 
            }
            carrying.clear();
        } 
        System.out.println("Successful drop.");
        return true;
    }
    
    /**
     * checks if the user is carrying too many items 
     * if not, allows the user to add items to their carrying and removes those items from the current location
     * @param carrying
     * @param item
     * @param currentLocation
     */
    public void addtoCarrying(ArrayList<String> carrying, String item, String currentLocation) {
        if (carrying.size() >= 4) {
            System.out.println("**************************************************************************************************");
            System.out.println("You cannot carry anything else! Drop an item, or return to your base hood and put your items down. ");
            System.out.println("**************************************************************************************************");
            System.out.println();
        } else {
            carrying.add(item);
            hoods.get(currentLocation).remove(item);
            this.copyRxn.remove(item);
            System.out.println(item + " added to carrying.");
        }
    }
    
    /**
     * removes item from the user's carrying inventory and adds it to the inventory of the current location
     * @param carrying
     * @param item
     * @return boolean if the drop was successful
     */
    public boolean drop(ArrayList<String> carrying, String item) {   
        for (int o = 0; 0 < carrying.size(); o++) {
            if (item.equals(carrying.get(o))) {
                String currentLocation = currentRoom.getName() + " " + currentHood.toString();
                hoods.get(currentLocation).add(item);
                System.out.println(hoods.get(currentLocation).toString());
                carrying.remove(item);
                System.out.println(carrying);
                return true;
            }
        } 
        return false;
    } 
    /**
     * checks if the reaction array is empty, returns true if so
     * @return boolean
     */
    public boolean checkHood() {
        if (this.reaction.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * checks the user's location and the hood or lab they want to go to, if the lab is connecting then the user's location is changed 
     * to that lab
     * @param nLab
     * @param nHood
     */
    public void move (Room nLab, Integer nHood) {
        if (nLab != this.currentRoom) {
            for (int i = 0; i < nLab.connect.get(nLab).size(); i++){
                if (nLab.connect.get(nLab).get(i).equals(this.currentRoom)) {
                    this.currentRoom = nLab;
                }
            }
        }
        if (nHood != this.currentHood) {
            this.currentHood = nHood;
        } else {
            System.out.println("You are already located in that hood.");
        }
        if (this.currentRoom != nLab) {
            System.out.println("****************************************");
            System.out.println("Move unsuccessful. Not a connecting lab.");
            System.out.println("****************************************");
        }
    }
    /**
     * checks the current inventory for the item that the user wants to pick up 
     * adds to carrying if the item is accepted
     * @param item
     */
    public void pickUp(String item){
        String hoodIdentifier = currentRoom.getName() + " " + currentHood.toString();
        String error = item;
        for (int l = 0; l < hoods.get(hoodIdentifier).size(); l++) {
            if (item.equals(hoods.get(hoodIdentifier).get(l))) {
                error = "xxx";
                addtoCarrying(this.carrying, hoods.get(hoodIdentifier).get(l), hoodIdentifier);
            }
        }
        if (item.equals(error)) {
            System.out.println("This item is not in the inventory.");
        } 
    }
    /**
     * checks the user's response for yes/no questions 
     * @param response
     * @return
     */
    public boolean yesNo(String response) {
        while (true) {
            response = response.toLowerCase();
            if (response.contains("yes")) {
                return true;
            } 
            if (response.contains("no")) {
                return false;
            } else {
                System.out.println("Response not accepted. Please try again (Yes or No): ");
                response = scanner.nextLine();
                continue;
            }
        }
    }
}
