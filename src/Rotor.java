abstract public class Rotor {

    //Type of rotor
    private String name;

    //Current position of the rotor between 0 and 25
    private Integer position = 0;

    //Mapping of the rotor
    protected Integer[] mapping;

    /* Stores the maximum size of the rotor.
       Static means only one instance is created for the class and shared between objects.
       Final means the value cannot be changed after initialisation, this makes it constant. */
    protected static final Integer ROTORSIZE = 26;

    //Change the position of the rotor
    public void setPosition(int newPosition) {
        position = newPosition;
    }

    //Return the current position of the rotor
    public Integer getPosition() {
        return position;
    }

    //Overridden by child classes to setup the rotor
    public void initialise(String type) {
        name = type;
    }

    //Overridden by child classes to return the output of the rotor for a given input
    abstract public Integer substitute(int input);

}
