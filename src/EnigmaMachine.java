public class EnigmaMachine {

    private Plugboard plugboard;
    private BasicRotor[] rotorSlot;
    private Reflector reflector;

    //Pass a settings object to apply it
    public EnigmaMachine() {
         plugboard = new Plugboard();
         rotorSlot = new BasicRotor[3];
    }

    public String start(String message, EnigmaSetting settings) {
        clearSettings();

        try {
            //Plugs
            for (int i = 0; i < settings.getNoOfPlugs(); i++) {
                addPlug(settings.getPlugStart(i), settings.getPlugEnd(i));
            }


            //Rotors
            for (int i = 2; i > -1; i--) {
                int rotorKind = settings.getRotorKind(i);
                if (rotorKind == 1) {

                    addRotor(new BasicRotor(settings.getRotorType(i)), i);

                } else if (rotorKind == 2) {

                    if (i == 2) {
                        addRotor(new TurnoverRotor(settings.getRotorType(i)), i);
                    } else {
                        addRotor(new TurnoverRotor(settings.getRotorType(i), rotorSlot[i+1]), i);
                    }
                }

                setPosition(i, settings.getStartingPosition(i));
            }

            //Reflector
            reflector = new Reflector();
            addReflector(reflector, settings.getReflectorType());
        } catch (Exception e) {
            System.err.println("The settings could not be applied");
        }

        String output = "";
        message = message.toLowerCase();
        char[] messageArray = message.toCharArray();

        for (char c : messageArray) {
            output += encodeLetter(c);
        }

        return output;
    }

    private void clearSettings() {
        clearPlugboard();
        reflector = null;
        for (Rotor rotor : rotorSlot) {
            rotor = null;
        }
    }

    private void addPlug(char start, char end) {
        plugboard.addPlug(start, end);
    }

    public void clearPlugboard() {
        plugboard.clear();
    }

    private void addRotor(BasicRotor newRotor, int slot) {
        rotorSlot[slot] = newRotor;
    }

    public Rotor getRotor(int slot) {
        return rotorSlot[slot];
    }

    private void addReflector(Reflector reflector, String type) {
        reflector.initialise(type);
    }

    public Reflector getReflector() {
        return reflector;
    }

    private void setPosition(int slot, int position) {
        rotorSlot[slot].setPosition(position);
    }

    private char encodeLetter(char letter) {
        letter = plugboard.substitute(letter);

        int sub = charToInteger(letter);

        for (int i = 0; i <= 2; i++) {
            sub = rotorSlot[i].substitute(sub);
        }

        sub = reflector.substitute(sub);

        for (int i = 2; i >= 0; i--) {
            sub = rotorSlot[i].substituteBack(sub);
        }

        letter = integerToChar(sub);
        letter = plugboard.substitute(letter);

        rotorSlot[0].rotate();
        return letter;
    }

    private Integer charToInteger(char inputChar) {
        int ascii = (int)inputChar;
        return (ascii - 97);
    }

    private char integerToChar(int inputInt) {
        inputInt += 97;
        return (char)inputInt;
    }
}
