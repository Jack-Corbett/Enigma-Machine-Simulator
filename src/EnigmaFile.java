import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 *
 */
class EnigmaFile {

    /**
     *
     */
    EnigmaFile() {
        String message;
        String encodedMessage = null;

        System.out.println();
        System.out.println("Reading encoded message from file...");

        message = readFromFile();   //Read the message to encode

        //If a message is returned instantiate an enigma machine and run the message through it
        if (message != null) {
            EnigmaMachine enigmaMachine = new EnigmaMachine();
            EnigmaSetting settings = new EnigmaSetting();

            encodedMessage = enigmaMachine.start(message, settings);

            System.out.println("Writing decoded message to file...");
            System.out.println();

            //Write the encoded message to the file
            writeToFile(encodedMessage);
        }
    }

    /**
     *
     * @return
     */
    private String readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("EncodedMessage.txt"))) {
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                builder.append(line);
                line = reader.readLine();
            }

            return builder.toString();

        } catch (Exception e) {
            System.err.println("Error: Could not read from file");
        }
        return null;
    }

    /**
     *
     * @param message
     */
    private void writeToFile(String message) {
        try (PrintWriter writer = new PrintWriter("Message.txt", "UTF-8")) {
            writer.println(message);
            writer.close();
        } catch (Exception e) {
            System.err.println("Error: Could not write to file");
        }
    }

}
