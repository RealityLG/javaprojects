package edu.kit.kastel.codefight.command;

import edu.kit.kastel.codefight.aicommandsgame.AddC;
import edu.kit.kastel.codefight.aicommandsgame.AddrC;
import edu.kit.kastel.codefight.aicommandsgame.CMP;
import edu.kit.kastel.codefight.aicommandsgame.JmpC;
import edu.kit.kastel.codefight.aicommandsgame.JmzC;
import edu.kit.kastel.codefight.aicommandsgame.MoviC;
import edu.kit.kastel.codefight.aicommandsgame.MovrC;
import edu.kit.kastel.codefight.aicommandsgame.StopC;
import edu.kit.kastel.codefight.aicommandsgame.SwapC;
import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.AIGameCommands;
import edu.kit.kastel.codefight.model.objects.Phase;
import edu.kit.kastel.codefight.model.objects.ais.AIobject;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfield;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfieldobject;

/**
 * This command is for next AI steps.
 *
 * @author uxtdn
 */
public class NextCommand implements Command {

    private static final String PHASE_NOT_READY = Showmemory.PHASE_NOT_READY;
    private static final String NO_NUMBER = "No number.";
    private static final String STOP = AIGameCommands.STOP;
    private static final String MOVR = AIGameCommands.MOV_R;
    private static final String MOVI = AIGameCommands.MOV_I;
    private static final String ADD = AIGameCommands.ADD;
    private static final String ADDR = AIGameCommands.ADD_R;
    private static final String JMP = AIGameCommands.JMP;
    private static final String JMZ = AIGameCommands.JMZ;
    private static final String CMP = AIGameCommands.CMP;
    private static final String SWAP = AIGameCommands.SWAP;



    @Override
    public CommandResult execute(CodeFight model, String[] commandArguments) {
        if (model.getCurrentPhase() != Phase.GAME) {
            return new CommandResult(CommandResultType.FAILURE, PHASE_NOT_READY);
        }
        int roundsteps;
        if (commandArguments == null || commandArguments.length == 0) {
            roundsteps = 1;
        } else {
            if (!isNumber(commandArguments[0])) {
                return new CommandResult(CommandResultType.FAILURE, NO_NUMBER);
            }
            roundsteps = Integer.parseInt(commandArguments[0]);
        }
        int steps = 0;
        int index = searchAI(model.getLastAI(), model.getAIs());

        while (steps < roundsteps) {
            boolean anyAIExecuted = false;
            AIobject[] playingAIs = model.getAIs();
            for (int x = index; x < playingAIs.length; x++) {

                if (!playingAIs[x].getRunning()) {
                    continue; // Falls die KI gestoppt wurde, überspringen
                }

                anyAIExecuted = true;
                steps++;
                executeAICommand(model, playingAIs[x]);
                if (steps >= roundsteps) {
                    model.setlastAI(playingAIs[x]);
                    break; // Beenden Sie die äußere Schleife, wenn die Runden abgeschlossen sind
                }
            }
            if (!anyAIExecuted) {
                steps++;
            }
        }
        return new CommandResult(CommandResultType.SUCCESS, null);
    }


    @Override
    public int getNumberOfArguments() {
        return 1;
    }

    private void executeAICommand(CodeFight model, AIobject ai) {
        Playfield playfield = model.getPlayfield();
        int position = ai.getPosition();
        Playfieldobject cell = playfield.getPlayfieldcell(playfield.getPlayfield(), position);
        String commandName = cell.getAICommand();
        switch (commandName) {
            case STOP:
                new StopC().stop(model, playfield, ai, model.getNextAICommand(), model.getNextRunningAI(ai));
                break;
            case MOVR:
                new MovrC().movr(model, playfield, ai, model.getNextAICommand(),
                        model.getNextAIsCommands(), model.getNextRunningAI(ai));
                break;
            case MOVI:
                new MoviC().movi(model, playfield, ai, model.getNextAICommand(),
                        model.getNextAIsCommands(), model.getNextRunningAI(ai));
                break;
            case ADD:
                new AddC().add(model, playfield, ai, ai.getSymbol(), model.getNextAICommand(),
                        model.getNextAIsCommands(), model.getNextRunningAI(ai));
                break;
            case ADDR:
                new AddrC().addr(model, playfield, ai, model.getNextAICommand(),
                        model.getNextAIsCommands(), model.getNextRunningAI(ai));
                break;
            case JMP:
                new JmpC().jmp(model, playfield, ai, model.getNextAICommand(),
                        model.getNextAIsCommands(), model.getNextRunningAI(ai));
                break;
            case JMZ:
                new JmzC().jmz(model, playfield, ai, model.getNextAICommand(),
                        model.getNextAIsCommands(), model.getNextRunningAI(ai));
                break;
            case CMP:
                new CMP().cmp(model, playfield, ai, model.getNextAICommand(),
                        model.getNextAIsCommands(), model.getNextRunningAI(ai));
                break;
            case SWAP:
                new SwapC().swap(model, playfield, ai, model.getNextAICommand(),
                        model.getNextAIsCommands(), model.getNextRunningAI(ai));
                break;
            default:
                break;
        }
    }
    private int searchAI(AIobject lastAI, AIobject[] aIs) {
        for (int i = 0; i < aIs.length; i++) {
            if (aIs[i] == lastAI) {
                int index = (i + 1) % aIs.length;
                for (int x = 0; x < aIs.length; x++) {
                    if (aIs[index].getRunning()) {
                        return index;
                    }
                    index = (index + 1) % aIs.length;
                }
                return 0;
            }
        }
        return 0;
    }


    private boolean isNumber(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
