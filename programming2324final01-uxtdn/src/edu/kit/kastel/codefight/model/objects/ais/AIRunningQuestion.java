package edu.kit.kastel.codefight.model.objects.ais;

/**
 * Represents a utility class for displaying information about an AI.
 *
 * @author uxtdn
 * @version 1.0
 */
public class AIRunningQuestion {
    private static final String RUNNING = "RUNNING";
    private static final String STOPPED = "STOPPED";
    private static final String STR = " (";
    private static final String STR1 = "@";
    private static final String STR2 = ")";
    private static final String STR3 = "\nNext Command: ";
    private static final String STR4 = "|";
    private static final String SPACE = " ";

    /**
     * Generates argumenta formatted string representing information about an AI.
     *
     * @param name The name of the AI.
     * @param isRunning A boolean indicating whether the AI is currently running.
     * @param counter The counter indicating the current state of the AI.
     * @param nextCommand The next command to be executed by the AI.
     * @param argumenta The first argument associated with the next command.
     * @param argumentb The second argument associated with the next command.
     * @param memoryCell The memory cell associated with the next command.
     * @return A formatted string containing information about the AI.
     */
    public String showAI(String name, boolean isRunning, int counter, String nextCommand, int argumenta, int argumentb, int memoryCell) {
        StringBuilder sb = new StringBuilder();

        sb.append(name).append(STR).append(isRunning ? RUNNING : STOPPED).append(STR1).append(counter).append(STR2);

        if (isRunning) {
            sb.append(STR3)
                    .append(nextCommand)
                    .append(STR4)
                    .append(argumenta)
                    .append(STR4)
                    .append(argumentb)
                    .append(SPACE)
                    .append(STR1)
                    .append(memoryCell);
        }
        return sb.toString();
    }
}
