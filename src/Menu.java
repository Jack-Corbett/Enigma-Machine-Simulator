import java.util.Scanner;

public class Menu {

    private Scanner in = new Scanner(System.in);

    public Menu() {
        int menuChoice;

        while (true) {
            displayTopMenu();
            menuChoice = in.nextInt();

            if (menuChoice == 1) {
                String message;
                String encodedMessage;

                EnigmaSetting settings = new EnigmaSetting();

                EnigmaMachine enigmaMachine = new EnigmaMachine();

                message = inputMessage();
                System.out.println();

                encodedMessage = enigmaMachine.start(message, settings);

                System.out.println("The message is: " + encodedMessage);
                System.out.println();

            } else if (menuChoice == 2) {
                EnigmaFile enigmaFile = new EnigmaFile();

            } else {
                System.exit(0);
            }
        }
    }

    private void displayTopMenu() {
        System.out.println("--------------------------------");
        System.out.println("    Enigma Machine Simulator    ");
        System.out.println("--------------------------------");
        System.out.println("          Jack Corbett          ");
        System.out.println();
        System.out.println("Decode:");
        System.out.println("    1. Input encoded message");
        System.out.println("    2. Read encoded message from file");
        System.out.println("    3. Run the Bombe to find unknown settings");
        System.out.println("Encode:");
        System.out.println("    4. Input plain text and settings");
        System.out.print("Input: ");
    }

    private String inputMessage() {
        String message;
        System.out.print("Enter the message to decode: ");
        return in.next();
    }




}
