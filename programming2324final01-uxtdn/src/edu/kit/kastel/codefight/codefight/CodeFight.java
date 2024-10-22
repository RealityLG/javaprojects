package edu.kit.kastel.codefight.codefight;
/*
 * Copyright (c) 2024, KASTEL. All rights reserved.
 */

import edu.kit.kastel.codefight.model.objects.InitMode;
import edu.kit.kastel.codefight.model.objects.Phase;
import edu.kit.kastel.codefight.model.objects.aimap.AIMAP;
import edu.kit.kastel.codefight.model.objects.ais.AIRunningQuestion;
import edu.kit.kastel.codefight.model.objects.ais.AIobject;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfield;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfieldobject;

/**
 * This class represents the facade of CodeFight.
 * 
 * @author uxtdn
 * @version 1.0
 */
public class CodeFight {
    private static final int NEXTAICOMMAND = 3;
    private static final int NEXTAISCOMMAND = 4;
    /**
     * The maximum number of AI objects allowed in the game.
     */
    private final int maxAI;
    private final String startSymbol;
    private final int sizePlayfield;

    private Phase currentPhase;

    private final InitMode initMode;
    private Playfield playfield;
    private final AIMAP aIswithCommands;
    private final String[] symbolAI;
    private final String bordersymbol;
    private final String nextAICommand;
    private final String nextAIsCommands;
    private AIobject[] ais;
    private AIobject lastAI;


    /**
     * Constructs a CodeFight object with the specified parameters.
     *
     * @param sizePlayfield   The size of the playfield.
     * @param symbolPlayfield The symbols representing various elements of the playfield.
     * @param symbolKI        The symbols representing the AI objects.
     * @param maxAI           The maximum number of AI objects.
     */
    public CodeFight(int sizePlayfield, String[] symbolPlayfield, String[] symbolKI, int maxAI) {

        this.maxAI = maxAI;
        this.initMode = new InitMode();
        this.playfield = new Playfield(sizePlayfield);
        this.aIswithCommands = new AIMAP();
        this.currentPhase = Phase.BEFOREGAME;
        this.startSymbol = symbolPlayfield[1];
        this.nextAICommand = symbolPlayfield[NEXTAICOMMAND];
        this.bordersymbol = symbolPlayfield[2];
        this.nextAIsCommands = symbolPlayfield[NEXTAISCOMMAND];
        this.sizePlayfield = sizePlayfield;
        this.ais = new AIobject[symbolKI.length / 2];
        this.symbolAI = symbolKI.clone();
        this.lastAI = null;
    }
    /**
     * Retrieves the symbol representing the boundary of the playfield.
     *
     * @return The symbol representing the boundary of the playfield.
     */
    public String getBordersymbol() {
        return bordersymbol;
    }

    /**
     * Retrieves an array containing symbols representing AI objects.
     *
     * @return An array containing symbols representing AI objects.
     */
    public String[] getSymbolAI() {
        return symbolAI.clone();
    }
    /**
     * Retrieves the current phase of the game.
     *
     * @return The current phase of the game.
     */
    public Phase getCurrentPhase() {
        return currentPhase;
    }

    /**
     * Retrieves the initialization mode of the game.
     *
     * @return The initialization mode of the game.
     */
    public InitMode getInitMode() {
        return initMode;
    }

    /**
     * Retrieves an array containing AI objects.
     *
     * @return An array containing AI objects.
     */
    public AIobject[] getAIs() {
        return ais.clone();
    }

    /**
     * Sets the array of AI objects to the specified array.
     *
     * @param aIobjects The array of AI objects to set.
     */
    public void setAIs(AIobject[] aIobjects) {
        this.ais = aIobjects.clone();
    }

    /**
     * Retrieves the map of AI objects with their associated commands.
     *
     * @return The map of AI objects with their associated commands.
     */
    public AIMAP getAIswithCommands() {
        return aIswithCommands;
    }

    /**
     * Retrieves the size of the playfield.
     *
     * @return The size of the playfield.
     */
    public int getSizePlayfield() {
        return sizePlayfield;
    }


    /**
     * Retrieves the symbol representing the command for the next AI.
     *
     * @return The symbol representing the command for the next AI.
     */
    public String getNextAICommand() {
        return nextAICommand;
    }
    /**
     * Retrieves the symbols representing the commands for the next AI.
     *
     * @return The symbols representing the commands for the next AI.
     */
    public String getNextAIsCommands() {
        return nextAIsCommands;
    }
    /**
     * Retrieves the symbol representing the start of the playfield.
     *
     * @return The symbol representing the start of the playfield.
     */
    public String getStartSymbol() {
        return startSymbol;
    }

    /**
     * Retrieves the playfield of the game.
     *
     * @return The playfield of the game.
     */
    public Playfield getPlayfield() {
        return playfield;
    }

    /**
     * Retrieves the maximum number of AI objects.
     *
     * @return The maximum number of AI objects.
     */
    public int getMaxAI() {
        return maxAI;
    }

    /**
     * Sets the current phase of the game.
     *
     * @param phase The phase to set.
     */
    public void setCurrentPhase(Phase phase) {
        this.currentPhase = phase;
    }


    /**
     * Retrieves the status of the given AI object, including its running status, step counter, next command, and memory cell information.
     *
     * @param aIobject         The AI object for which to retrieve the status.
     * @param currentPlayfield The current playfield.
     * @return A string representing the status of the AI object.
     */
    public String getRunning(AIobject aIobject, Playfield currentPlayfield) {
        AIRunningQuestion message = new AIRunningQuestion();
        Playfieldobject[] playfield = currentPlayfield.getPlayfield();
        int position = aIobject.getPosition();
        boolean isRunning = aIobject.getRunning();
        int counter = aIobject.getSteps();
        String nextCommand = "";
        int memoryCell = -1;
        int argumenta = 0;
        int argumentb = 0;
        if (isRunning) {
            Playfieldobject currentCell = playfield[position];
            argumenta = currentCell.getArgumentA();
            argumentb = currentCell.getArgumentB();
            nextCommand = currentCell.getAICommand();
            memoryCell = position;
        }

        return message.showAI(aIobject.getName(), isRunning, counter, nextCommand, argumenta, argumentb, memoryCell);
    }


    /**
     * Resets the game state to its initial state.
     */
    public void reset() {
        this.playfield = new Playfield(sizePlayfield);
        this.currentPhase = Phase.BEFOREGAME;
        this.ais = new AIobject[maxAI];
    }

    /**
     * Retrieves the next running AI object in the sequence after the given AI object.
     *
     * @param currentAI The current AI object.
     * @return The next running AI object, or null if none is found.
     */
    public AIobject getNextRunningAI(AIobject currentAI) {
        AIobject[] ais = getAIs();
        int currentPos = -1;
        for (int i = 0; i < ais.length; i++) {
            if (ais[i].equals(currentAI)) {
                currentPos = i;
                break;
            }
        }
        if (currentPos == -1) {
            return null;
        }

        int nextPos = (currentPos + 1) % ais.length;

        for (int i = 0; i < ais.length; i++) {
            if (ais[nextPos].getRunning()) {
                return ais[nextPos];
            }
            nextPos = (nextPos + 1) % ais.length;
        }
        return null;
    }

    /**
     *  to.
     * @param ai is last AI.
     */
    public void setlastAI(AIobject ai) {
        this.lastAI = ai;
    }

    /**
     * d.
     *
     * @return lastAI.
     */
    public AIobject getLastAI() {
        return lastAI;
    }

    /**
     * sets round Symbols in game.
     * @param roundSymbols Symbol for AIs.
     */
    public void setRoundSymbols(String roundSymbols) {
        for (AIobject ai : ais) {
            if (ai.getRunning()) {
                int position = ai.getPosition();
                playfield.setPlayfieldCellSymbol(position, playfield, roundSymbols);
            }
        }
    }
}
