/**
 * A version of the Basic Rotor that causes the next rotor to rotate once it reaches it's turn over position.
 */
class TurnoverRotor extends BasicRotor {

    /** The point at which the next rotor rotates */
    private int turnoverPosition;

    /** Stores the rotor in the next slot */
    private BasicRotor nextRotor;

    /**
     * Constructor used for the first and second rotors as they have a rotor in the slot after them.
     * @param type The rotor type which dictates the mapping.
     * @param rotor The rotor in the next slot along in the Enigma Machine.
     */
    TurnoverRotor(String type, BasicRotor rotor) {
        // Calls the basic rotor constructor
        super(type);

        setNextRotor(rotor);

        // The type of rotor also determines the position
        setTurnoverPosition(type);
    }

    /**
     * Constructor for the third rotor as it is the last rotor so has no rotor after it.
     * @param type The rotor type which dictates the mapping.
     */
    TurnoverRotor(String type) {
        super(type);

        setTurnoverPosition(type);
    }

    /**
     * @param type Determines the turn over position at which the next rotor will rotate
     */
    private void setTurnoverPosition(String type) {
        switch (type) {
            case "I":
                turnoverPosition = 24;
                break;
            case "II":
                turnoverPosition = 12;
                break;
            case "III":
                turnoverPosition = 3;
                break;
            case "IV":
                turnoverPosition = 17;
                break;
            case "V":
                turnoverPosition = 7;
                break;
            default:
                System.err.println("Error: Failed to set turnover position of rotor type " + type);
        }
    }

    /**
     * @param rotor Sets the rotor that is in the next slot which will be rotated.
     */
    private void setNextRotor(BasicRotor rotor) {
        nextRotor = rotor;
    }

    /**
     * Rotate the rotor and if it falls on the turn over position rotate the next rotor.
     */
    @Override
    public void rotate() {
        super.rotate();
        if (super.getPosition() == turnoverPosition & nextRotor != null) {
            nextRotor.rotate();
        }
    }
}
