import java.util.*;

/**
 * creates new instances of Room
 */
public class Room {
    public Hood sheaOne;
    protected String name;
    protected ArrayList<String> inventory;
    Hashtable<Room, ArrayList<Room>> connect = new Hashtable<>();
    ArrayList<Room> connecting = new ArrayList<Room>();
    Hood hood;
    /**
     * returns the string name corresponding with the hood (allowing it to be printed to give info to player)
     * @return String
     */
    String getName(){
        return this.name;
    }
    //Scanner scanner = new Scanner(System.in);
    /**
     * constructor 
     * @param name
     * @param inventory
     */
    public Room (String name, ArrayList<String> inventory) {
        this.name = name;
        this.inventory = inventory;
    }
    /**
     * connects rooms to each other 
     * @param base
     * @param b
     */
    public void connected(Room base, Room b) {
        ArrayList<Room> connecting = new ArrayList<>();
        connecting.add(b);
        this.connect.put(base, connecting);
    }
    /**
     * connects rooms to each other
     * overloaded to allow two room connections 
     * @param base
     * @param b
     * @param c
     */
    public void connected(Room base, Room b, Room c) {
        ArrayList<Room> connecting = new ArrayList<>();
        connecting.add(b);
        connecting.add(c);
        this.connect.put(base, connecting);
    }
    /**
     * prints out the rooms connected to the room that this method is called to
     * @return String
     */
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
}

