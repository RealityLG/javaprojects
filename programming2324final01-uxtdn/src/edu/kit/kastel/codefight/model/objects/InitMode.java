package edu.kit.kastel.codefight.model.objects;
/**
 * Represents the initialization mode for the game.
 * The initialization mode determines how the playfieldcells are initialized in the Start PhaseHandler.
 * It includes information about the initialization mode and a number associated with it.
 *
 * @author uxtdn
 * @version 1.0
 */
public class InitMode {

    /**
     * STRING for init mode stop.
     */
    public static final String STRING = "INIT_MODE_STOP";
    private String init;
    private int num;

    /**
     * Constructs an InitMode object with default values.
     * The default initialization mode is "INIT_MODE_STOP", and the number is initialized to 0.
     */
    public InitMode() {
        this.init = STRING;
        this.num = 0;
    }

    /**
     * Sets the initialization mode and associated number.
     *
     * @param mode The initialization mode to set.
     * @param num  The number associated with the initialization mode.
     */
    public void setInitMode(String mode, int num) {
        this.init = mode;
        this.num = num;
    }

    /**
     * Retrieves the associated number.
     *
     * @return The number associated with the initialization mode.
     */
    public long getSeed() {
        return num;
    }

    /**
     * Retrieves the initialization mode.
     *
     * @return The initialization mode.
     */
    public String getInit() {
        return init;
    }
}
