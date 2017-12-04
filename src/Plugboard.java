import java.util.ArrayList;

/**
 * A board containing 26 slots for each letter of the alphabet. Plugs are connected to link letters together.
 * During encoding of a letter the input is run through the plugboard at the beginning and end.
 */
class Plugboard {

    /** An array list to store the plugs connected to the plugboard.
      * This has a max size of 13 as there are 26 slots. */
    private ArrayList<Plug> plugConnections = new ArrayList<>();

    /**
     * Instantiates a new plug and tries adds it to the plugboard.
     * @param end1 Start character of the plug.
     * @param end2 End character of the plug.
     * @return True if is successfully added to the plugboard, false otherwise.
     */
    Boolean addPlug(char end1, char end2) {

        //Instantiate a new plug
        Plug newPlug = new Plug(end1, end2);

        //Creates a boolean flag to check clashes
        Boolean clash = false;

        //Loop through all the plugs currently in the array list
        for (int i = 0; i < getNumPlugs(); i++) {

            //If the new plug clashes with one already in the array list set the clash flag to true
            if (plugConnections.get(i).clashesWith(newPlug)) {
                clash = true;
            }
        }

        // If there has been a clash remove the reference to the object and return false.
        if (clash) {
            // The plug be deleted in garbage collection
            newPlug = null;
            return false;

        // Otherwise add the plug to the array list and return true
        } else {
            plugConnections.add(newPlug);
            return true;
        }
    }

    /**
     * Substitutes depending on the plug connections.
     * If a plug is connected to that slot return the other end of the plug.
     * @param input Input character.
     * @return Character at the other end of the plug or the input character if no plug is connected.
     */
    char substitute(char input) {
        char destination = input;

        //Check each plug to see if any are connected to the input character using the encode method
        for (int i = 0; i < getNumPlugs(); i++) {
            destination = plugConnections.get(i).encode(input);
        }

        return destination;
    }

    /**
     * @return The number of plugs in the plugboard.
     */
    private Integer getNumPlugs() {
        return plugConnections.size();
    }

    /**
     * Removes all the plugs from the plugboard
     */
    void clear() {
        plugConnections.clear();
    }
}
