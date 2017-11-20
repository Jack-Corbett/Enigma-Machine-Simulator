import java.util.Scanner;

/**
 * Represents the Bombe machine used to crack the Enigma Code.
 * It tries many combinations of settings to find a message containing a known word.
 */
class Bombe {

    private Scanner in = new Scanner(System.in);

    /* Instantiates a new Enigma Machine */
    private EnigmaMachine enigmaMachine = new EnigmaMachine();

    private String message = "";
    private String encodedMessage;
    private String searchWord;

    /**
     * Depending on the user choice runs the appropriate test harness to find the settings.
     *
     * @param inputMessage The encoded message for which the settings are unknown.
     */
    Bombe(String inputMessage) {
        encodedMessage = inputMessage;

        System.out.print("Enter a word you know is contained in the message: ");
        searchWord = in.next();

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
        System.out.println("Which settings are unknown");
        System.out.println("1. The ends of two plugs");
        System.out.println("2. Rotor starting positions");
        System.out.println("3. Rotor types");
        System.out.print("Input: ");
    }

    /**
     * Test every combination of ends for two unknown plugs to find the settings and decode the message.
     */
    private void missingPlugs() {

        EnigmaSetting setting = new EnigmaSetting("Plugboard");

        int noOfPlugs = setting.getNoOfPlugs();
        int[] start = new int[noOfPlugs];

        for (int i = 0; i < noOfPlugs; i++) {
            start[i] = (int) setting.getPlugStart(i);
        }

        System.out.println("Results:");

        for (int i = 97; i < 123; i++) {
            if (start[0] != i || start[1] != i) {
                setting.setUnknownPlugEnd(0, (char) i);

                for (int j = 97; j < 123; j++) {

                    setting.setUnknownPlugEnd(1, (char) j);

                    message = enigmaMachine.start(encodedMessage, setting);

                    if (message.contains(searchWord)) {
                        System.out.println(message);
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

        EnigmaSetting setting = new EnigmaSetting("RotorPosition");

        System.out.println("Result: ");

        for (int i = 0; i < 26; i++) {
            setting.setRotorPosition(0, i);
            for (int j = 0; j < 26; j++) {
                setting.setRotorPosition(1, j);
                for (int k = 0; k < 26; k++) {
                    setting.setRotorPosition(2, k);
                    message = enigmaMachine.start(encodedMessage, setting);

                    if (message.contains(searchWord)) {
                        System.out.println(message);
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

        String[] rotorTypes = {"I", "II", "III", "IV", "V"};

        EnigmaSetting setting = new EnigmaSetting("RotorType");

        System.out.println("This uses Basic Rotors");

        for (int i = 0; i < 5; i++) {
            setting.setRotorType(0, rotorTypes[i]);
            for (int j = 0; j < 5; j++) {
                setting.setRotorType(1, rotorTypes[j]);
                for (int k = 0; k < 5; k++) {
                    setting.setRotorType(2, rotorTypes[k]);

                    message = enigmaMachine.start(encodedMessage, setting);

                    if (message.contains(searchWord)) {
                        System.out.println(message);
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
        while (true) try {
            int input = in.nextInt();
            if (input >= 1 & input <= 3) {
                return input;
            } else throw new Exception();

        } catch (Exception e) {
            System.err.print("Error: Invalid input please choose a menu option. Input: ");
            in.next();
        }
    }
}
