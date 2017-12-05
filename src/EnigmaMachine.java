/**
 * Represents the Enigma Machine which will be used in simulations to decode and encode messages.
 */
class EnigmaMachine {

    /** Plugboard containing the plugs connecting letters which is used at the start and end of encoding. */
    private Plugboard plugboard;

    /** Slots to hold the rotors used in mapping. */
    private BasicRotor[] rotorSlot;

    /** Reflector which maps the character, sending it back through the rotors. */
    private Reflector reflector;

    /**
     * Constructor instantiates the plugboard and the rotor slots setting the number of slots to 3.
     */
    EnigmaMachine() {
         plugboard = new Plugboard();
         rotorSlot = new BasicRotor[3];
    }

    /**
     * Runs the simulation on this Enigma Machine to encode a message.
     * @param message Message to encode.
     * @param settings An object containing all the Enigma Machine settings such as rotor types and plugs.
     * @return Encoded message as a string.
     */
    String start(String message, EnigmaSetting settings) {
        /* Remove the current settings including: plugs, rotors and the reflector to ensure there are no conflicts,
           as this method can be called many times on the same object. */
        clearSettings();

        try {
            /* PLUGS
               Fetch the plugs from the settings object and add them to the plugboard. */
            for (int i = 0; i < settings.getNoOfPlugs(); i++) {
                addPlug(settings.getPlugStart(i), settings.getPlugEnd(i));
            }


            /* ROTORS
               Fetch the rotors from the settings object and add them to the rotor slots.
               Count down from 2 to 0 adding the rotors.
               This makes it easy to pass the next rotor for turnover rotors. */
            for (int i = 2; i > -1; i--) {
                int rotorKind = settings.getRotorKind(i);

                // If the rotor is a basic rotor instantiate it and add it to the correct slot
                if (rotorKind == 1) {

                    addRotor(new BasicRotor(settings.getRotorType(i)), i);

                // If the rotor is a turnover rotor
                } else if (rotorKind == 2) {

                    // If the rotor is for the end slot add it normally.
                    if (i == 2) {
                        addRotor(new TurnoverRotor(settings.getRotorType(i)), i);

                    // If not pass the rotor in the next slot to the turnover rotor constructor
                    } else {
                        addRotor(new TurnoverRotor(settings.getRotorType(i), getRotor(i+1)), i);
                    }
                }

                // For each rotor set it's starting position by fetching it from the settings object
                setPosition(i, settings.getStartingPosition(i));
            }

            /* REFLECTOR
               Instantiate a new reflector and fetch it's type to dictate it's mapping. */
            addReflector(settings.getReflectorType());

        } catch (Exception e) {
            System.err.println("Error: The settings could not be applied.");
        }

        // Convert the message to encode to lower case
        message = message.toLowerCase();

        // Split the message into an array of characters
        char[] messageArray = message.toCharArray();

        // Instantiate a string builder to stitch the message back together after encoding
        StringBuilder builder = new StringBuilder();

        // For each character encode it and add it to the output string
        for (char c : messageArray) {
            builder.append(encodeLetter(c));
        }

        return builder.toString();
    }

    /**
     * Removes all current settings from this Enigma Machine.
     */
    private void clearSettings() {
        clearPlugboard();
        reflector = null;
        for (int i = 0; i < 3; i++) {
            rotorSlot[i] = null;
        }
    }

    /**
     * Removes all plugs from the plugboard.
     */
    private void clearPlugboard() {
        plugboard.clear();
    }

    /**
     * Adds a plug by calling the method on the plugboard.
     * If it clashes with an existing plug and cannot be added output error.
     * @param start Start point of the plug
     * @param end End point of the plug
     */
    private void addPlug(char start, char end) {
        Boolean plugAdded;
        plugAdded = plugboard.addPlug(start, end);

        if (!plugAdded) {
            System.err.println("Error: The plug could not be added to the plugboard.");
        }
    }

    /**
     * Adds a rotor to a slot.
     * @param newRotor Rotor object to add to the machine.
     * @param slot Slot number to add the rotor to.
     */
    private void addRotor(BasicRotor newRotor, int slot) {
        rotorSlot[slot] = newRotor;
    }

    /**
     * Fetch a rotor.
     * @param slot The slot which contains the rotor.
     * @return The rotor object.
     */
    private BasicRotor getRotor(int slot) {
        return rotorSlot[slot];
    }

    /**
     * Adds a reflector to the machine by calling the initialise method.
     * @param type The type which determines the mapping.
     */
    private void addReflector(String type) {
        reflector = new Reflector(type);
    }

    /**
     * Fetch the reflector.
     * @return The reflector object.
     */
    private Reflector getReflector() {
        return reflector;
    }

    /**
     * Sets the position of a rotor.
     * @param slot The slot the rotor is in.
     * @param position The new position of the rotor.
     */
    private void setPosition(int slot, int position) {
        rotorSlot[slot].setPosition(position);
    }

    /**
     * Maps a character through the plugboard, rotors, reflector and then back again.
     * @param letter The letter to encode.
     * @return The encoded letter.
     */
    private char encodeLetter(char letter) {

        letter = plugboard.substitute(letter);     // Substitute the letter through the plugboard

        int sub = charToInteger(letter);           // Convert the letter to an integer for the rotors and reflector

        // Substitute through each rotor counting UP
        for (int i = 0; i <= 2; i++) {
            sub = getRotor(i).substitute(sub);
        }

        // Perform the reflector mapping
        sub = getReflector().substitute(sub);

        // Substitute back through each rotor this time counting DOWN
        for (int i = 2; i >= 0; i--) {
            sub = getRotor(i).substituteBack(sub);
        }

        letter = integerToChar(sub);                // Convert back to a character

        letter = plugboard.substitute(letter);      // Substitute the letter through the plugboard

        // Rotate the first rotor and return the encoded letter
        rotorSlot[0].rotate();
        return letter;
    }

    /**
     * Converts a character to an integer.
     * @param inputChar Character to convert.
     * @return Integer representing that character (0 - 25)
     */
    private Integer charToInteger(char inputChar) {
        int ascii = (int)inputChar;
        return (ascii - 97);
    }

    /**
     * Converts an integer to a character.
     * @param inputInt Integer to convert.
     * @return Character represented by that integer (a - z)
     */
    private char integerToChar(int inputInt) {
        inputInt += 97;         // Add 97 as this maps 0 = a to the ASCII codes (97 = a)
        return (char)inputInt;
    }
}
