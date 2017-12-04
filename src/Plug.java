/**
 * A wire connecting two sockets representing letters on the plugboard.
 */
class Plug {

    /** The start character of the plug */
    private char end1;

    /** The end character of the plug */
    private char end2;

    /**
     * Constructor to create a plug connecting two sockets.
     * @param plugEnd1 Start of the plug.
     * @param plugEnd2 End of the plug.
     */
    Plug(char plugEnd1, char plugEnd2) {
        setEnd1(plugEnd1);
        setEnd2(plugEnd2);
    }

    /**
     * @return The first end of the plug.
     */
    private char getEnd1() {
        return end1;
    }

    /**
     * @return The second end of the plug.
     */
    private char getEnd2() {
        return end2;
    }

    /**
     * @param plugEnd1 the first end of the plug.
     */
    private void setEnd1(char plugEnd1) {
        end1 = plugEnd1;
    }

    /**
     * @param plugEnd2 the second end of the plug
     */
    private void setEnd2(char plugEnd2) {
        end2 = plugEnd2;
    }


    /* Returns letterIn if the plug is not connected to that character.
     If it is return the character at the other end of the plug. */

    /**
     * Checks if the plug is connected to a character. If it is return the character on the other end of the plug.
     * @param letterIn The input character.
     * @return letterIn if the plug is not connected or the other character connected to the plug.
     */
    char encode(char letterIn) {
        if (letterIn == getEnd1()) {
            return getEnd2();
        } else if (letterIn == getEnd2()) {
            return getEnd1();
        } else {
            return letterIn;
        }
    }

    /**
     * Checks if a new plug shares sockets with this plug object
     * @param plugin Plug object to test against.
     * @return True if either of the plug sockets are shared, false otherwise
     */
    Boolean clashesWith(Plug plugin) {
        // Get the character of the new plugs ends
        char newEnd1 = plugin.getEnd1();
        char newEnd2 = plugin.getEnd2();

        // Return true if they are the same as the end points of this plug, false otherwise.
        return newEnd1 == end1 || newEnd1 == end2 || newEnd2 == end1 || newEnd2 == end2;
    }
}