import java.util.*;
public class RunRxn {
    
//import javax.management.RuntimeErrorException;
    String chemist;
    ArrayList<String> hoodInventory;
    ArrayList<String> reaction;
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    int positionFB;
    int positionLR;
    int solventAmt;
    int flaskSize;
    String[] rxnObservation = {
        "bubbling.",
        "fizzing.",
        "turning blue.",
        "turning red.",
        "overflowing.",
        "heating up.",
        "not working.",
        "successful.", 
        "looking rather odd.",
        "colorless."
    };
    /**
     * initializes chemist and inventory
     * @param chemist
     * @param hoodInventory
     * @param reaction
     * @param flaskSize
     */
    public RunRxn (String chemist, ArrayList<String> hoodInventory, ArrayList<String> reaction, int flaskSize) {
        this.chemist = chemist;
        this.hoodInventory = new ArrayList<String>();
        this.reaction = new ArrayList<String>();
        this.positionFB = 0;
        this.positionLR = 0;
        this.flaskSize = flaskSize;
        // this.reaction = reaction;
        // this.hoodInventory = hoodInventory;
    }
    /**
     * adds reagents and solvent to the flask (solvnt amount depends on flask size)
     * @param reagent1
     * @param reagent2
     * @param reagent3
     * @param flaskSize
     */
    public void startRxn(String reagent1, String reagent2, String reagent3, int flaskSize) {
        this.solventAmt = flaskSize / 2;
        System.out.println("The flask contains " + solventAmt + " mL of solvent.");
        drop(reagent1);
        drop(reagent2);
        drop(reagent3);
        System.out.println("The reaction has started. Make sure to monitor the reaction.");
    }
    /**
     * adds item to reaction
     * @param item
     * @return String
     */
    public String drop(String item) {
        reaction.add(item);
        return item + " has been added to the reaction.";
    }
    /**
     * offers a random observation of the reaction
     * @param item
     */
    public void examine(String item) {
        int rand_int = rand.nextInt(10);
        System.out.println("You are examining the reaction. The reaction contains: " + reaction.toString());
        System.out.println("The reaction is " + rxnObservation[rand_int]);
    }
    /**
     * explains what common lab items are used for 
     * asks if the chemist wants to use the item or return it 
     * @param item
     */
    public void use(String item) {
        item = item.toLowerCase();
        if (item.contains("flask")) {
            System.out.println("Erlenmeyer flasks are used to measure volumes and store solvents. Round-bottom-flasks are often used to run reactions.");
        } 
        if (item.contains("beaker")) {
            System.out.println("Beakers can be used to measure approximates volumes. They are also commonly used to store solvents while working.");
        }
        if (item.contains("pipette")) {
            System.out.println("Pipettes are used for transferring small amounts of liquid.");
        }
        if (item.contains("tweezers")) {
            System.out.println("Tweezers are used for taking TLC plates out of the chamber.");
        }
        if (item.contains("graduated cyclinder")) {
            System.out.println("Graduated cylinders are used for measuring liquids. There are many sizes of graduated cylinders.");
        } 
        while (true) {
            System.out.println("Type 'ONE' to use " + item + " now, or type 'TWO' to add it to your inventory.");
            String response = scanner.nextLine();
            if (response.contains("ONE")) {
                System.out.println("You are now using " + item);
                break;
            }
            if (response.contains("TWO")) {
                hoodInventory.add(item);
                System.out.println("Your inventory contains: " + hoodInventory.toString());
                break;
            }
            if (!response.contains("ONE") && !response.contains("TWO")) {
                System.out.println("Response not accepted. Try again. ");
                continue;
            }
        }
        
    }

    /**
     * lowers the amount of solvent in the flask, unless the solvent level is too low 
     * returns amount of solvent in flask
     * @return Number
     */
    public Number shrink(){
        while (true) {
            System.out.println("Would you like to reduce the amount of solvent in the flask? Y/N: ");
            String yN = scanner.nextLine();
            if (yN.contains("Y")) {
                this.solventAmt -= 5;
                if (this.solventAmt < 5){
                    System.out.println("Solvent level too low. Add solvent.");
                    grow();
                    continue;
                } else {
                    System.out.println("The solvent level is now at " + Integer.toString(this.solventAmt) + " mL.");
                    return this.solventAmt;
                }
            }
            if (yN.contains("N")) {
                System.out.println("Ok. The current solvent level is " + Integer.toString(this.solventAmt) + " mL.");
                return this.solventAmt;
            } else {
                System.out.println("Response not accepted. Please try again.");
                continue;
            }
        }
    }
    /**
     * adds solvent to the flask, unless the flask is too full
     * returns amount of solvent in flask
     * @return Number
     */
    public Number grow(){
        while (true) {
            if (solventAmt > (flaskSize - 10)) {
                System.out.println("Solvent level is too high. Please reduce solvent amount.");
                shrink();
                continue;
            } else {
                System.out.println("Would you like to add to the amount of solvent in the flask? Y/N: ");
                String yN = scanner.nextLine();
                if (yN.contains("Y")) {
                    this.solventAmt += 5;
                    while (true) {
                    System.out.println("The solvent level is now at " + Integer.toString(this.solventAmt) + " mL. Would you like to add more solvent? Y/N: ");
                    String solv = scanner.nextLine();
                    if (solv.contains("Y")) {
                        grow();
                        break;
                    } 
                    if (solv.contains("N")) {
                        return solventAmt;
                    } else {
                        System.out.println("Response not accepted. Please try again.");
                        continue;
                    }
                    }
                }
                if (yN.contains("N")) {
                    System.out.println("Ok. The current solvent level is " + Integer.toString(this.solventAmt) + " mL.");
                    return solventAmt;
                } else {
                    System.out.println("Response not accepted. Please try again.");
                    continue;
                }
            }
        }
    }
    /**
     * explains that the reaction is 'resting'
     */
    public void rest() {
        System.out.println("The reaction is allowed to stir for 30 minutes. Use this time to make observations or prepare for the next step.");
    }
    /**
     * offers solutions for errors in the reaction
     */
    public void undo() {
        while (true) {
            System.out.println("If an error has been made, please start the reaction over. Would you like to start over? Y/N: ");
            String response = scanner.nextLine();
            if (response.contains("Y")) {
                System.out.println("Starting reaction over. The flask has been emptied. ");
                this.reaction.clear();
                System.out.println("Input reagent 1: ");
                String reagent1 = scanner.nextLine();
                System.out.println("Input reagent 2: ");
                String reagent2 = scanner.nextLine();
                System.out.println("Input reagent 3: ");
                String reagent3 = scanner.nextLine();
                System.out.println("Input flask size: ");
                int flaskSize = scanner.nextInt();
                scanner.nextLine();
                startRxn(reagent1, reagent2, reagent3, flaskSize);
                break;
            }
            if (response.contains("N")) {
                System.out.println("Please refer to your TA or instructor for further guidance.");
                break;
            } else {
                System.out.println("Response not accepted. Please try again.");
                continue;
            }
        }
        
    }
    /**
     * creates new chemist and tests the methods
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<String> hoodOneInv = new ArrayList<String>();
        hoodOneInv.add("TLC Plates");
        hoodOneInv.add("Pipettes");
        hoodOneInv.add("Beaker");
        hoodOneInv.add("Tweezers");
        hoodOneInv.add("50 mL RBF");
        ArrayList<String> reaction = new ArrayList<String>();
        int flask = 100;
        Chemist rachel = new Chemist("Rachel", hoodOneInv, reaction, flask);
        rachel.startRxn("Methyl grignard", "carbonyl", "pyridine", flask);
        rachel.grab("100 mL graduated cylinder");
        rachel.drop("Dry ice");
        rachel.examine(reaction.toString());
        rachel.use("Pipette");
        rachel.walk("right");
        rachel.fly(5, 7);
        rachel.shrink();
        rachel.grow();
        rachel.rest();
        rachel.undo();
    }
        
}
    
