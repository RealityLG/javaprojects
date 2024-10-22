package edu.kit.kastel.codefight.aicommandsgame;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.ais.AIobject;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfield;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfieldobject;


/**
 * Represents the command for adding a value to a cell in the AI Commands game.
 * Provides a method for executing the ADD_R command and updating the game state.
 * @author uxtdn
 * @version 1.0
 */
public class AddrC {
    /**
     * Executes the ADD_R command in the game.
     * Adds a value to the cell at a relative position in the playfield and updates the game state accordingly.
     *
     * @param model         The model.
     * @param playfield     The playfield containing the game state.
     * @param ai            The AI object representing the current AI player.
     * @param nextSymbol    The symbol representing the next cell in the playfield.
     * @param roundSymbol   The symbol representing the round cell in the playfield.
     * @param nextAI        The AI object representing the next AI player.
     */
    public void addr(CodeFight model, Playfield playfield, AIobject ai,
                     String nextSymbol, String roundSymbol, AIobject nextAI) {
        //current Position from AI
        int currentPosition = ai.getPosition();
        Playfieldobject cell = playfield.getPlayfieldcell(playfield.getPlayfield(), currentPosition);

        //getting argument b
        int argumentb = cell.getArgumentB() % playfield.getPlayfieldsize();
        int targetCell = (currentPosition + argumentb + playfield.getPlayfieldsize()) % playfield.getPlayfieldsize();

        //getting argument a
        int argA = cell.getArgumentA();

        // Update entry B of the target cell
        Playfieldobject targetCellEntry = playfield.getPlayfieldcell(playfield.getPlayfield(), targetCell);
        int currentBValue = targetCellEntry.getArgumentB();
        targetCellEntry.setArgumentB(currentBValue + argA);
        Aibomb aibomb = new Aibomb();
        if (aibomb.aibomb(targetCellEntry)) {
            targetCellEntry.setSymbol(ai.getAibombSymbol());
            targetCellEntry.setlastCellBuilder(targetCellEntry.getAISymbol());
        } else {
            targetCellEntry.setSymbol(ai.getSymbol());
            targetCellEntry.setlastCellBuilder(targetCellEntry.getAISymbol());
        }
        playfield.setPlayfieldCell(targetCell, targetCellEntry);

        // Update symbols in the playfield
        ai.setSteps();
        Playfieldobject currentcell = playfield.getPlayfieldcell(playfield.getPlayfield(), ai.getPosition());
        if (currentcell.getLastCellBuilder() == null) {
            playfield.setPlayfieldCellSymbol(ai.getPosition(), playfield, model.getStartSymbol());
        } else {
            playfield.setPlayfieldCellSymbol(ai.getPosition(), playfield, currentcell.getLastCellBuilder());
        }
        if (currentPosition + 1 >= playfield.getPlayfieldsize()) {
            int nextPosition = currentPosition + 1 - playfield.getPlayfieldsize();
            ai.setPosition(nextPosition);
        } else {
            ai.setPosition(currentPosition + 1);
        }
        model.setRoundSymbols(roundSymbol);

        // Update symbol for the next AI if it is active
        if (nextAI.getRunning()) {
            int nextAIposition = nextAI.getPosition();
            playfield.setPlayfieldCellSymbol(nextAIposition, playfield, nextSymbol);
        }
    }
}
