package edu.kit.kastel.codefight.model.objects.aimap;

/**
 * Utility class for handling AI commands.
 *
 * @author uxtdn
 * @version 1.0
 */
public final class AICommandHandler {
    private static final String UTILITY_CLASS = "Utility class";
    private static final String COMMA = ",";
    private static final int THREE = 3;

    // Private constructor to prevent instantiation
    private AICommandHandler() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    /**
     * Splits the input string by commas.
     *
     * @param input The input string to split.
     * @return An array of strings after splitting the input string.
     */
    public static String[] splitInput(String input) {
        return input.split(COMMA);
    }
    /**
     * Parses an array of strings representing AI commands into an array of AICommandObject instances.
     *
     * @param commands The array of strings representing AI commands.
     * @return An array of AICommandObject instances parsed from the input array of commands.
     *
     */
    public static AICommandObject[] parseCommands(String[] commands) {
        AICommandObject[] aiObjects = new AICommandObject[commands.length / THREE];
        int index = 0;

        for (int i = 0; i < commands.length; i += THREE) {
            String command = commands[i];
            if (isNumber(commands[i + 1]) || isNumber(commands[i + 2])) {
                return null;
            }
            int argumentA = Integer.parseInt(commands[i + 1]);
            int argumentB = Integer.parseInt(commands[i + 2]);

            aiObjects[index] = new AICommandObject(command, argumentA, argumentB);
            index++;
        }
        return aiObjects;
    }

    private static boolean isNumber(String text) {
        try {
            Integer.parseInt(text);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
