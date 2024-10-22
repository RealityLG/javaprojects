package edu.kit.kastel.codefight.model.objects.playfieldobject;

/**
 * Represents a playfield cell containing an AI symbol, AI command, two arguments and an last Symbol.
 * This class provides methods to retrieve and modify the attributes of a playfield object.
 *
 * @author uxtdn
 * @version 1.0
 */
public class Playfieldobject {
    private String lastSymbol;
    private String aISymbol;
    private int argumentA;
    private String aICommand;
    private int argumentB;


    /**
     * Constructs argumenta playfield object with the specified symbol, command, and arguments.
     *
     * @param aisymbol The symbol associated with the playfield object.
     * @param aIC The command associated with the playfield object.
     * @param argumenta The first argument associated with the playfield object.
     * @param argumentb The second argument associated with the playfield object.
     */
    public Playfieldobject(String aisymbol, String aIC, int argumenta, int argumentb) {
        this.aISymbol = aisymbol;
        this.aICommand = aIC;
        this.argumentA = argumenta;
        this.argumentB = argumentb;
        this.lastSymbol = null;
    }


    /**
     * Retrieves the AI symbol associated with this playfield object.
     *
     * @return The AI symbol associated with the playfield object.
     */
    public String getAISymbol() {
        return aISymbol;
    }

    /**
     * Retrieves the AI command associated with this playfield object.
     *
     * @return The AI command associated with the playfield object.
     */
    public String getAICommand() {
        return aICommand;
    }

    /**
     * Retrieves the first argument associated with this playfield object.
     *
     * @return The first argument associated with the playfield object.
     */
    public  int getArgumentA() {
        return argumentA;
    }

    /**
     * Retrieves the second argument associated with this playfield object.
     *
     * @return The second argument associated with the playfield object.
     */
    public int getArgumentB() {
        return argumentB;
    }

    /**
     * Sets the symbol, command, and arguments associated with this playfield object.
     *
     * @param symbol    The symbol to be set.
     * @param command   The command to be set.
     * @param argumenta The first argument to be set.
     * @param argumentb The second argument to be set.
     */
    public void setPlayfieldobject(String symbol, String command, int argumenta, int argumentb) {
        this.aISymbol = symbol;
        this.aICommand = command;
        this.argumentA = argumenta;
        this.argumentB = argumentb;
    }


    /**
     * Sets the symbol associated with this playfield object.
     *
     * @param symbol The symbol to be set.
     */
    public void setSymbol(String symbol) {
        this.aISymbol = symbol;
    }

    /**
     * Retrieves the symbol associated with this playfield object.
     *
     * @return The symbol associated with the playfield object.
     */
    public String getPlayfieldobject() {
        return aISymbol;
    }

    /**
     * Sets the first argument associated with this playfield object.
     *
     * @param argumentA The first argument to be set.
     */
    public void setArgumentA(int argumentA) {
        this.argumentA = argumentA;
    }

    /**
     * Sets the second argument associated with this playfield object.
     *
     * @param argumentB The second argument to be set.
     */
    public void setArgumentB(int argumentB) {
        this.argumentB = argumentB;
    }

    /**
     * Sets the AI command associated with this playfield object.
     *
     * @param aiCommand The AI command to be set.
     */
    public void setAICommand(String aiCommand) {
        this.aICommand = aiCommand;
    }

    /**
     * Sets last Symbol, for show-memory command.
     *
     * @param symbol is last symbol
     */
    public void setlastCellBuilder(String symbol) {
        this.lastSymbol = symbol;
    }

    /**
     * Gets last symbol.
     * @return lastSymbol
     */
    public String getLastCellBuilder() {
        return lastSymbol;
    }
}
