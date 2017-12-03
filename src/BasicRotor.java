/**
 * Extends the rotor class to represent a basic rotor.
 * It can be added to the three rotor slots in the Enigma Machine.
 */
class BasicRotor extends Rotor {

    /**
     * Creates a basic rotor and sets the mapping.
     * @param type The type of rotor which determines the mapping. (I - V)
     */
    BasicRotor(String type) {
        Integer[] I = {4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9};
        Integer[] II = {0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4};
        Integer[] III = {1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14};
        Integer[] IV = {4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1};
        Integer[] V = {21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10};

        super.initialise(type);     //Calls initialise to set the Rotor name

        //Set the mapping of the rotor determined by the rotor type
        switch (type) {
            case "I":
                mapping = I;
                break;
            case "II":
                mapping = II;
                break;
            case "III":
                mapping = III;
                break;
            case "IV":
                mapping = IV;
                break;
            case "V":
                mapping = V;
                break;
            default:
                System.err.println("Error: " + type + " is not a valid type of Basic Rotor");
        }
    }

    /**
     * Calls the map method to perform the substitution.
     * @param input An integer that represents the character being mapped. (0 - 25)
     * @return mapping result as an integer
     */
    @Override
    public Integer substitute(int input) {
        return map(mapping, input);
    }


    /**
     * Creates an inverse of the rotors mapping array.
     * Passes this to the map method to perform the inverse substitution.
     * @param input An integer that represents the character being mapped. (0 - 25)
     * @return mapping result as an integer
     */
    Integer substituteBack(int input) {
        Integer[] inverseMapping = new Integer[ROTORSIZE];
        int position;

        // Loop through the mapping array to create an inverse map
        for (int i = 0; i < ROTORSIZE; i++) {
            position = mapping[i];
            inverseMapping[position] = i;
        }

        return map(inverseMapping, input);
    }

    /**
     * Performs the mapping process from an input to a new output.
     * @param mapping The array used for mapping.
     * @param input An integer that represents the character being mapped. (0 - 25)
     * @return mapping result as an integer
     */
    private Integer map(Integer[] mapping, int input) {
        int currentPosition = getPosition();
        int newPosition;

        //If the current position is 0 use the mapping as is
        if (currentPosition == 0) {
            newPosition = mapping[input];

        /* If it isn't 0, take away the current position from the input before mapping.
         *
         * If the input minus the current position is less than 0 then calculate the difference
         * between the current position and the input and subtract that from the rotor size (26)
         * before performing the mapping. */
        } else if (input - currentPosition < 0) {
            int difference;
            difference = currentPosition - input;
            input = ROTORSIZE - difference;
            newPosition = mapping[input];

        /* If the input minus the current position is greater than 0 then perform the subtraction
           and then the mapping. */
        } else {
            input -= currentPosition;
            newPosition = mapping[input];
        }

        /* After performing the mapping add the current position to the result.
         * If this is larger than the rotor size - 1 then take away the rotor size from the result.
         * Otherwise just perform the addition. */
        if (newPosition + currentPosition > ROTORSIZE - 1) {
            return (newPosition + currentPosition) - ROTORSIZE;
        } else {
            return newPosition + currentPosition;
        }
    }

    /**
     * Rotates the rotor by one position.
     */
    public void rotate() {
        // Increment the rotor position position
        int newPosition = getPosition() + 1;
        // If it has reached the size of the rotor reset it to 0
        if (newPosition == ROTORSIZE) {
            setPosition(0);
        } else {
            setPosition(newPosition);
        }
    }
}
