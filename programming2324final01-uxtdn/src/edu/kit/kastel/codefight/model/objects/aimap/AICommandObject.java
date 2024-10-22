package edu.kit.kastel.codefight.model.objects.aimap;

/**
 * Represents an AI command object with a symbol, command, and two arguments.
 * This class provides methods to retrieve and modify the attributes of an AI command object.
 *
 * @author uxtdn
 * @version 1.0
 */
public class AICommandObject {

    private int length;
    private String command;
    private final int argumenta;
    private final int argumentb;

    /**
     * Constructs an AI command object with the specified command and arguments.
     *
     * @param command The command associated with the AI command object.
     * @param argumenta The first argument associated with the AI command object.
     * @param argumentb The second argument associated with the AI command object.
     */
    public AICommandObject(String command, int argumenta, int argumentb) {
        this.command = command;
        this.argumenta = argumenta;
        this.argumentb = argumentb;
    }

    /**
     * Retrieves the command associated with this AI command object.
     *
     * @return The command associated with the AI command object.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Retrieves the first argument associated with this AI command object.
     *
     * @return The first argument associated with the AI command object.
     */
    public int getArgumentA() {
        return argumenta;
    }

    /**
     * Retrieves the second argument associated with this AI command object.
     *
     * @return The second argument associated with the AI command object.
     */
    public int getArgumentB() {
        return argumentb;
    }

    /**
     * Sets the command associated with this AI command object.
     *
     * @param command The command to be set.
     */
    public void setCommand(String command) {
        this.command = command;
    }


    /**
     * The length of the AI command object.
     * @return length.
     */
    public int getLength() {
        return length;
    }

    /**
     * sets length.
     * @param length
     */
    public void setLength(int length) {
        this.length = length;
    }
}
