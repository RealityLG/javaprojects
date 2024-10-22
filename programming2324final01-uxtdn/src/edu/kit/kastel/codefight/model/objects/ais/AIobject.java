package edu.kit.kastel.codefight.model.objects.ais;

/**
 * Represents an AI object in the game.
 * It stores information such as name, position, symbols, and running status of the AI.
 *
 * @author uxtdn
 * @version 1.0
 */
public class AIobject {
    private static final String I = "i";
    private int steps;
    private boolean running;
    private int position;
    private String standardSymbol;
    private String aibombSymbol;
    private String name;

    /**
     * Constructs an AI object with default values.
     */
    public AIobject() {
        this.name = "";
        this.position = 0;
        this.standardSymbol = I;
        this.aibombSymbol = I;
        this.running = false;
        this.steps = 0;
    }

    /**
     * Sets the attributes of the AI object.
     *
     * @param name           The name of the AI.
     * @param position       The position of the AI.
     * @param standardsymbol The standard symbol of the AI.
     * @param aibombsymbol   The AI bomb symbol of the AI.
     * @param running        The running status of the AI.
     */
    public void setAIobject(String name, int position, String standardsymbol, String aibombsymbol, boolean running) {
        this.name = name;
        this.position = position;
        this.standardSymbol = standardsymbol;
        this.aibombSymbol = aibombsymbol;
        this.running = running;
    }

    /**
     * Returns the standard symbol of the AI.
     *
     * @return The standard symbol of the AI.
     */
    public String getSymbol() {
        return standardSymbol;
    }

    /**
     * Returns the AI bomb symbol of the AI.
     *
     * @return The AI bomb symbol of the AI.
     */
    public String getAibombSymbol() {
        return aibombSymbol;
    }

    /**
     * Returns the position of the AI.
     *
     * @return The position of the AI.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Returns the running status of the AI.
     *
     * @return The running status of the AI.
     */
    public boolean getRunning() {
        return running;
    }

    /**
     * Increments the steps of the AI.
     */
    public void setSteps() {
        this.steps++;
    }

    /**
     * Returns the number of steps taken by the AI.
     *
     * @return The number of steps taken by the AI.
     */
    public int getSteps() {
        return steps;
    }

    /**
     * Returns the name of the AI.
     *
     * @return The name of the AI.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the AI.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the running status of the AI.
     *
     * @param status The running status to set.
     */
    public void setRunning(boolean status) {
        this.running = status;
    }

    /**
     * Sets the position of the AI.
     *
     * @param position The position to set.
     */
    public void setPosition(int position) {
        this.position = position;
    }
}
