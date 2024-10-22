package edu.kit.kastel.codefight.aicommandsgame;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.ais.AIobject;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfield;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfieldobject;


/**
 * Represents the command for adding two values in the AI Commands game.
 * Provides a method for executing the ADD command and updating the game state.
 * @author uxtdn
 * @version 1.0
 */
public class AddC {
    /**
     * Executes the ADD command in the game.
     * Adds two values from the current cell in the playfield and updates the game state accordingly.
     *
     * @param model         The model.
     * @param playfield     The playfield containing the game state.
     * @param ai            The AI object representing the current AI player.
     * @param normalSymbol  The symbol representing a normal cell in the playfield.
     * @param nextSymbol    The symbol representing the next cell in the playfield.
     * @param roundSymbol   The symbol representing the round cell in the playfield.
     * @param nextAI        The AI object representing the next AI player.
     */
    public void add(CodeFight model, Playfield playfield,
                    AIobject ai, String normalSymbol, String nextSymbol, String roundSymbol, AIobject nextAI) {
        //current Position
        int currentPosition = ai.getPosition();
        Playfieldobject cell = playfield.getPlayfieldcell(playfield.getPlayfield(), currentPosition);

        // Retrieve the arguments from the cell
        int argA = cell.getArgumentA();
        int argB = cell.getArgumentB();

        // Add the two arguments and update the playfield
        int result = argA + argB;
        Playfieldobject currentCell = playfield.getPlayfieldcell(playfield.getPlayfield(), currentPosition);
        currentCell.setArgumentB(result);
        playfield.setPlayfieldCell(currentPosition, currentCell);

        // Update the symbols in the playfield
        ai.setSteps();
        playfield.setPlayfieldCellSymbol(currentPosition, playfield, normalSymbol);
        playfield.setlastCellbuilder(currentPosition, playfield, normalSymbol);

        if (currentPosition + 1 >= playfield.getPlayfieldsize()) {
            int nextPosition = currentPosition + 1 - playfield.getPlayfieldsize();
            ai.setPosition(nextPosition);
        } else {
            ai.setPosition(currentPosition + 1);
        }
        model.setRoundSymbols(roundSymbol);

        // Update the symbol for the next AI if it is active
        if (nextAI.getRunning()) {
            int nextAIposition = nextAI.getPosition();
            playfield.setPlayfieldCellSymbol(nextAIposition, playfield, nextSymbol);
        }
    }
}
