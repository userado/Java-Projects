import java.util.Random;

/**
 * This class generates a random password with a user interface.
 */
public class RandomPasswordGenerator {
    private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_-+=<>?";

    /**
     * Generates a random password with the specified length.
     *
     * @param length The length of the password to generate.
     * @return Returns a randomly generated password.
     */
    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder();

        // Create a string containing all possible characters
        String allCharacters = LOWERCASE_CHARACTERS + UPPERCASE_CHARACTERS + NUMBERS + SPECIAL_CHARACTERS;

        Random random = new Random();

        // Generate random characters from the allCharacters string
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(index));
        }

        return password.toString();
    }

    /**
     * Main method to run the program and generate a random password with a user interface.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        int passwordLength = 12; // Default password length

        // Check if a custom password length is provided as a command line argument
        if (args.length > 0) {
            try {
                passwordLength = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid password length. Using default length of 8.");
            }
        }

        // Generate the random password
        String password = generatePassword(passwordLength);

        // Display the generated password
        System.out.println("Generated Password: " + password);
    }
}
