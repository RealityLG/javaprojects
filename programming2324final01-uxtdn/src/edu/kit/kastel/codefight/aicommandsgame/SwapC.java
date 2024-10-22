package edu.kit.kastel.codefight.aicommandsgame;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.ais.AIobject;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfield;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfieldobject;


/**
 * Represents the implementation of the SWAP command for AI objects in the CodeFight game.
 * This class handles the behavior of an AI object when executing a SWAP command.
 * It swaps the entries of two cells on the playfield and updates the AI's position and symbols accordingly.
 *
 * @author uxtdn
 * @version 1.0
 */
public class SwapC {
    /**
     * Executes the SWAP command for the given AI object and updates the playfield accordingly.
     *
     * @param model         The model.
     * @param playfield    The playfield where the AI object is located.
     * @param ai           The AI object executing the SWAP command.
     * @param nextSymbol   The symbol representing the next AI object.
     * @param roundSymbol  The symbol representing the current round on the playfield.
     * @param nextAI       The next AI object in the sequence.
     */
    public void swap(CodeFight model, Playfield playfield, AIobject ai,
                     String nextSymbol, String roundSymbol, AIobject nextAI) {
        int currentCell = ai.getPosition(); // Current position of the AI command
        Playfieldobject cell = playfield.getPlayfieldcell(playfield.getPlayfield(), currentCell);
        int argumenta = cell.getArgumentA() % playfield.getPlayfieldsize();
        int argumentb = cell.getArgumentB() % playfield.getPlayfieldsize();
        int cellA = (currentCell + argumenta + playfield.getPlayfieldsize()) % playfield.getPlayfieldsize(); // Berechnung für Zelle A
        int cellB = (currentCell + argumentb + playfield.getPlayfieldsize()) % playfield.getPlayfieldsize(); // Berechnung für Zelle B

        // Swap entries of cell A and cell B
        Playfieldobject entryA = playfield.getPlayfieldcell(playfield.getPlayfield(), cellA);
        Playfieldobject entryB = playfield.getPlayfieldcell(playfield.getPlayfield(), cellB);
        int enta = entryA.getArgumentA();
        entryA.setArgumentA(entryB.getArgumentB());
        entryB.setArgumentB(enta);
        Aibomb aibomb = new Aibomb();
        if (aibomb.aibomb(entryA)) {
            entryA.setSymbol(ai.getAibombSymbol());
            entryA.setlastCellBuilder(entryA.getAISymbol());
        } else {
            entryA.setSymbol(ai.getSymbol());
            entryA.setlastCellBuilder(entryA.getAISymbol());
        }
        if (aibomb.aibomb(entryB)) {
            entryB.setSymbol(ai.getAibombSymbol());
            entryB.setlastCellBuilder(entryB.getAISymbol());
        } else {
            entryB.setSymbol(ai.getSymbol());
            entryB.setlastCellBuilder(entryB.getAISymbol());
        }
        playfield.setPlayfieldCell(cellA, entryA);
        playfield.setPlayfieldCell(cellB, entryB);

        // Update AI position, step, and symbol
        ai.setSteps();

        Playfieldobject currentcell = playfield.getPlayfieldcell(playfield.getPlayfield(), ai.getPosition());
        if (currentcell.getLastCellBuilder() == null) {
            playfield.setPlayfieldCellSymbol(ai.getPosition(), playfield, model.getStartSymbol());
        } else {
            playfield.setPlayfieldCellSymbol(ai.getPosition(), playfield, currentcell.getLastCellBuilder());
        }
        int nextPosition = (currentCell + 1) % playfield.getPlayfieldsize(); // Nächste Position des KI-Befehls
        ai.setPosition(nextPosition);
        model.setRoundSymbols(roundSymbol);
        // Update symbol for the next AI if it is running
        if (nextAI.getRunning()) {
            int nextAIposition = nextAI.getPosition();
            playfield.setPlayfieldCellSymbol(nextAIposition, playfield, nextSymbol);
        }
    }
}
