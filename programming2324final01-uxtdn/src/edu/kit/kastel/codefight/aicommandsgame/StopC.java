package edu.kit.kastel.codefight.aicommandsgame;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.ais.AIobject;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfield;



/**
 * Represents the implementation of the STOP command for AI objects in the CodeFight game.
 * This class handles the behavior of an AI object when executing a STOP command.
 * It stops the AI object, updates the playfield accordingly, and selects the next AI object.
 *
 * @author uxtdn
 * @version 1.0
 */
public class StopC {
    private static final String EXECUTED = " executed ";
    private static final String STOPPING = " steps until stopping.";

    /**
     * Executes the STOP command for the given AI object and updates the playfield accordingly.
     *
     * @param model           the hole model.
     * @param playfield       The playfield where the AI object is located.
     * @param ai              The AI object executing the STOP command.
     * @param nextSymbol      The symbol representing the next AI object.
     * @param nextAI          The next AI object in the sequence.
     */
    public void stop(CodeFight model, Playfield playfield, AIobject ai,
                     String nextSymbol, AIobject nextAI) {
        // Stop the AI and update steps
        ai.setRunning(false);
        int currentPosition = ai.getPosition();
        System.out.println(ai.getName() + EXECUTED + ai.getSteps() + STOPPING);
        String symbol = playfield.getPlayfieldcell(playfield.getPlayfield(), currentPosition).getLastCellBuilder();
        ai.setSteps();
        if (symbol != null) {
            playfield.setPlayfieldCellSymbol(currentPosition, playfield, symbol);
        } else {
            playfield.setPlayfieldCellSymbol(currentPosition, playfield, model.getStartSymbol());
        }
        // Select the next AI object
        if (nextAI.getRunning()) {
            int nextAIposition = nextAI.getPosition();
            playfield.setPlayfieldCellSymbol(nextAIposition, playfield, nextSymbol);
        }
    }
}
