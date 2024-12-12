import java.util.*;

//import javax.management.RuntimeErrorException;
public class Room {
    public Hood sheaOne;
    protected String name;
    protected ArrayList<String> inventory;
    Hashtable<Room, ArrayList<Room>> connect = new Hashtable<>();
    ArrayList<Room> connecting = new ArrayList<Room>();
    Hood hood;
    //ArrayList<String> labInventory;
    //String lab;
    //String hoodNumber;
    //Hashtable<String, ArrayList<String>> hoods;

    public Room (String name, ArrayList<String> inventory, Hashtable<Room, ArrayList<Room>> connect, Hood hood/**, Hashtable<String, ArrayList<String>> hoods*/) { 
        this.name = name;
        this.inventory = inventory;
        //this.connect = connect;
        this.hood = hood;
        this.connect = connect != null ? connect : new Hashtable<>();
    }

    String getName(){
        return this.name;
    }

    String[] acceptedResponses = {
        "shea","buck","strom","queeney","gorin","teaching","hallway","one", 
        "two","three", "four", "grab", "drop", "add", "move", "go", "yes", "no", "help", "exit"
    };
    
    Scanner scanner = new Scanner(System.in);
    public Room (String name, ArrayList<String> inventory) {
        this.name = name;
        this.inventory = inventory;
    }
    //Hashtable<String, ArrayList<String>> connect, String room, ArrayList<String> connecting
    public void connected(Room base, Room b) {
        ArrayList<Room> connecting = new ArrayList<>();
        connecting.add(b);
        this.connect.put(base, connecting);
    }
    public void connected(Room base, Room b, Room c) {
        ArrayList<Room> connecting = new ArrayList<>();
        connecting.add(b);
        connecting.add(c);
        this.connect.put(base, connecting);
    }

    public String connects() {
        if (name.contains("shea")) {
            return "Buck Lab and the Gorin Lab.";
        }
        if (name.contains("gorin")) {
            return "Shea Lab.";
        }
        if (name.contains("queeney")) {
            return "Strom Lab and the hallway.";
        }
        if (name.contains("buck")) {
            return "Strom Lab and the Shea Lab.";
        }
        if (name.contains("strom")) {
            return "Buck Lab and the Queeney Lab.";
        }
        if (name.contains("hallway")) {
            return "Queeney Lab and the Teaching Lab.";
        }
        if (name.contains("teaching")) {
            return "hallway.";
        }
        return "Error";
    }

    public void instructions() {
        System.out.println("INSTRUCTIONS HERE");
    }



}

