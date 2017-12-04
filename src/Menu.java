import java.util.Scanner;

/**
 * Outputs the simulator menu to the user.
 * Enabling them to chose whether to input the message, read from a file or use the Bombe to find unknown settings.
 */
class Menu {

    /** Instantiates a scanner object used to read user input from the command line */
    private Scanner in = new Scanner(System.in);

    /**
     * Output the menu and depending on the users choice run the relevant components of the simulator
     */
    Menu() {
        // Stores the input of the machine
        String encodedMessage;

        // Stores the output of the machine
        String message;

        while (true) {
            displayTopMenu();

            int menuChoice;
            menuChoice = getMenuChoice();

            System.out.println();

            // INPUT MESSAGE
            if (menuChoice == 1) {
                encodedMessage = inputMessage();       // Get the input message from the command line

                // Instantiate a settings object and machine
                EnigmaSetting settings = new EnigmaSetting();
                EnigmaMachine enigmaMachine = new EnigmaMachine();

                // Run the machine with the inputted settings on the encoded message
                message = enigmaMachine.start(encodedMessage, settings);

                System.out.println("The message is: " + message);
                System.out.println();

            // READ FILE
            } else if (menuChoice == 2) {

                // Instantiate an enigma file object which handles: reading, decoding and writing
                new EnigmaFile();

            // BOMBE
            } else if (menuChoice == 3) {
                message = inputMessage();       // Get the input message from the command line
                System.out.println();

                // Instantiate a Bombe to find unknown settings
                new Bombe(message);

            } else if (menuChoice == 4) {
                System.exit(0);
            }
        }
    }

    /**
     * Write the menu to the command line.
     */
    private void displayTopMenu() {
        System.out.println("--------------------------------");
        System.out.println("    Enigma Machine Simulator    ");
        System.out.println("--------------------------------");
        System.out.println("          Jack Corbett          ");
        System.out.println();
        System.out.println("1. Input message");
        System.out.println("2. Read message from file");
        System.out.println("3. Run the Bombe to find unknown settings and decode message");
        System.out.println("4. Quit");
        System.out.print("Input: ");
    }

    /**
     * Check the message the user input only contains alphabetic characters and convert the input to lower case.
     * @return Validated message inputted by user.
     */
    private String inputMessage() {
        // Loop until the user inputs a valid input
        while (true) try {
            System.out.print("Enter the message: ");
            String input = in.next();
            input = input.toLowerCase();

            // Split the input into an array of characters to make it easier to validate each letter
            char[] characters = input.toCharArray();

            // Set a boolean flag to true
            Boolean valid = true;
            // If any character is a digit or not within the ASCII a-z range the flag is set to false
            for (char c : characters) {
                if (Character.isDigit(c) || (int) c < 97 || (int) c > 122) {
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

    /**
     * @return The users input between 1 and 4
     */
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
