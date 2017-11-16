public class TurnoverRotor extends BasicRotor {

    private int turnoverPosition;

    private BasicRotor nextRotor;

    public TurnoverRotor(String type, BasicRotor rotor) {
        super(type);

        setNextRotor(rotor);

        setTurnoverPosition(type);
    }

    public TurnoverRotor(String type) {
        super(type);

        setTurnoverPosition(type);
    }

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

    public void setNextRotor(BasicRotor rotor) {
        nextRotor = rotor;
    }

    @Override
    public void rotate() {
        super.rotate();
        if (super.getPosition() == turnoverPosition & nextRotor != null) {
            nextRotor.rotate();
        }
    }
}
