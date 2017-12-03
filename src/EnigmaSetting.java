import java.util.Scanner;

/**
 * Stores the settings for the Enigma Machine including the plugboard, rotors and reflectors.
 */
class EnigmaSetting {

    /** Uses the scanner class to read from the command line */
    private Scanner in = new Scanner(System.in);


    /* PLUGS */
    /** The number of plugs to add to the plugboard. */
    private int noOfPlugs;

    /** An array containing the start of each plug (a-z). */
    private char[] plugStart;

    /** An array containing the end of each plug (a-z). */
    private char[] plugEnd;


    /* ROTORS */
    /** An array containing the kind of each rotor (1 = Basic or 2 = Turn Over). */
    private int[] rotorKind = new int[3];

    /** An array containing the type of each rotor (I - V). */
    private String[] rotorType = new String[3];

    /** An array containing the starting positions of each rotor. */
    private int[] startingPosition = new int[3];


    /* REFLECTOR */
    /** The type of reflector (I or II) */
    private String reflectorType;


    /**
     * Constructor which asks the user to input all the settings.
     */
    EnigmaSetting() {
        System.out.println();
        System.out.println("             SETTINGS");
        System.out.println();

        inputAllSettings();
    }

    /**
     * Constructor which asks only for the known settings (used by the Bombe).
     * @param unknown The setting which needs to be found to decode the message.
     */
    EnigmaSetting(String unknown) {
        System.out.println();
        System.out.println("             SETTINGS");
        System.out.println();

        switch (unknown) {
            case "Plugboard":
                setPlugboardSettings(true);     // True is passed as we don't know both ends of each plug
                setRotorKindSettings();
                setRotorTypeSettings();
                setRotorPositionSettings();
                setReflectorSettings();

                break;
            case "RotorPosition":
                setPlugboardSettings(false);    // False is passed as we know the start and end for each plug
                setRotorKindSettings();
                setRotorTypeSettings();
                setReflectorSettings();

                break;
            case "RotorType":
                setPlugboardSettings(false);    // False is passed as we know the start and end for each plug
                setRotorKindSettings();
                setRotorPositionSettings();
                setReflectorSettings();

                break;
        }
    }

    // GET methods
    /**
     * @return Number of plugs currently in the plugboard.
     */
    int getNoOfPlugs() {
        return noOfPlugs;
    }

    /**
     * @param position Location of the plug in the plug array.
     * @return Start character of that plug.
     */
    char getPlugStart(int position) {
        return plugStart[position];
    }

    /**
     * @param position Location of the plug in the plug array.
     * @return End character of that plug.
     */
    char getPlugEnd(int position) {
        return plugEnd[position];
    }

    /**
     * @param position Location of the rotor in the array.
     * @return It's kind: 1 = Basic Rotor, 2 = Turn Over Rotor.
     */
    int getRotorKind(int position) {
        return rotorKind[position];
    }

    /**
     * @param position Location of the rotor in the array.
     * @return It's type: I - V.
     */
    String getRotorType(int position) {
        return rotorType[position];
    }

    /**
     * @param position Location of the rotor in the array.
     * @return It's starting position: 0 - 25.
     */
    int getStartingPosition(int position) {
        return startingPosition[position];
    }

    /**
     * @return Type of the reflector: I or II.
     */
    String getReflectorType() {
        return reflectorType;
    }


    /**
     * Call all the settings methods for the user to input.
     */
    private void inputAllSettings() {
        // False is passed as we know the start and end for each plug
        setPlugboardSettings(false);
        setRotorKindSettings();
        setRotorTypeSettings();
        setRotorPositionSettings();
        setReflectorSettings();
    }

    // PLUGBOARD

    /**
     * Output to the command line and take input from the user to input the plugboard settings.
     * @param unknownPlugEnds True if the ends of the plugs are unknown, false otherwise.
     */
    private void setPlugboardSettings(Boolean unknownPlugEnds) {
        System.out.println("----------- Plugboard ------------");
        System.out.println();
        System.out.print("Enter the number of plugs you want to add: ");
        noOfPlugs = getInt();
        System.out.println();

        // Instantiate the plug start and end arrays to the size given by the user.
        plugStart = new char[noOfPlugs];
        plugEnd = new char[noOfPlugs];

        System.out.println("(Characters)");
        for (int i = 0; i < noOfPlugs; i++) {
            System.out.print("Enter the start of plug " + (i + 1) + ": ");
            plugStart[i] = getChar();

            // If the ends of the plugs are known, ask the user to input them.
            if (!unknownPlugEnds) {
                System.out.print("Enter the end of plug " + (i + 1) + ": " );
                plugEnd[i] = getChar();
            }
            System.out.println();
        }
    }

    /**
     * @param position The location of the plug in the array.
     * @param end The new character for the end of the plug.
     */
    void setUnknownPlugEnd(int position, char end) {
        plugEnd[position] = end;
    }

    // ROTORS

    /**
     * Output to the command line and take input from the user to set the rotor kind.
     */
    private void setRotorKindSettings() {

        System.out.println("---------- Rotor Kind ------------");

        for (int i = 0; i < 3; i++) {
            System.out.println("Slot " + (i + 1));
            System.out.println("1. Basic Rotor");
            System.out.println("2. Turnover Rotor");
            System.out.print("Input: ");
            rotorKind[i] = getInt();

            System.out.println();
        }
    }

    /**
     * Output to the command line and take input from the user for the rotor type settings.
     */
    private void setRotorTypeSettings() {
        //Rotors
        System.out.println("---------- Rotor Type ------------");
        System.out.println();
        System.out.println("Types: I, II, III, IV, V");
        System.out.println();

        for (int i = 0; i < 3; i++) {
            System.out.println("Slot " + (i + 1));

            System.out.print("Input the rotor type: ");
            rotorType[i] = getString(0);

            System.out.println();
        }
    }

    /**
     * @param slot The location of the rotor in the array.
     * @param type The type of rotor I-V.
     */
    void setRotorType(int slot, String type) {
        rotorType[slot] = type;
    }

    /**
     * Output to the command line and take input from the user for each rotors starting position.
     */
    private void setRotorPositionSettings() {

        System.out.println("-------- Rotor Positions ---------");
        System.out.println();
        System.out.println("Enter the starting positions of:");

        for (int i = 0; i < 3; i++) {
            System.out.print("Rotor " + (i+1) + ": ");
            startingPosition[i] = getInt();
        }
    }

    /**
     * @param slot The location of the rotor in the array.
     * @param position The position the rotor is initially set to: 0-25.
     */
    void setRotorPosition(int slot, int position) {
        startingPosition[slot] = position;
    }

    // REFLECTOR

    /**
     *
     */
    private void setReflectorSettings() {
        //Reflector
        System.out.println("----------- Reflector ------------");
        System.out.println();
        System.out.println("Types: I, II");
        System.out.println();

        System.out.print("Enter the type of reflector: ");
        reflectorType = "Reflector" + getString(1);
        System.out.println();
    }


    // VALIDATION

    /**
     * @return An integer inputted by the user.
     */
    private int getInt() {
        // Loop until an integer is inputted
        while (true) try {
            return in.nextInt();
        } catch (Exception e) {
            System.err.print("Error: Invalid input expecting integer, try again. Input: ");
            in.next();
        }
    }

    /**
     * @return A character inputted by the user from the lower case alphabet (a-z).
     */
    private char getChar() {
        // Loop until a character (a-z) is inputted
        while (true) try {
            String input = in.next().toLowerCase();

            // If it is a string longer than one character throw an exception
            if (input.length() > 1) throw new Exception();

            // Take the first character from the string
            char character = input.charAt(0);

            // If it isn't a digit and it's ASCII code is between 95 (a) and 122 (z) return the character
            if (!Character.isDigit(character) & (int) character > 96 & (int) character < 123) {
                return character;
            } else throw new Exception();

        } catch (Exception e) {
            System.err.print("Error: Invalid input expecting character (a-z), try again. Input: ");
        }
    }

    /**
     * @param type Dictates the choices. 0 = Rotor selection (I-V) 1 = Reflector selection ()
     * @return A string abiding by the rules set out above.
     */
    private String getString(int type) {
        // Loop until a valid string has been entered
        while (true) try {
            String input = in.next();

            // If taking input for a rotor
            if (type == 0) {
                if (input.equals("I") || input.equals("II") || input.equals("III") || input.equals("IV") || input.equals("V")) {
                    return input;
                } else throw new Exception();

            // If taking input for a reflector
            } else if (type == 1) {
                if (input.equals("I") || input.equals("II")) {
                    return input;
                } else throw new Exception();
            }
        } catch (Exception e) {
            System.err.print("Error: Invalid input choose type from the list above. Input: ");
        }
    }
}