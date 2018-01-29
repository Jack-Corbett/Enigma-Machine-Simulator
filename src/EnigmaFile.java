import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Handles reading encoded messages, decoding them with the Enigma Machine and writing the message to another file.
 */
class EnigmaFile {

    /**
     * Constructor to call the methods to read InputMessage.txt, decode the it and write result to OutputMessage.txt.
     */
    EnigmaFile() {
        // Stores the input message
        String message;

        // Stores the output message
        String encodedMessage;

        System.out.println();
        System.out.println("Reading message from file...");

        message = readFromFile();   //Read the message to encode from InputMessage.txt

        //If a message is returned instantiate an enigma machine and run the message through it
        if (message != null) {
            EnigmaMachine enigmaMachine = new EnigmaMachine();
            EnigmaSetting settings = new EnigmaSetting();

            encodedMessage = enigmaMachine.start(message, settings);

            System.out.println("Writing output message to file...");
            System.out.println();

            //Write the encoded message to the OutputMessage.txt file
            writeToFile(encodedMessage);
        }
    }

    /**
     * Reads the OutputMessage.txt file.
     * @return A string containing the encoded message read from the file.
     */
    private String readFromFile() {
        // Instantiate a Buffered Reader and File Reader to set up an input stream from the file
        try (BufferedReader reader = new BufferedReader(new FileReader("InputMessage.txt"))) {

            // Instantiate a string builder to make it easier to stitch lines into one string
            StringBuilder builder = new StringBuilder();

            String line = reader.readLine();    // Read the first line of the file

            // Loop until the line is empty, appending the read string
            while (line != null) {
                line = line.toLowerCase();
                // Remove all characters that are not from the lower case alphabet
                line = line.replaceAll("[^a-z\\s]", "");
                // Remove all spaces
                line = line.replaceAll(" +","");
                builder.append(line);
                line = reader.readLine();
            }

            // Return the string that was read from the file
            return builder.toString();

        } catch (FileNotFoundException e) {
            System.err.println("Error: File could not be found.");
        } catch (Exception e) {
            System.err.println("Error: Could not read from file.");
        }

        // If an input stream fails to be set up return null
        return null;
    }

    /**
     * Writes to the OutputMessage.txt file.
     * @param message The decoded message to write.
     */
    private void writeToFile(String message) {
        // Instantiate a Print Writer to write to the file
        try (PrintWriter writer = new PrintWriter("OutputMessage.txt", "UTF-8")) {

            // Write the message on the first line and close the output stream
            writer.println(message);
            writer.close();
        } catch (Exception e) {
            System.err.println("Error: Could not write to file.");
        }
    }

}
