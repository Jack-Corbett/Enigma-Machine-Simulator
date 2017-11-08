public class BasicRotor extends Rotor {

    private Integer[] I = {4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9};
    private Integer[] II = {0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4};
    private Integer[] III = {1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14};
    private Integer[] IV = {4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1};
    private Integer[] V = {21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10};

    public BasicRotor(String type) {
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
                System.out.println("Error: " + type + " is not a valid type of Basic Rotor");
        }
    }

    @Override
    public Integer substitute(int input) {
        return map(mapping, input);
    }

    public Integer substituteBack(int input) {
        Integer[] inverseMapping = new Integer[26];
        int position;

        for (int i = 0; i < 26; i++) {
            position = mapping[i];
            inverseMapping[position] = i;
        }

        return map(inverseMapping, input);
    }

    public Integer map(Integer[] mapping, int input) {
        int currentPosition = getPosition();
        int newPosition;

        if (currentPosition == 0) {
            newPosition = mapping[input];
        } else if (input - currentPosition < 0) {
            int difference;
            difference = currentPosition - input;
            input = 26 - difference;
            newPosition = mapping[input];
        } else {
            input -= currentPosition;
            newPosition = mapping[input];
        }

        if (newPosition + currentPosition > 25) {
            return (newPosition + currentPosition) - 26;
        } else {
            return newPosition + currentPosition;
        }
    }

    public void rotate() {
        int newPosition = getPosition() + 1;
        if (newPosition == ROTORSIZE) {
            setPosition(0);
        } else {
            setPosition(newPosition);
        }
    }
}
