import java.util.Scanner;

public class Bombe {

    private Scanner in = new Scanner(System.in);

    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private char[] alphabetArray = alphabet.toCharArray();

    public Bombe(String message) {
        String encodedMessage = "";
        String searchWord = "answer";
        EnigmaMachine enigmaMachine = new EnigmaMachine();

        displayMenu();

        int menuChoice;
        menuChoice = getMenuChoice();

        if (menuChoice == 1) {
            EnigmaSetting setting = new EnigmaSetting("Plugboard");

            while (!encodedMessage.contains(searchWord)) {
                //Loop through the alphabet each time!
                for (int i = 0; i < setting.getNoOfPlugs(); i++) {
                    setting.setUnknownPlugEnd(i,'a');
                }

                encodedMessage = enigmaMachine.start(message, setting);
            }



        } else if (menuChoice == 2) {

        }


    }

    private void displayMenu() {
        System.out.println();
        System.out.println("------------- BOMBE -------------");
        System.out.println();
        System.out.println("Which settings are unknown");
        System.out.println("1. Plugboard settings");
        System.out.println("2. Rotor starting positions");
        System.out.println("3. Rotor types");
        System.out.print("Input: ");
    }

    public void plugs() {


    }

    public void rotorPositions() {

    }

    public void rotorTypes() {

    }

    public int getMenuChoice() {
        while (true) try {
            int input = in.nextInt();
            if (input >= 1 & input <= 3) {
                return input;
            } else throw new Exception();

        } catch (Exception e) {
            System.err.print("Error: Invalid input please choose a menu option. Input: ");
            in.next();
        }
    }
}
