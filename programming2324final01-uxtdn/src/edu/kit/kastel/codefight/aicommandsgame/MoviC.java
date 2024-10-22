package edu.kit.kastel.codefight.aicommandsgame;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.ais.AIobject;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfield;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfieldobject;


/**
 * Represents the implementation of the MOV_I (Move if Intermediate) command for AI objects in the CodeFight game.
 * This class handles the behavior of an AI object when executing a MOV_I command.
 * It moves the AI object to a target cell based on the intermediate cell calculation and updates the symbols on the playfield accordingly.
 *
 * @author uxtdn
 * @version 1.0
 */
public class MoviC {
    /**
     * Executes the MOV_I command for the given AI object and updates the playfield accordingly.
     *
     * @param model         The CodeFight game model.
     * @param playfield     The playfield where the AI object is located.
     * @param ai            The AI object executing the MOV_I command.
     * @param nextSymbol    The symbol representing the next cell for the next AI object.
     * @param roundSymbol   The symbol representing the current round on the playfield.
     * @param nextAI        The next AI object in the sequence.
     */
    public void movi(CodeFight model, Playfield playfield, AIobject ai,
                     String nextSymbol, String roundSymbol, AIobject nextAI) {
        // Current position of the AI object
        int currentPosition = ai.getPosition();
        Playfieldobject cell = playfield.getPlayfieldcell(playfield.getPlayfield(), currentPosition);

        // Calculate positions for intermediate and target cells
        int argumenta = cell.getArgumentA() % playfield.getPlayfieldsize();
        int cellA = (currentPosition + argumenta + playfield.getPlayfieldsize()) % playfield.getPlayfieldsize();
        int argumentb = cell.getArgumentB() % playfield.getPlayfieldsize();
        int intermediateCell = (currentPosition + argumentb + playfield.getPlayfieldsize()) % playfield.getPlayfieldsize();
        int targetB = playfield.getPlayfieldcell(playfield.getPlayfield(), intermediateCell).getArgumentB();
        targetB = (targetB % playfield.getPlayfieldsize() + playfield.getPlayfieldsize()) % playfield.getPlayfieldsize();
        int targetCell = (intermediateCell + targetB + playfield.getPlayfieldsize()) % playfield.getPlayfieldsize();
        // Get information from cellA
        Playfieldobject entryA = playfield.getPlayfieldcell(playfield.getPlayfield(), cellA);
        // Copy information from cell A to target cell with a new object
        Playfieldobject infoCell = getPlayfieldobject(ai, entryA);
        playfield.setPlayfieldCell(targetCell, infoCell);

        Playfieldobject currentcell = playfield.getPlayfieldcell(playfield.getPlayfield(), ai.getPosition());
        if (currentcell.getLastCellBuilder() == null) {
            playfield.setPlayfieldCellSymbol(ai.getPosition(), playfield, model.getStartSymbol());
        } else {
            playfield.setPlayfieldCellSymbol(ai.getPosition(), playfield, currentcell.getLastCellBuilder());
        }
        ai.setSteps();
        int nextPosition;
        if (currentPosition + 1 >= playfield.getPlayfieldsize()) {
            nextPosition = currentPosition + 1 - playfield.getPlayfieldsize();
        } else {
            nextPosition = currentPosition + 1;
        }
        ai.setPosition(nextPosition);
        model.setRoundSymbols(roundSymbol);

        // Update symbol for the next AI object if it is running
        if (nextAI.getRunning()) {
            int nextAIposition = nextAI.getPosition();
            playfield.setPlayfieldCellSymbol(nextAIposition, playfield, nextSymbol);
        }
    }

    private static Playfieldobject getPlayfieldobject(AIobject ai, Playfieldobject entryA) {
        Playfieldobject infoCell = new Playfieldobject(ai.getSymbol(),
                entryA.getAICommand(), entryA.getArgumentA(), entryA.getArgumentB());
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
