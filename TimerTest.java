import java.util.*;

public class TimerTest {
    Timer timer = new Timer();
    boolean result = false; // Use 'boolean' instead of 'Boolean'
    Scanner userInput = new Scanner(System.in);
    String response = "";
    int counter = 5;

    public void start() {
        // Thread for handling timer
        TimerTask task = new TimerTask() {
            public void run() {
                if (counter > 0) {
                    System.out.println(counter + " seconds remaining...");
                    counter--;
                } else {
                    System.out.println("Time's up!");
                    timer.cancel();
                    checkResult();
                }
            }
        };

        // Schedule the timer
        timer.scheduleAtFixedRate(task, 0, 1000);

        // Thread for handling input
        new Thread(() -> {
            while (counter > 0) {
                response = userInput.nextLine();
                if (response.contains("stop reaction")) {
                    result = false;
                    timer.cancel();
                    System.out.println("Reaction stopped!");
                    break;
                }
            }
        }).start();
    }

    public void checkResult() {
        if (response.contains("1")) {
            result = true;
            System.out.println("Success: Result is true.");
        } else {
            System.out.println("Failure: Result is false.");
        }
    }

    public static void main(String[] args) {
        TimerTest timerTest = new TimerTest();
        System.out.println("Enter '1' before the timer ends or 'stop reaction' to stop the timer.");
        timerTest.start();
    }
}
