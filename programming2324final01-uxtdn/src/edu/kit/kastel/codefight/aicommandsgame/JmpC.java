package edu.kit.kastel.codefight.aicommandsgame;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.ais.AIobject;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfield;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfieldobject;


/**
 * Represents the JMP (jump) logic for AI commands in the AI Commands game.
 * Provides a method to execute the JMP command and update the playfield accordingly.
 * @author uxtdn
 * @version 1.0
 */
public class JmpC {
    /**
     * Executes the JMP (jump) command and updates the playfield accordingly.
     *
     * @param model         The model.
     * @param playfield    The current playfield.
     * @param ai           The current AI object.
     * @param nextSymbol   The symbol to represent the next cell.
     * @param roundSymbol  The symbol to represent the current round cell.
     * @param nextAI       The next AI object.
     */
    public void jmp(CodeFight model, Playfield playfield, AIobject ai,
                    String nextSymbol, String roundSymbol, AIobject nextAI) {
        int currentCell = ai.getPosition(); // Current position of the AI command
        Playfieldobject cell = playfield.getPlayfieldcell(playfield.getPlayfield(), currentCell);

        Playfieldobject currentcell = playfield.getPlayfieldcell(playfield.getPlayfield(), ai.getPosition());
        if (currentcell.getLastCellBuilder() == null) {
            playfield.setPlayfieldCellSymbol(ai.getPosition(), playfield, model.getStartSymbol());
        } else {
            playfield.setPlayfieldCellSymbol(ai.getPosition(), playfield, currentcell.getLastCellBuilder());
        }

        // Calculate target cell
        int arga = cell.getArgumentA() % playfield.getPlayfieldsize();

        int targetCell = (currentCell + arga + playfield.getPlayfieldsize()) % playfield.getPlayfieldsize();
        // Update symbols
        ai.setPosition(targetCell);
        model.setRoundSymbols(roundSymbol);

        // Execute the next AI command normally
        ai.setSteps();

        // Update symbol for the next AI if it's running
        if (nextAI.getRunning()) {
            int nextAIposition = nextAI.getPosition();
            playfield.setPlayfieldCellSymbol(nextAIposition, playfield, nextSymbol);
        }
    }
}
