import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

public class GameLoop {
    Random rand = new Random();
    Scanner userInput = new Scanner(System.in);
    //String hoodStation;
    //String name;
    String item;
    Room nLab;
    Integer nHood;
    String currentLocation;
    String responseOne;
    ArrayList<String> carrying = new ArrayList<>();
    String[] inventory = {
        "graduated cyclinger", "beaker", "round bottom flask", "magnesium", "syringe", "magnesium powder",
        "diethyl ether", "bromobenzene", "stirring rod", "benzophenone", "hydrochloric acid", "blah",
        "I see.",
        "It makes you think, doesn't it?",
        "I've never thought about it like that.",
        "Now that's a thought!",
        "It's one of those things, you know?"
      };
    ArrayList<String> reaction;
    ArrayList<String> grignard = new ArrayList<>();
    ArrayList<String> sonogashira = new ArrayList<String>();
    ArrayList<String> dielsAlder = new ArrayList<String>();
    Hashtable<String, ArrayList<String>> hoods = new Hashtable<String, ArrayList<String>>();
    Room shea = new Room("shea", randInventory());
    Room gorin = new Room("gorin", randInventory());
    Room strom = new Room("strom", randInventory());
    Room queeney = new Room("queeney", randInventory());
    Room buck = new Room("buck", randInventory());
    Room hallway = new Room("hallway", new ArrayList<String>());
    Room teaching = new Room ("teaching", randInventory());
    Room baseRoom;
    Integer baseHood;
    
    //public GameLoop() {
    //} 
    ArrayList<String> randInventory() {
        ArrayList<String> randInventory = new ArrayList<>();
        int randomInt = rand.nextInt(10) + 3;
        while (randInventory.size() < randomInt) {
            int rand_int = rand.nextInt(10);
            String finalItem = inventory[rand_int];
            randInventory.add(finalItem);
        }
        return randInventory;
    }

    void setUp () {
        grignard.add(" ");
        grignard.add("round bottom flask");
        grignard.add("magnesium powder");
        grignard.add("hydrochloric acid");
        grignard.add("syringe");
        grignard.add("diethyl ether");
        grignard.add("bromobenzene");
        grignard.add("stirring rod");
        grignard.add("benzophenone");
        sonogashira.add("chemical1");
        sonogashira.add("chemical2");
        sonogashira.add("chemical3");
        sonogashira.add("chemical1");
        sonogashira.add("chemical2");
        sonogashira.add("chemical3");
        dielsAlder.add("chemical1");
        dielsAlder.add("chemical1");
        dielsAlder.add("chemical1");
        dielsAlder.add("chemical1");
        dielsAlder.add("chemical1");
        dielsAlder.add("chemical1");
        buck.connected(buck, shea, strom);
        shea.connected(shea, buck, gorin);
        gorin.connected(gorin, shea, hallway);
        hallway.connected(hallway, gorin, teaching);
        teaching.connected(teaching, hallway, queeney);
        queeney.connected(queeney, hallway, strom);
        strom.connected(strom, queeney, buck);
        hoods.put("shea 1", randInventory());
        hoods.put("shea 2", randInventory());
        hoods.put("shea 3", randInventory());
        hoods.put("shea 4", randInventory());
        hoods.put("gorin 1", randInventory());
        hoods.put("gorin 2", randInventory());
        hoods.put("gorin 3", randInventory());
        hoods.put("gorin 4", randInventory());
        hoods.put("strom 1", randInventory());
        hoods.put("strom 2", randInventory());
        hoods.put("strom 3", randInventory());
        hoods.put("strom 4", randInventory());
        hoods.put("queeney 1", randInventory());
        hoods.put("queeney 2", randInventory());
        hoods.put("queeney 3", randInventory());
        hoods.put("queeney 4", randInventory());
        hoods.put("buck 1", randInventory());
        hoods.put("buck 2", randInventory());
        hoods.put("buck 3", randInventory());
        hoods.put("buck 4", randInventory());
        hoods.put("teaching 1", randInventory());
        hoods.put("teaching 2", randInventory());
        hoods.put("teaching 3", randInventory());
        hoods.put("teaching 4", randInventory());
        baseRoom = shea;
        baseHood = 1;
    }

    void startLocation() {
            System.out.println("Where would you like to set up your reaction? Lab options: Shea, Gorin, Buck, Strom, Queeney. [Default: Shea 1]");
            String lab = userInput.nextLine().toLowerCase();
            baseRoom = checkLab(lab, this.baseRoom);
            System.out.println("fume hood?");
            lab = userInput.nextLine().toLowerCase();
            baseHood = checkHoodResponse(lab, this.baseHood);

            System.out.println("Great! You will set up your reaction in: "+ baseRoom.getName() + " " + baseHood.toString());
    }

    ArrayList<String> startRxn() {
        while (true) {
            System.out.println("What reaction would you like to run? Options: Grignard, Sonogashira, Diels-Alder. ");
            String rxn = userInput.nextLine().toLowerCase();
            if (rxn.contains("grignard")) {
                System.out.println("You will need: X, X, X, X");
                return grignard;
            }
            if (rxn.contains("sonogashira")) {
                System.out.println("You will need: X, X, X, X");
                return sonogashira;
            }
            if (rxn.contains("diels")) {
                System.out.println("You will need: X, X, X, X");
                return dielsAlder;
            } else {
                System.out.println("Sorry, that was not an option. Please enter a new response!");
                continue;
            }
        }
    }

    void instructions() {
        System.out.println("insutrctions");
    }

    public static void main(String[] args) {
        GameLoop gameLoop = new GameLoop();
        System.out.println(" instrunctions ");
        gameLoop.setUp();
        boolean stillPlaying = true;
        Scanner userInput = new Scanner(System.in);
        //gameLoop.currentLocation = gameLoop.startLocation();
        //gameLoop.reaction = gameLoop.startRxn();
        gameLoop.currentLocation = "shea 1";
        gameLoop.reaction = gameLoop.grignard;
        Chemistt chemist = new Chemistt(gameLoop.reaction, gameLoop.baseHood, gameLoop.baseRoom, gameLoop.shea, gameLoop.gorin, gameLoop.buck, gameLoop.strom, gameLoop.queeney, gameLoop.teaching, gameLoop.hallway, gameLoop.hoods);
    
        do {
            // ADD TO STATEMENT AND SAY WHAT LABS YOU CONNECT TO 
            // having issues getting connecting labs 
            String location = chemist.currentRoom.getName() + " " + chemist.currentHood.toString();
            System.out.print("You need to gather: " + chemist.reaction.toString() + ". You are located at " + chemist.currentRoom.getName() +" lab, at fume hood " + chemist.currentHood.toString()+ ". The inventory at this location is: " + chemist.hoods.get(location));
            System.out.print(". This lab connects to the " + chemist.currentRoom.connects());
            if (chemist.currentRoom != gameLoop.hallway) {
                System.out.println(" This lab contains 4 hoods.");
            }
            gameLoop.responseOne = userInput.nextLine().toLowerCase();
            if (gameLoop.responseOne.contains("grab")) {
                // error picking up, doesn't recognize some items and for some items will make you ask twice
                if (gameLoop.checkItem(gameLoop.responseOne)) {
                    String item = gameLoop.returnItem(gameLoop.responseOne);
                    chemist.pickUp(item);
                } else {
                    System.out.println("That is not an accepted item.");
                }
            }
            if (gameLoop.responseOne.contains("go")) {
                Room nLab = gameLoop.checkLab(gameLoop.responseOne, chemist.currentRoom);
                Integer nHood = gameLoop.checkHoodResponse(gameLoop.responseOne, chemist.currentHood);
                chemist.move(nLab, nHood);
            }
            if (gameLoop.responseOne.contains("drop") || chemist.yesNo(gameLoop.responseOne)) {
                // error dropping, maybe with loop? out of bounds
                if (gameLoop.checkItem(gameLoop.responseOne)) {
                    String item = gameLoop.returnItem(gameLoop.responseOne);
                    if (!chemist.drop(chemist.carrying, item)) {
                        System.out.println(item + " is not in your carrying inventory.");
                    } else {
                        System.out.println("successful drop");
                    } 
                } else {
                    System.out.println("That is not an item in the inventory.");
                }
            }
            if (gameLoop.responseOne.contains("exit")) {
                stillPlaying = false;
            }
            if (gameLoop.responseOne.contains("help")) {
                gameLoop.instructions();
            }
            if (chemist.checkHood() || gameLoop.responseOne.equals("exit")) {
                stillPlaying = false;
            }
        } while (stillPlaying);

        if (!gameLoop.responseOne.equals("exit")) {
            System.out.println("Yay, you won!");
        } else { 
            System.out.println("Better luck next time.");
        }
        userInput.close();
    }

    public Room checkLab(String lab, Room currentRoom) {
        lab = lab.toLowerCase();
        if (lab.contains("shea")) {
            return shea;
        }
        if (lab.contains("gorin")) {
            return gorin;
        }
        if (lab.contains("queeney")) {
            return queeney;
        }
        if (lab.contains("strom")) {
            return strom;
        }
        if (lab.contains("buck")) {
            return buck;
        }
        if (lab.contains("teaching")) {
            return teaching;
        }
        if (lab.contains("hallway")) {
            return hallway;
        }
        return currentRoom;
    }

    boolean checkItem(String response) {
        // issue bc some items are multi word
        String[] responseSplit = response.split(" ");
        for (int i = 0; i < responseSplit.length; i++) {
            for (int o = 0; o < inventory.length; o++)
            {
                if (responseSplit[i].equals(inventory[o])) {
                    return true;
                } 
                if (response.contains(inventory[o])) {
                    return true;
                }
            }
            
        }
        return false;
    }

    public String returnItem(String response) {
        // issue bc some items are multi word
        String[] responseSplit = response.split(" ");
        for (int i = 0; i < responseSplit.length; i++) {
            for (int o = 0; o < inventory.length; o++)
            {
                if (responseSplit[i].equals(inventory[o])) {
                    return inventory[o];
                }
                if (response.contains(inventory[o])) {
                    return inventory[o];
                }
            }
            
        }
        return "error";
    }

    public Integer checkHoodResponse(String hood, Integer currentHood) {
        hood = hood.toLowerCase();
        if (hood.contains("1") || hood.contains("one")) {
            return 1;
        }
        if (hood.contains("2")|| hood.contains("two")) {
            return 2;
        }
        if (hood.contains("3")|| hood.contains("three")) {
            return 3;
        }
        if (hood.contains("4")|| hood.contains("four")) {
            return 4;
        }
        return currentHood;
    }

}