import java.util.ArrayList;

public class Plugboard {

    private ArrayList<Plug> plugConnections = new ArrayList<>();

    //Instantiates a new plug and returns true if successful, removes the plug and returns false if it's inputs conflicted with others
    public Boolean addPlug(char end1, char end2) {
        //Instantiate a new plug
        Plug newPlug = new Plug(end1, end2);
        //Creates a boolean flag to check clashes
        Boolean clash = false;

        //Loop through all the plugs currently in the array list
        for (Plug plug : plugConnections) {
            //If the new plug clashes with one already in the array list set the clash flag to true
            if (plug.clashesWith(newPlug)) {
                clash = true;
            }
        }

        //If there has been a clash remove the reference to the object (it will be deleted in garbage collection) and return false
        if (clash) {
            newPlug = null;
            return false;
        } else {
            //Otherwise add the plug to the array list and return true
            plugConnections.add(newPlug);
            return true;
        }
    }

    //Returns the size of the array list
    public Integer getNumPlugs() {
        return plugConnections.size();
    }

    //Clears the array list - removing all plugs from the plugboard
    public void clear() {
        plugConnections.clear();
    }

    //Returns the encoded character from the plugboard
    public char substitute(char input) {
        //Sets the default destination of the character to the inputted character
        char destination = input;

        //Check each plug to see if any are connected to the input character
        for (Plug plug : plugConnections) {
            //If they are then set the destination to the other end of that plug
            if (plug.getEnd1() == input) {
                destination = plug.getEnd2();
            } else if (plug.getEnd2() == input) {
                destination = plug.getEnd1();
            }
        }
        return destination;
    }
}
