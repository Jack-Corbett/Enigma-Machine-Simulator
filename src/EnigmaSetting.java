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
        inputSettings();
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

    private void inputSettings() {
        System.out.println();
        System.out.println("             SETTINGS");
        System.out.println();

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
            System.out.print("Enter the end of plug " + (i + 1) + ": " );
            plugEnd[i] = getChar();
            System.out.println();
        }

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
            rotorType[i] = in.next();

            System.out.print("Enter the starting position: ");
            startingPosition[i] = getInt();
            System.out.println();
        }


        //Reflector
        System.out.println("----------- Reflector ------------");
        System.out.println();
        System.out.println("Types: I, II");
        System.out.println();

        System.out.print("Enter the type of reflector: ");
        reflectorType = "Reflector" + in.next();
        System.out.println();
    }

    private int getInt() {
        while (true) {
            try {
                int input = in.nextInt();
                return input;
            } catch (Exception e) {
                System.err.print("Error: Invalid input, try again... Input: ");
                in.next();
            }
        }
    }

    private char getChar() {
        while (true) {
            try {
                String input = in.next().toLowerCase();
                char character = input.charAt(0);
                if (!Character.isDigit(character) & (int)character > 96 & (int)character < 123)  {
                    return character;
                } else throw new Exception();
            } catch (Exception e) {
                System.err.print("Error: Invalid input, try again... Input: ");
            }
        }
    }
}
