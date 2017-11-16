
import java.util.Scanner;

public class Menu {

    private Scanner in = new Scanner(System.in);

    public Menu() {
        String message;
        String encodedMessage;

        while (true) {
            displayTopMenu();
            int menuChoice;

            menuChoice = getMenuChoice();

            if (menuChoice == 1) {
                System.out.println();
                encodedMessage = inputMessage("decode");

                EnigmaSetting settings = new EnigmaSetting();
                EnigmaMachine enigmaMachine = new EnigmaMachine();

                message = enigmaMachine.start(encodedMessage, settings);

                System.out.println("The message is: " + message);
                System.out.println();

            } else if (menuChoice == 2) {

                EnigmaFile enigmaFile = new EnigmaFile();

            } else if (menuChoice == 3) {
                message = inputMessage("decode");
                System.out.println();

                Bombe bombe = new Bombe(message);

            } else if (menuChoice == 4) {
                EnigmaSetting settings = new EnigmaSetting();
                EnigmaMachine enigmaMachine = new EnigmaMachine();

                message = inputMessage("encode");
                System.out.println();

                encodedMessage = enigmaMachine.start(message, settings);

                System.out.println("The encoded message is: " + encodedMessage);
                System.out.println();

            } else {
                System.exit(0);
            }
        }
    }

    private void displayTopMenu() {
        System.out.println("--------------------------------");
        System.out.println("    Enigma Machine Simulator    ");
        System.out.println("--------------------------------");
        System.out.println("          Jack Corbett          ");
        System.out.println();
        System.out.println("Decode:");
        System.out.println("    1. Input message to decode");
        System.out.println("    2. Read message to decode from file");
        System.out.println("    3. Run the Bombe to find unknown settings and decode message");
        System.out.println("Encode:");
        System.out.println("    4. Input message to encode");
        System.out.print("Input: ");
    }

    private String inputMessage(String option) {
        while (true) {
            try {
                System.out.print("Enter the message to " + option + ": ");
                String input = in.next();

                char[] characters = input.toCharArray();

                Boolean valid = true;
                for (char c : characters) {
                    if (Character.isDigit(c) || (int)c < 97 || (int)c > 122)  {
                        valid = false;
                    }
                }

                if (valid) {
                    return input;
                } else throw new Exception();

            } catch (Exception e) {
                System.err.println("Error: Invalid message please try again");
                System.err.flush();
                System.out.println();
            }
        }
    }

    private int getMenuChoice() {
        while (true) try {
            int input = in.nextInt();

            if (input >= 1 & input <= 4) {
                return input;
            } else throw new Exception();

        } catch (Exception e) {
            System.err.print("Error: Invalid input please choose a menu option. Input: ");
            in.next();
        }
    }

}
