import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.Timer;
/**
 * The game loop is responsible for running the game. It sets up a new instance of chemist, as well as all of the rooms and the hood inventories.
 * The game loop also has all of the code for the second level of the game, which did not really require its own class.
 */

public class GameLoop {
    Random rand = new Random();
    Scanner userInput = new Scanner(System.in);
    ArrayList<String> canAdd = new ArrayList<>();
    String location;
    String item;
    Room nLab;
    Integer nHood;
    String currentLocation;
    String responseOne;
    ArrayList<String> carrying = new ArrayList<>();
    String[] runR;
    String[] inventory = {
        "graduated cyclinder",  "round bottom flask",  "magnesium powder",
        "diethyl ether", "bromobenzene", "stirring rod", "palladium(iii) acetate", "beaker", "erlenmeyer flask",
        "pipettes",  "oil bath", "hydrochloric acid", "separatory funnel", "aluminum chloride", "balance", "stir bar", "reflux condenser", 
        "triethylamine", "toluene", "sodium sulfite", "sulfuric acid", "methanol", "hexane", "ethyl acetate", "ammonium hydroxide", 
        "acetone", "iodobenzene", "benzophenone","cyclohexane","stir plate", 
        "cyclopentadiene", "heating mantle","maleic anhydride", "phenylacetylene", "magnesium sulfate", "syringe","calcium chloride", "pyridine", "isopropanol", 
        "cyclohexanone", "phenanthrene",  "benzyl alcohol", "volumetric flasks", "copper(i) iodide", "graduated cylinders", "watch glasses",
        "thermometer", "sand bath", "nitrogen tubing", "filter paper", "buchner funnel"
      };
    ArrayList<String> measurables = new ArrayList<>();
    ArrayList<String> reaction = new ArrayList<>();
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
    String baseLoc;
    Timer timer = new Timer();
    int counter = 5;
    Boolean result = false;
    ArrayList<String> copyR = new ArrayList<>();
    String response = "";

    public void start() {
        // Thread for handling timer
        TimerTask task = new TimerTask() {
            public void run() {
                if (counter > 0) {
                    System.out.println(counter + " seconds remaining...");
                    counter--;
                } else {
                    timer.cancel();
                    checkResult();
                }
            }
        };

        // Schedule the timer
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void startWrongRxn() {
        // Thread for handling timer
        TimerTask task = new TimerTask() {
            public void run() {
                if (counter > 0) {
                    System.out.println(counter + " seconds remaining...");
                    counter--;
                } else {
                    System.out.println("Your reaction FAILED >:( you did not have all of the chemicals and equipment needed!)");
                    timer.cancel();
                }
            }
        };

        // Schedule the timer
        timer.scheduleAtFixedRate(task, 0, 1000);
    }      

    boolean checkResult() {
        int randomInt = rand.nextInt(5);
        if (randomInt == 4) {
            System.out.println("Your reaction failed >:( better luck next time!)");
            return false;
        } else {
            System.out.println("YAY! Your reaction worked!!");
            return true;
        }
    }

    /**
     * creates a new random inventory each time it is called, adding items from the set inventory. 
     * @return randInventory
     */
    
    ArrayList<String> randInventory() {
        ArrayList<String> randInventory = new ArrayList<>(); 
        int randomInt = rand.nextInt(5) + 1;
        while (randInventory.size() < randomInt) {
            int rand_int = rand.nextInt(inventory.length);
            String finalItem = inventory[rand_int];
            randInventory.add(finalItem);
        }
        return randInventory;
        
    }

   ArrayList<String> randInventory(int c, int p) {
        ArrayList<String> randInventory = new ArrayList<>(); 
        while (c < p && p < inventory.length && c < inventory.length) {
            randInventory.add(inventory[c]);
            c++;
        }
        int randomInt = rand.nextInt(5) + 1;
        while (randInventory.size() < randomInt) {
            int rand_int = rand.nextInt(inventory.length);
            String finalItem = inventory[rand_int];
            randInventory.add(finalItem);
        }
        return randInventory;
        
    }
    
    /**
     * sets up the reaction arrays, the room connections, and the hood inventories 
     * sets the default base hood as shea 1
     * sets up the "measurables" array which includes all reaction chemicals that need to be measured before being added to a reaction
     */
    void setUp () {
        grignard.add("round bottom flask");
        grignard.add("magnesium powder");
        grignard.add("hydrochloric acid");
        grignard.add("syringe");
        grignard.add("diethyl ether");
        grignard.add("bromobenzene");
        grignard.add("benzophenone");

        sonogashira.add("triethylamine");
        sonogashira.add("toluene");
        sonogashira.add("oil bath");
        sonogashira.add("iodobenzene");
        sonogashira.add("phenylacetylene");
        sonogashira.add("palladium(iii) acetate");
        sonogashira.add("copper(i) iodide");
        sonogashira.add("stir plate");
        sonogashira.add("stir bar");
        sonogashira.add("syringe");
        sonogashira.add("round bottom flask");
        sonogashira.add("nitrogen tubing");

        dielsAlder.add("cyclopentadiene");
        dielsAlder.add("maleic anhydride");
        dielsAlder.add("aluminum chloride");
        dielsAlder.add("toluene");
        dielsAlder.add("reflux condenser");
        dielsAlder.add("oil bath");
        dielsAlder.add("stir plate");
        dielsAlder.add("stir bar");
        dielsAlder.add("syringe");
        dielsAlder.add("round bottom flask");

        measurables.add("cyclopentadiene");
        measurables.add("maleic anhydride");
        measurables.add("aluminum chloride");
        measurables.add("stir bar");
        measurables.add("toluene");
        measurables.add("copper(i) iodide");
        measurables.add("palladium(iii) acetate");
        measurables.add("iodobenzene");
        measurables.add("phenylacetylene");  
        measurables.add("triethylamine");
        measurables.add("magnesium powder");
        measurables.add("hydrochloric acid");
        measurables.add("diethyl ether");
        measurables.add("bromobenzene");
        measurables.add("benzophenone");


        buck.connected(buck, shea, strom);
        shea.connected(shea, buck, gorin);
        gorin.connected(gorin, shea);
        hallway.connected(hallway, queeney, teaching);
        teaching.connected(teaching, hallway, queeney);
        queeney.connected(queeney, hallway, strom);
        strom.connected(strom, queeney, buck);
        hoods.put("shea 1", new ArrayList<>(randInventory(0, 2)));
        hoods.put("shea 2", new ArrayList<>(randInventory(2, 3)));
        hoods.put("shea 3", new ArrayList<>(randInventory(3, 4)));
        hoods.put("shea 4", new ArrayList<>(randInventory(4, 6)));
        hoods.put("gorin 1", new ArrayList<>(randInventory(6, 8)));
        hoods.put("gorin 2", new ArrayList<>(randInventory(8, 10)));
        hoods.put("gorin 3", new ArrayList<>(randInventory(10, 14)));
        hoods.put("gorin 4", new ArrayList<>(randInventory(14, 15)));
        hoods.put("strom 1", new ArrayList<>(randInventory(15, 16)));
        hoods.put("strom 2", new ArrayList<>(randInventory(16, 18)));
        hoods.put("strom 3", new ArrayList<>(randInventory(18, 20)));
        hoods.put("strom 4", new ArrayList<>(randInventory(20, 22)));
        hoods.put("queeney 1", new ArrayList<>(randInventory(22, 24)));
        hoods.put("queeney 2", new ArrayList<>(randInventory(24, 26)));
        hoods.put("queeney 3", new ArrayList<>(randInventory(26, 28)));
        hoods.put("queeney 4", new ArrayList<>(randInventory(28, 31)));
        hoods.put("buck 1", new ArrayList<>(randInventory(31, 33)));
        hoods.put("buck 2", new ArrayList<>(randInventory(33, 35)));
        hoods.put("buck 3", new ArrayList<>(randInventory(35, 38)));
        hoods.put("buck 4", new ArrayList<>(randInventory(38, 43)));
        hoods.put("teaching 1", new ArrayList<>(randInventory(43, 46)));
        hoods.put("teaching 2", new ArrayList<>(randInventory(46, 48)));
        hoods.put("teaching 3", new ArrayList<>(randInventory(48, 49)));
        hoods.put("teaching 4", new ArrayList<>(randInventory(49, inventory.length)));
        baseRoom = shea;
        baseHood = 1;
    }

    /**
     * prompts the user to choose a base hood and base array
     * @return base room plus base hood, which acts as a specific location identifier
     */
    String startLocation() {
            System.out.println("Choose a lab and hood to set up your reaction. Lab options: Shea, Gorin, Buck, Strom, Queeney. Hood options: 1, 2, 3, 4.[Default: Shea 1]");
            String lab = userInput.nextLine().toLowerCase();
            baseRoom = checkLab(lab, this.baseRoom);
            baseHood = checkHoodResponse(lab, this.baseHood);
            System.out.println("Great! You will set up your reaction in: "+ baseRoom.getName() + " " + baseHood.toString());
            return baseRoom.getName() + " " + baseHood.toString();
    }

    /**
     * prompts user to choose reaction. unlike for the hoods, there is no default reaction, so the user has to continue until they have 
     * chosen an acceptable reaction
     * @return reaction
     */
    ArrayList<String> startRxn() {
        while (true) {
            System.out.println("What reaction would you like to run? Options: Grignard, Sonogashira, Diels-Alder. ");
            String rxn = userInput.nextLine().toLowerCase();
            if (rxn.contains("grignard")) {
                for (int i = 0; i < grignard.size(); i++) {
                    reaction.add(grignard.get(i));
                    copyR.add(grignard.get(i)); 
                }
                return reaction;
            }
            if (rxn.contains("sonogashira")) {
                for (int i = 0; i < sonogashira.size(); i++) {
                    reaction.add(sonogashira.get(i));
                    copyR.add(sonogashira.get(i)); 
                }
                return reaction;
            }
            if (rxn.contains("diels")) {
                for (int i = 0; i < dielsAlder.size(); i++) {
                    reaction.add(dielsAlder.get(i));
                    copyR.add(dielsAlder.get(i)); 
                }
                return reaction;
            } else {
                System.out.println("Sorry, that was not an option. Please enter a new response!");
                continue;
            }
        }
    }
    /**
     * prints out instructions for the first level of the game
     */
    void instructions() {
        System.out.println("The goal of this game is to run a reaction. In order to start the reaction, you must first run around to the research labs, gathering reagents and equipment.");
        System.out.println("Each lab has four hoods, which you can accessing by inputting 'go' and the number of the hood you would like to go to. Similarly, adjacent labs can be accessed with 'go' and the name of the lab.");
        System.out.println();
        System.out.println("ACCEPTED COMMANDS: go, grab, drop, exit, help. Good luck!");
    }
    /**
     * prints out instructions for the second level of the game
     */
    void instructionsRxn() {
        System.out.println("Let's start your reaction! First set up your equipment, then measure and add your reagents. Finally, start the timer. Only start the timer when you are sure you have measured and added everything you need! Your reaction will run for five seconcds, and then you will find out whether or not it worked!"); 
        System.out.println("NEW ACCEPTED COMMANDS: set up, measure, add, start timer, exit, help.");
    }
    /**
     * game loop for second level of the game where the player sets up and runs the reaction by setting up the equipment, measuring the 
     * reagents, and adding them. finally, the player can start the timer for the reaction (the timer isn't really working right now )
     * @param chemist
     * @param stillPlaying
     */
    void runRxn(Chemistt chemist, Boolean stillPlaying) {
        ArrayList<String> empty = new ArrayList<>();
        instructionsRxn();
        while (stillPlaying) {
            System.out.println("Components of the reaction: " + copyR.toString());
            //System.out.println(empty.toString());
            responseOne = userInput.nextLine();
            if (responseOne.contains("set up")) {
                if (responseOne.contains("round bottom flask") || responseOne.contains("oil bath") || responseOne.contains("heating mantle")||responseOne.contains("reflux condensor") || responseOne.contains("stir plate") || responseOne.contains("nitrogen tubing")) {
                    String item = returnItem(responseOne);
                    empty.add(item);
                } else {
                    System.out.println("That item does not need to be set up. Try measuring it.");
                }
            }
            //checks to make sure an item needs to be measured 
            if (responseOne.contains("measure")) {
                int x = 0;
                for (int i = 0; i < measurables.size(); i++) {
                    if (responseOne.contains(measurables.get(i))) {
                        if (empty.contains("syringe")) {
                            canAdd.add(measurables.get(i));
                            x++;
                            continue;
                        } else {
                            // if no items have been measured, it checks what you want to measure with (since syringe is used for all reactions)
                            System.out.println("What would you like to measure this item with? ");
                            responseOne = userInput.nextLine();
                            if (responseOne.contains("syringe")) {
                                empty.add("syringe");
                                canAdd.add(measurables.get(i));
                                x++;
                            } else {
                                System.out.println("Response not accepted. Item was not measured. ");
                            }
                        }
                    }
                }
                if (x == 0) {
                    System.out.println("That item cannot be measured.");
                }
            }
            // checks if the user has set up the reaction flask and measured the chemical before adding to the reaction array
            if (responseOne.contains("add")) {
                if (empty.contains("round bottom flask")) {
                    int b = 0;
                    for (int i = 0; i < measurables.size(); i++) {
                        if (canAdd.contains(measurables.get(i)) && !empty.contains(measurables.get(i)) && responseOne.contains(measurables.get(i))) {
                            empty.add(measurables.get(i));
                            b++;
                        } 
                    }
                    if (b == 0) {
                        System.out.println("Item needs to be measured first, has already been added to the reaction or is not an accepted item.");
                    }
                } else {
                    System.out.println("Must set up round bottom flask first.");
                }
            }
            // prevent the user from starting timer before everything is added (new array contains everything from copy of reaction array)
            if (responseOne.contains("start time")) {
                if (copyR.containsAll(empty) && empty.containsAll(copyR)) {
                    start();
                    stillPlaying = false;
                } else {
                    startWrongRxn();
                    stillPlaying = false;
                }
            }
            if (responseOne.contains("exit")) {
                stillPlaying = false;
            }
            if (responseOne.contains("help")) {
                instructionsRxn();
            }
        }
    }
    /**
     * main method for the first level of the game
     * sets up current location, new instance of chemistt
     * checks user response each round and calls corresponding methods 
     * @param args
     */
    public static void main(String[] args) {
        GameLoop gameLoop = new GameLoop();
        gameLoop.instructions();
        gameLoop.setUp();
        boolean stillPlaying = true;
        Scanner userInput = new Scanner(System.in);
        gameLoop.currentLocation = gameLoop.startLocation();
        gameLoop.reaction = gameLoop.startRxn();
        Chemistt chemist = new Chemistt(gameLoop.reaction, gameLoop.baseHood, gameLoop.baseRoom, gameLoop.shea, gameLoop.gorin, gameLoop.buck, gameLoop.strom, gameLoop.queeney, gameLoop.teaching, gameLoop.hallway, gameLoop.hoods);
        gameLoop.location = chemist.baseRoom.getName() + " " + chemist.baseHood.toString();
        gameLoop.baseLoc = chemist.baseRoom.getName() + " " + chemist.baseHood.toString();
        // empties the inventory of the hood that the player is starting their reaction in
        for (int i = gameLoop.hoods.get(gameLoop.baseLoc).size()-1; i >=0; i--) {
            gameLoop.hoods.get(gameLoop.baseLoc).remove(i);
        }
        // game loop
        do {
            gameLoop.location = chemist.currentRoom.getName() + " " + chemist.currentHood.toString();
            System.out.println("You need to gather: " + chemist.copyRxn.toString());
            System.out.println();
            System.out.println("You are carrying: " + chemist.carrying.toString());
            System.out.println();
            System.out.println("You are located at: " + gameLoop.location + ". The inventory at this hood is: "+ chemist.hoods.get(gameLoop.location));
            System.out.println();
            System.out.println("This lab connects to the " + chemist.currentRoom.connects());
            if (gameLoop.location.equals(gameLoop.baseLoc) && !chemist.carrying.isEmpty()) {
                chemist.addtoHood();
            }
            gameLoop.responseOne = userInput.nextLine().toLowerCase();
            if (gameLoop.responseOne.contains("grab")) {
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
            if (gameLoop.responseOne.contains("drop")) {
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
        // allows player to choose whether they would like to continue on in the game
        if (!gameLoop.responseOne.equals("exit")) {
            System.out.println("Yay, your reaction is ready to be run! Would you like to proceed to the next level and start your reaction? Yes/No: ");
            gameLoop.responseOne = userInput.nextLine();
            if (chemist.yesNo(gameLoop.responseOne)) {
                stillPlaying = true;
                gameLoop.runRxn(chemist, stillPlaying);
            } else {
                System.out.println("Goodbye!");
            }    
        } else { 
            System.out.println("Better luck next time.");
        }
        userInput.close();
    }
    /**
     * checks the user input for what lab they want to go to, returns the object Room corresponding to the name
     * returns currentRoom if the user does not input any accepted labs
     * @param lab
     * @param currentRoom
     * @return currentRoom
     */
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

    /**
     * checks inventory to see if the item the player wants is in the inventory 
     * @param response
     * @return boolean
     */
    boolean checkItem(String response) {
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
    /**
     * returns the (String) item in the inventory that the user wants 
     * returns error (this code should be unreachable because if the item is not in the inventory the user would have found out from the result of checkItem(response))
     * @param response
     * @return item
     */
    public String returnItem(String response) {
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
    /**
     * takes the user input and returns the Integer corresponding with the hood they want to go to, returns currentHood if there is not an 
     * acceptable number in the string
     * @param hood
     * @param currentHood
     * @return Integer
     */
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