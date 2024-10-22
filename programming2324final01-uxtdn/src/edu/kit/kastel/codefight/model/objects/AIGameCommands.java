package edu.kit.kastel.codefight.model.objects;
/**
 * Represents the available game commands for AI players.
 * This class provides a list of predefined game commands that AI players can execute.
 *
 * @author uxtdn
 * @version 1.0
 */
public class AIGameCommands {

    /**
     * String for ADD_R.
     */
    public static final String ADD_R = "ADD_R";

    /**
     * String for MOV_I.
     */
    public static final String MOV_I = "MOV_I";

    /**
     * String for MOV_R.
     */
    public static final String MOV_R = "MOV_R";

    /**
     * String for STOP.
     */
    public static final String STOP = "STOP";

    /**
     * String for ADD.
     */
    public static final String ADD = "ADD";

    /**
     * String for JMP.
     */
    public static final String JMP = "JMP";

    /**
     * String for JMZ.
     */
    public static final String JMZ = "JMZ";

    /**
     * String for CMP.
     */
    public static final String CMP = "CMP";

    /**
     * String for SWAP.
     */
    public static final String SWAP = "SWAP";

    /**
     * Retrieves an array of available game commands for AI players.
     *
     * @return An array of strings representing the available game commands.
     */
    public String[] getAIGameCommands() {
        return new String[]{STOP, MOV_R, MOV_I, ADD, ADD_R, JMP, JMZ, CMP, SWAP};
    }
}
