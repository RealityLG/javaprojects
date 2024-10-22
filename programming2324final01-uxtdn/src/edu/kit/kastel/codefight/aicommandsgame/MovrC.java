package edu.kit.kastel.codefight.aicommandsgame;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.ais.AIobject;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfield;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfieldobject;


/**
 * Represents the implementation of the MOV_R (Move to Right) command for AI objects in the CodeFight game.
 * This class handles the behavior of an AI object when executing a MOV_R command.
 * It moves the contents of cell A to cell B and updates the symbols on the playfield accordingly.
 *
 * @author uxtdn
 * @version 1.0
 */
public class MovrC {
    /**
     * Executes the MOV_R command for the given AI object and updates the playfield accordingly.
     *
     * @param model         The CodeFight game model.
     * @param playfield     The playfield where the AI object is located.
     * @param ai            The AI object executing the MOV_R command.
     * @param nextSymbol    The symbol representing the next cell for the next AI object.
     * @param roundSymbol   The symbol representing the current round on the playfield.
     * @param nextAI        The next AI object in the sequence.
     */
    public void movr(CodeFight model, Playfield playfield, AIobject ai,
                     String nextSymbol, String roundSymbol, AIobject nextAI) {
        // Current position of the AI object
        int currentPosition = ai.getPosition();
        Playfieldobject cell = playfield.getPlayfieldcell(playfield.getPlayfield(), currentPosition);
        // Calculate positions for cell A and cell B
        int argumenta = cell.getArgumentA() % playfield.getPlayfieldsize();
        int cellA = (currentPosition + argumenta + playfield.getPlayfieldsize()) % playfield.getPlayfieldsize();
        int argumentb = cell.getArgumentB() % playfield.getPlayfieldsize();
        int cellB = (currentPosition + argumentb + playfield.getPlayfieldsize()) % playfield.getPlayfieldsize();

        Playfieldobject entryA = playfield.getPlayfieldcell(playfield.getPlayfield(), cellA);

        // Copy information from cell A to cell B with argumenta new object
        Playfieldobject infoCell = getPlayfieldobject(ai, entryA);
        playfield.setPlayfieldCell(cellB, infoCell);



        Playfieldobject currentcell = playfield.getPlayfieldcell(playfield.getPlayfield(), ai.getPosition());
        if (currentcell.getLastCellBuilder() == null) {
            playfield.setPlayfieldCellSymbol(ai.getPosition(), playfield, model.getStartSymbol());
        } else {
            playfield.setPlayfieldCellSymbol(ai.getPosition(), playfield, currentcell.getLastCellBuilder());
        }
        ai.setSteps();
        int nextPosition = (currentPosition + 1) % playfield.getPlayfieldsize();
        ai.setPosition(nextPosition);
        model.setRoundSymbols(roundSymbol);
        // Symbols for next running AIs
        if (nextAI.getRunning()) {
            int nextAIposition = nextAI.getPosition();
            playfield.setPlayfieldCellSymbol(nextAIposition, playfield, nextSymbol);
        }
    }

    private static Playfieldobject getPlayfieldobject(AIobject ai, Playfieldobject entryA) {
        Playfieldobject infoCell = new Playfieldobject(ai.getSymbol(), entryA.getAICommand(),
                entryA.getArgumentA(), entryA.getArgumentB());
        Aibomb aibomb = new Aibomb();
        if (aibomb.aibomb(infoCell)) {
            infoCell.setSymbol(ai.getAibombSymbol());
            infoCell.setlastCellBuilder(infoCell.getAISymbol());
        } else {
            infoCell.setSymbol(ai.getSymbol());
            infoCell.setlastCellBuilder(infoCell.getAISymbol());
        }
        return infoCell;
    }
}
