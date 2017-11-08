public class Plug {

    private char end1;
    private char end2;

    public Plug(char plugEnd1, char plugEnd2) {
        setEnd1(plugEnd1);
        setEnd2(plugEnd2);
    }

    //Returns the first end of the plug
    public char getEnd1() {
        return end1;
    }

    //Returns the second end of the plug
    public char getEnd2() {
        return end2;
    }

    //Sets the first end of the plug
    public void setEnd1(char plugEnd1) {
        end1 = plugEnd1;
    }

    //Sets the second end of the plug
    public void setEnd2(char plugEnd2) {
        end2 = plugEnd2;
    }

    /* Returns letterIn if the plug is not connected to that character.
     If it is return the character at the other end of the plug. */
    public char encode(char letterIn) {
        if (letterIn == end1) {
            return end2;
        } else if (letterIn == end2) {
            return end1;
        } else {
            return letterIn;
        }
    }

    //Returns true if either of the plug sockets are already taken, false if they are both available
    public Boolean clashesWith(Plug plugin) {
        //Get the character of the new plugs ends
        char newEnd1 = plugin.getEnd1();
        char newEnd2 = plugin.getEnd2();

        //Check if they are the same as the end points of this plug
        if (newEnd1 == end1 || newEnd1 == end2 || newEnd2 == end1 || newEnd2 == end2) {
            return true;
        } else {
            return false;
        }
    }
}
