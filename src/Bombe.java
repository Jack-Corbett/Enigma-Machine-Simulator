import java.util.Scanner;

/**
 * Represents the Bombe machine used to crack the Enigma Code.
 * It tries many combinations of settings to find the message containing a known word.
 */
class Bombe {

    /** Uses the scanner class to read from the command line */
    private Scanner in = new Scanner(System.in);

    /** Instantiates a new Enigma Machine */
    private EnigmaMachine enigmaMachine = new EnigmaMachine();

    /** The decoded message */
    private String message = "";

    /** The message we are trying different settings to decode */
    private String encodedMessage;

    /** The word we know is contained in the message. This makes it much easier to find the settings */
    private String searchWord;

    /**
     * Depending on the user choice runs the appropriate test harness to find the settings.
     * @param inputMessage The encoded message for which the settings are unknown.
     */
    Bombe(String inputMessage) {
        encodedMessage = inputMessage;

        System.out.print("Enter a word you know is contained in the message: ");
        searchWord = in.next().toLowerCase();

        displayMenu();  //Output the menu to the console

        int menuChoice;
        menuChoice = getMenuChoice();

        if (menuChoice == 1) {
            missingPlugs();

        } else if (menuChoice == 2) {
            rotorPositions();

        } else if (menuChoice == 3) {
            rotorTypes();
        }
    }

    /**
     * Outputs the menu to the console for the user to choose which settings are unknown.
     */
    private void displayMenu() {
        System.out.println();
        System.out.println("------------- BOMBE -------------");
        System.out.println();
        System.out.println("Which settings are unknown?");
        System.out.println("1. The ends of two plugs");
        System.out.println("2. Rotor starting positions");
        System.out.println("3. Rotor types");
        System.out.print("Input: ");
    }

    /**
     * Test every combination of ends for two unknown plugs to find the settings and decode the message.
     */
    private void missingPlugs() {

        /* Instantiate an enigma settings object to store the machine settings.
         * Pass Plugboard as the unknown so it won't ask the user for the end of each plug they add. */
        EnigmaSetting setting = new EnigmaSetting("Plugboard");

        // Get the number of plugs from the settings object
        int noOfPlugs = setting.getNoOfPlugs();

        // Create a new array to store the starts of each plug which the user inputted
        int[] start = new int[noOfPlugs];

        // Add the start of the plugs to an array so we don't check plugboard slots that are already used
        for (int i = 0; i < noOfPlugs; i++) {
            start[i] = (int) setting.getPlugStart(i);
        }

        System.out.println("Results (multiple):");

        /* Two for loops loop through every combination of end points for the two plugs.
         * Count from 97 to 122 which represent a to z in ASCII codes. */
        for (int i = 97; i < 123; i++) {
            // If the plugboard slot is not already taken
            if (start[0] != i && start[1] != i) {

                // Set the plug end of the first plug to character represented by i
                setting.setUnknownPlugEnd(0, (char) i);

                // Loop through every character for the second plug
                for (int j = 97; j < 123; j++) {

                    // If j represents a character that's socket isn't already taken
                    if (start[0] != j && start[1] != j && i != j) {

                        // Set the second plug end to the character represented by j
                        setting.setUnknownPlugEnd(1, (char) j);

                        // Run the enigma machine to test the settings
                        message = enigmaMachine.start(encodedMessage, setting);

                        // If the message output from the enigma machine contains the known word output it
                        if (message.contains(searchWord)) {

                            System.out.println(message);

                            // Output the plug settings used to get the answer
                            System.out.print("The plugs were: ");

                            // Loop through each plug outputting it's start and end
                            for (int l = 0; l < noOfPlugs; l++) {
                                System.out.print(setting.getPlugStart(l) + "," + setting.getPlugEnd(l));
                                if (l < noOfPlugs - 1) {
                                    System.out.print(" ");
                                }
                            }
                            System.out.println();
                        }
                    }
                }
            }
        }
        System.out.println();
    }

    /**
     * Test every combination of rotor starting positions to find the settings and decode the message.
     */
    private void rotorPositions() {

        /* Instantiate a new settings object to store the machine settings.
         * Pass RotorPosition as the unknown so it won't ask the user for the starting positions of the rotors. */
        EnigmaSetting setting = new EnigmaSetting("RotorPosition");

        System.out.println("Result: ");

        // Three for loops loop through each starting position from 0 - 26 for each rotor
        for (int i = 0; i < 26; i++) {
            // Set the position of the first rotor
            setting.setRotorPosition(0, i);
            for (int j = 0; j < 26; j++) {
                // Set the position of the second rotor
                setting.setRotorPosition(1, j);
                for (int k = 0; k < 26; k++) {
                    // Set the position of the third rotor
                    setting.setRotorPosition(2, k);

                    // Run the enigma machine to test the settings
                    message = enigmaMachine.start(encodedMessage, setting);

                    // If the message output from the enigma machine contains the known word output it
                    if (message.contains(searchWord)) {

                        System.out.println(message);

                        // Output the starting positions of the rotors
                        System.out.print("The starting positions were: ");
                        for (int l = 0; l < 3; l++) {
                            if (l < 2) {
                                System.out.print(setting.getStartingPosition(l) + ", ");
                            } else {
                                System.out.print(setting.getStartingPosition(l));
                            }
                        }
                        System.out.println();
                    }
                }
            }
        }
        System.out.println();
    }

    /**
     * Test every combination of rotor types to find the settings and decode the message.
     */
    private void rotorTypes() {

        // An array containing all the rotor types to make it easy to iterate through them
        String[] rotorTypes = {"I", "II", "III", "IV", "V"};

        /* Instantiate a new settings object to store the machine settings.
         * Pass RotorType as the unknown so it won't ask the user for the type of rotor used.
         * It does not test for turnover rotors. */
        EnigmaSetting setting = new EnigmaSetting("RotorType");

        System.out.println("Result: ");

        // Three for loops loop through the rotor types array for each rotor
        for (int i = 0; i < 5; i++) {
            // Set the type of the first rotor
            setting.setRotorType(0, rotorTypes[i]);
            for (int j = 0; j < 5; j++) {
                // Set the type of the second rotor
                setting.setRotorType(1, rotorTypes[j]);
                for (int k = 0; k < 5; k++) {
                    // Set the type of the third rotor
                    setting.setRotorType(2, rotorTypes[k]);

                    // Run the enigma machine to test the settings
                    message = enigmaMachine.start(encodedMessage, setting);

                    // If the message output from the enigma machine contains the known word output it
                    if (message.contains(searchWord)) {

                        System.out.println(message);

                        // Output the types of rotor used by looping through each
                        System.out.print("The rotor types were: ");
                        for (int l = 0; l < 3; l++) {
                            if (l == 2) {
                                System.out.print(setting.getRotorType(l));
                            } else {
                                System.out.print(setting.getRotorType(l) + ", ");
                            }
                        }
                        System.out.println();
                    }
                }
            }
        }
        System.out.println();
    }

    /**
     * Allow the user to input their menu choice and validate to check it matches a menu option.
     * @return the users valid input.
     */
    private int getMenuChoice() {
        // Loops until the users input is valid
        while (true) try {
            int input = in.nextInt();
            // If the input is an integer between 1 and 3 return it
            if (input >= 1 & input <= 3) {
                return input;
            } else throw new Exception();

        } catch (Exception e) {
            System.err.print("Error: Invalid input please choose a menu option. Input: ");
            in.next();
        }
    }
}
