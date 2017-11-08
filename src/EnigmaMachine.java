public class EnigmaMachine {

    private Plugboard plugboard;
    private BasicRotor[] rotorSlot;
    private Reflector reflector;

    public EnigmaMachine() {
         plugboard = new Plugboard();
         rotorSlot = new BasicRotor[3];
    }

    public void start() {
        addPlug('b', 'c');
        addPlug('r', 'i');
        addPlug('s', 'm');
        addPlug('a', 'f');

        addRotor(new BasicRotor("IV"), 0);
        setPosition(0,23);

        addRotor(new BasicRotor("V"), 1);
        setPosition(1,4);

        addRotor(new BasicRotor("II"), 2);
        setPosition(2,9);

        reflector = new Reflector();
        addReflector(reflector, "ReflectorII");

        String message = "GACIG";
        String output = "";
        message = message.toLowerCase();
        char[] messageArray = message.toCharArray();

        for (char c : messageArray) {
            output += encodeLetter(c);
        }

        System.out.println(output);
    }

    public void addPlug(char start, char end) {
        plugboard.addPlug(start, end);
    }

    public void clearPlugboard() {
        plugboard.clear();
    }

    public void addRotor(BasicRotor newRotor, int slot) {
        rotorSlot[slot] = newRotor;
    }

    public Rotor getRotor(int slot) {
        return rotorSlot[slot];
    }

    public void addReflector(Reflector reflector, String type) {
        reflector.initialise(type);
    }

    public Reflector getReflector() {
        return reflector;
    }

    public void setPosition(int slot, int position) {
        rotorSlot[slot].setPosition(position);
    }

    public char encodeLetter(char letter) {
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

    public Integer charToInteger(char inputChar) {
        int ascii = (int)inputChar;
        return (ascii - 97);
    }

    public char integerToChar(int inputInt) {
        inputInt += 97;
        return (char)inputInt;
    }
}
