import java.util.*;
public class Chemistt {
    String chemist;
    ArrayList<String> reaction;
    //ArrayList<String> hoodInventory;
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
        //hoods.get(this.baseLocation).clear();
    }

    public boolean addtoHood() {
        System.out.println("Would you like to add the items you are carrying to your hood inventory? ");
        String response = scanner.nextLine().toLowerCase();
        //String add;
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
        return true;
    }
    
    public void addtoCarrying(ArrayList<String> carrying, String item, String currentLocation) {
        if (carrying.size() >= 4) {
            System.out.println("You cannot carry anything else! Drop an item, or return to your base hood and put your items down. ");
        } else {
            carrying.add(item);
            hoods.get(currentLocation).remove(item);
            this.copyRxn.remove(item);
            System.out.println(item + " added to carrying.");
        }
    }
    
    public boolean drop(ArrayList<String> carrying, String item) {   
        for (int o = 0; 0 < carrying.size(); o++) {
            if (item.equals(carrying.get(o))) {
                String currentLocation = currentRoom.getName() + " " + currentHood.toString();
                hoods.get(currentLocation).add(item);
                System.out.println(hoods.get(currentLocation).toString());
                carrying.remove(item);
                reaction.remove(item);
                System.out.println(carrying);
                return true;
            }
        }
            
        return false;
    } 


    public boolean checkHood() {
        if (this.reaction.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

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
        }
        if (this.currentRoom != nLab) {
            System.out.println("Move unsuccessful. Not a connecting lab.");
        }
        //addtoHood();
    }


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

    public static void main(String[] args) {
        
    }
}
