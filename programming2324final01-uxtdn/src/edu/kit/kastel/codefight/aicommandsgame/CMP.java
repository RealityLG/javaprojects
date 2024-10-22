package edu.kit.kastel.codefight.aicommandsgame;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.ais.AIobject;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfield;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfieldobject;


/**
 * Represents the comparison (CMP) logic for AI commands in the AI Commands game.
 * Provides a method to compare values of two cells and execute the next AI command accordingly.
 * @author uxtdn
 * @version 1.0
 */
public class CMP {
    /**
     * Compares values of two cells and executes the next AI command accordingly.
     *
     * @param model         The model.
     * @param playfield   The current playfield.
     * @param ai          The current AI object.
     * @param nextSymbol   The symbol to represent the next cell.
     * @param roundSymbol  The symbol to represent the current round cell.
     * @param nextAI      The next AI object.
     */
    public void cmp(CodeFight model, Playfield playfield, AIobject ai,
                    String nextSymbol, String roundSymbol, AIobject nextAI) {
        int currentCell = ai.getPosition(); // Aktuelle Position des KI-Befehls
        Playfieldobject cell = playfield.getPlayfieldcell(playfield.getPlayfield(), currentCell);

        // Calculate cell A position
        int argumenta = cell.getArgumentA() % playfield.getPlayfieldsize();
        int cellA = (currentCell + argumenta + playfield.getPlayfieldsize()) % playfield.getPlayfieldsize();

        // Calculate cell B position
        int argumentb = cell.getArgumentB() % playfield.getPlayfieldsize();
        int cellB = (currentCell + argumentb + playfield.getPlayfieldsize()) % playfield.getPlayfieldsize();

        // Compare values of cell A and cell B
        Playfieldobject entryA = playfield.getPlayfieldcell(playfield.getPlayfield(), cellA);
        Playfieldobject entryB = playfield.getPlayfieldcell(playfield.getPlayfield(), cellB);
        boolean equal = entryA.getArgumentA() == entryB.getArgumentB();

        Playfieldobject currentcell = playfield.getPlayfieldcell(playfield.getPlayfield(), ai.getPosition());
        if (currentcell.getLastCellBuilder() == null) {
            playfield.setPlayfieldCellSymbol(ai.getPosition(), playfield, model.getStartSymbol());
        } else {
            playfield.setPlayfieldCellSymbol(ai.getPosition(), playfield, currentcell.getLastCellBuilder());
        }
        int nextPosition; // Jump two steps forward
        if (!equal) {
            nextPosition = (currentCell + 2) % playfield.getPlayfieldsize();
        } else {
            // Execute the next AI command normally
            nextPosition = (currentCell + 1) % playfield.getPlayfieldsize();
        }
        ai.setPosition(nextPosition);
        ai.setSteps();
        model.setRoundSymbols(roundSymbol);

        // Update symbol for the next AI if it's running
        if (nextAI.getRunning()) {
            int nextAIposition = nextAI.getPosition();
            playfield.setPlayfieldCellSymbol(nextAIposition, playfield, nextSymbol);
        }
    }

}
