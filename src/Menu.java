import java.util.Scanner;

public class Menu {

    private Scanner in = new Scanner(System.in);

    public Menu() {
        int menuChoice;

        displayTopMenu();
        menuChoice = getInt();

        if (menuChoice == 1) {
            getSettings();
            EnigmaMachine enigmaMachine = new EnigmaMachine();
            enigmaMachine.start();
        } else {
            System.exit(0);
        }
    }

    public void displayTopMenu() {
        System.out.println("--------------------------------");
        System.out.println("    Enigma Machine Simulator    ");
        System.out.println("--------------------------------");
        System.out.println("          Jack Corbett          ");
        System.out.println();
        System.out.println("Encode:");
        System.out.println("    1. Input plain text and settings");
        System.out.println("    2. Read plain text and settings from file");
        System.out.println("Decode:");
        System.out.println("    3. Input cipher text and settings");
        System.out.println("    4. Run the Bombe to find unknown settings");
    }

    //MOVE TO OBJECT and get this method to return the settings object
    public void getSettings() {
        //Plugs
        int noOfPlugs;
        System.out.println();
        System.out.println("How many plugs do you want to add?");
        noOfPlugs = getInt();

        char[] plugStart = new char[noOfPlugs];
        char[] plugEnd = new char[noOfPlugs];

        for (int i = 0; i < noOfPlugs; i++) {
            System.out.print("Enter the start of plug " + (i + 1) + ": ");
            plugStart[i] = getChar();
            System.out.print("Enter the end of plug " + (i + 1) + ": " );
            plugEnd[i] = getChar();
            System.out.println();
        }

        //Rotors
        System.out.println("This is a test");

        //Reflector


    }



    public int getInt() {
        //Validate this!
        System.out.print("Input: ");
        return in.nextInt();
    }

    public char getChar() {
        String input = in.next();
        return input.charAt(0);
    }
}
