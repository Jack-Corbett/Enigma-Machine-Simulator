import java.util.Scanner;

public class EnigmaSetting {

    private Scanner in = new Scanner(System.in);

    //Plugs
    private int noOfPlugs;
    private char[] plugStart;
    private char[] plugEnd;

    //Rotors
    private int[] rotorKind = new int[3];
    private String[] rotorType = new String[3];
    private int[] startingPosition = new int[3];

    //Reflector
    private String reflectorType;

    public EnigmaSetting() {
        inputAllSettings();
    }

    public EnigmaSetting(String unknown) {
        System.out.println();
        System.out.println("             SETTINGS");
        System.out.println();

        if (unknown.equals("Plugboard")) {
            setPlugboardSettings(true);
            setRotorTypeSettings();
            setRotorPositionSettings();
            setReflectorSettings();

        } else if (unknown.equals("RotorType")) {
            setPlugboardSettings(false);
            setRotorPositionSettings();
            setReflectorSettings();

        } else if (unknown.equals("RotorPosition")) {
            setPlugboardSettings(false);
            setRotorTypeSettings();
            setReflectorSettings();
        }
    }

    public int getNoOfPlugs() {
        return noOfPlugs;
    }

    public char getPlugStart(int position) {
        return plugStart[position];
    }

    public char getPlugEnd(int position) {
        return plugEnd[position];
    }

    public int getRotorKind(int position) {
        return rotorKind[position];
    }

    public String getRotorType(int position) {
        return rotorType[position];
    }

    public int getStartingPosition(int position) {
        return startingPosition[position];
    }

    public String getReflectorType() {
        return reflectorType;
    }

    public void inputAllSettings() {
        setPlugboardSettings(false);

        setRotorTypeSettings();

        setRotorPositionSettings();

        setReflectorSettings();
    }

    private void setPlugboardSettings(Boolean unknown) {
        //Plugs
        System.out.println("----------- Plugboard ------------");
        System.out.println();
        System.out.print("Enter the number of plugs you want to add: ");
        noOfPlugs = getInt();
        System.out.println();

        plugStart = new char[noOfPlugs];
        plugEnd = new char[noOfPlugs];

        System.out.println("(Characters)");
        for (int i = 0; i < noOfPlugs; i++) {
            System.out.print("Enter the start of plug " + (i + 1) + ": ");
            plugStart[i] = getChar();
            if (!unknown) {
                System.out.print("Enter the end of plug " + (i + 1) + ": " );
                plugEnd[i] = getChar();
            }
            System.out.println();
        }
    }

    public void setUnknownPlugEnd(int position, char end) {
        plugEnd[position] = end;

    }

    private void setRotorTypeSettings() {
        //Rotors
        System.out.println("------------ Rotors --------------");
        System.out.println();
        System.out.println("Types: I, II, III, IV, V");
        System.out.println();

        for (int i = 0; i < 3; i++) {
            System.out.println("Slot " + (i + 1));
            System.out.println("1. Basic Rotor");
            System.out.println("2. Turnover Rotor");
            System.out.print("Input: ");
            rotorKind[i] = getInt();

            System.out.print("Input the rotor type: ");
            rotorType[i] = getString(0);

            System.out.println();
        }
    }

    private void setRotorPositionSettings() {

        System.out.println("-------- Rotor Positions ---------");
        System.out.println();
        System.out.println("Enter the starting positions of:");

        for (int i = 0; i < 3; i++) {
            System.out.print("Rotor " + (i+1) + ": ");
            startingPosition[i] = getInt();
        }
    }

    private void setReflectorSettings() {
        //Reflector
        System.out.println("----------- Reflector ------------");
        System.out.println();
        System.out.println("Types: I, II");
        System.out.println();

        System.out.print("Enter the type of reflector: ");
        reflectorType = "Reflector" + getString(1);
        System.out.println();
    }






    private int getInt() {
        while (true) try {
            int input = in.nextInt();
            return input;
        } catch (Exception e) {
            System.err.print("Error: Invalid input expecting integer, try again. Input: ");
            in.next();
        }
    }

    private char getChar() {
        while (true) try {
            String input = in.next().toLowerCase();

            if (input.length() > 1) throw new Exception();

            char character = input.charAt(0);

            if (!Character.isDigit(character) & (int) character > 96 & (int) character < 123) {
                return character;
            } else throw new Exception();

        } catch (Exception e) {
            System.err.print("Error: Invalid input expecting character, try again. Input: ");
        }
    }

    private String getString(int type) {
        while (true) try {
            String input = in.next();

            if (type == 0) {
                if (input.equals("I") || input.equals("II") || input.equals("III") || input.equals("IV") || input.equals("V")) {
                    return input;
                } else throw new Exception();
            } else if (type == 1) {
                if (input.equals("I") || input.equals("II")) {
                    return input;
                } else throw new Exception();
            }
        } catch (Exception e) {
            System.err.print("Error: Invalid input choose type from the list above. Input: ");
        }
    }
}
