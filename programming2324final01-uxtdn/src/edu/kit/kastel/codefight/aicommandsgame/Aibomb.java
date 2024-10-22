package edu.kit.kastel.codefight.aicommandsgame;

import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfieldobject;

import java.util.Objects;

/**
 * Represents the logic for determining if an AI command triggers a bomb in the AI Commands game.
 * Provides a method to check if an AI command triggers a bomb explosion.
 * @author uxtdn
 * @version 1.0
 */
public class Aibomb {
    private static final String JMZ = "JMZ";
    private static final String JMP = "JMP";
    private static final String STOP = "STOP";
    /**
     * Determines if an AI command triggers a bomb explosion.
     *
     * @param cell The Playfieldobject representing the AI command.
     * @return true if the AI command triggers a bomb explosion, false otherwise.
     */
    public boolean aibomb(Playfieldobject cell) {
        String command = cell.getAICommand();
        int argumenta = cell.getArgumentA();
        int argumentb = cell.getArgumentB();
        return argumentb == 0 && argumenta == 0 && Objects.equals(command, JMZ)
                || argumenta == 0 && Objects.equals(command, JMP) || Objects.equals(command, STOP);
    }
}
