/**
 * Parent class for all rotors used to map characters represented by integers (0-25) in the Enigma Machine.
 */
abstract class Rotor {

    /** Type of rotor. */
    private String name;

    /** Current position of the rotor between 0 and 25. */
    private Integer position = 0;

    /** Mapping of the rotor represented as an array. */
    Integer[] mapping;

    /** Stores the maximum size of the rotor.
     * Static means only one instance is created for the class and shared between objects.
     * Final means the value cannot be changed after initialisation, this makes it constant. */
    static final Integer ROTORSIZE = 26;

    /**
     * @param newPosition Sets the rotor position.
     */
    void setPosition(int newPosition) {
        position = newPosition;
    }

    /**
     * @return The current position of the rotor.
     */
    Integer getPosition() {
        return position;
    }

    /**
     * @param type Sets the name of the rotor to it's type.
     */
    void initialise(String type) {
        name = type;
    }

    /**
     * Overridden by child classes to return the output of the rotor for a given input
     * @param input Input to the rotor
     * @return The result of the mapping.
     */
    abstract Integer substitute(int input);

}
