package edu.kit.kastel.codefight.aicommandsgame;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.ais.AIobject;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfield;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfieldobject;


/**
 * Represents the implementation of the JMZ (Jump if Zero) command for AI objects in the CodeFight game.
 * This class handles the behavior of an AI object when encountering a JMZ command.
 * If the argument B of the current cell is zero, the AI object jumps to the specified position;
 * otherwise, it proceeds to the next position.
 * It also updates the symbols on the playfield accordingly.
 *
 * @author uxtdn
 * @version 1.0
 */
public class JmzC {
    /**
     * Executes the JMZ command for the given AI object and updates the playfield accordingly.
     *
     * @param playfield     The playfield where the AI object is located.
     * @param ai            The AI object executing the JMZ command.
     * @param model  The symbol representing a normal cell on the playfield.
     * @param nextSymbol    The symbol representing the next cell for the next AI object.
     * @param roundSymbol   The symbol representing the current round on the playfield.
     * @param nextAI        The next AI object in the sequence.
     */
    public void jmz(CodeFight model, Playfield playfield, AIobject ai,
                    String nextSymbol, String roundSymbol, AIobject nextAI) {
        int currentCell = ai.getPosition(); // Current position of the AI command
        Playfieldobject cell = playfield.getPlayfieldcell(playfield.getPlayfield(), currentCell);


        Playfieldobject currentcell = playfield.getPlayfieldcell(playfield.getPlayfield(), ai.getPosition());
        if (currentcell.getLastCellBuilder() == null) {
            playfield.setPlayfieldCellSymbol(ai.getPosition(), playfield, model.getStartSymbol());
        } else {
            playfield.setPlayfieldCellSymbol(ai.getPosition(), playfield, currentcell.getLastCellBuilder());
        }
        int argumenta = cell.getArgumentA() % playfield.getPlayfieldsize();
        int targetcell = (currentCell + argumenta + playfield.getPlayfieldsize()) % playfield.getPlayfieldsize();

        // Get the value of argument B in the check cell
        int argumentb = cell.getArgumentB() % playfield.getPlayfieldsize();
        int checkCell = (currentCell + argumentb + playfield.getPlayfieldsize()) % playfield.getPlayfieldsize();
        Playfieldobject checkCellEntry = playfield.getPlayfieldcell(playfield.getPlayfield(), checkCell);
        int argumentB = checkCellEntry.getArgumentB();

        // If the value of argument B in the check cell is zero, jump to the check cell
        if (argumentB == 0) {
            ai.setPosition(targetcell);
            model.setRoundSymbols(roundSymbol);
            ai.setSteps();
        } else {
            ai.setSteps();
            int nextPosition = (currentCell + 1) % playfield.getPlayfieldsize();
            ai.setPosition(nextPosition);
            model.setRoundSymbols(roundSymbol);
        }

        // Update symbol for the next AI object if it is running
        if (nextAI.getRunning()) {
            int nextAIposition = nextAI.getPosition();
            playfield.setPlayfieldCellSymbol(nextAIposition, playfield, nextSymbol);
        }
    }
}
