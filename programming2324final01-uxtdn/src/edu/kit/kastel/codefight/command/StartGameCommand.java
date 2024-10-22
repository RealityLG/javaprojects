package edu.kit.kastel.codefight.command;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.aimap.AIMAP;
import edu.kit.kastel.codefight.model.objects.ais.AIobject;
import edu.kit.kastel.codefight.model.objects.InitMode;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfield;
import edu.kit.kastel.codefight.model.objects.Phase;

import java.util.Objects;

/**
 * This command starts a game.
 *
 * @author uxtdn
 */
public class StartGameCommand implements Command {
    /**
     * String for Phase.
     */
    public static final String STRING = "Phase not ready.";
    /**
     * For command maximum input.
     */
    private static final int MAXIMUM_AIS = 1337;

    private static final String S = "#";
    private static final String NOT_ENOUGH_SYMBOLS_FOR_AIS = "not enough symbols for AIs.";

    private static final String GAME_STARTED = "Game started.";

    private static final String INVALID_AIS_TOO_MUCH_COMMANDS_FOR_AIS = "Invalid AIs, too much commands for AIs.";

    private static final String NOT_ENOUGH_AIS = "not enough AIs.";
    private static final String AIS_ARE_NOT_IMPLEMENTED_YET = "AIs are not implemented yet.";

    private static final String AI_NOT_FOUND = "AI not found.";





    @Override
    public CommandResult execute(CodeFight model, String[] commandArguments) {
        if (model.getCurrentPhase() != Phase.BEFOREGAME) {
            return new CommandResult(CommandResultType.FAILURE, STRING);
        }
        if (commandArguments.length < 2) {
            return new CommandResult(CommandResultType.FAILURE, NOT_ENOUGH_AIS);
        }
        AIMAP aimap = model.getAIswithCommands();
        if (aimap == null) {
            return new CommandResult(CommandResultType.FAILURE, AIS_ARE_NOT_IMPLEMENTED_YET);
        }
        for (String commandArgument : commandArguments) {
            if (!aimap.contains(commandArgument)) {
                return new CommandResult(CommandResultType.FAILURE, AI_NOT_FOUND);
            }
        }

        InitMode initMode = model.getInitMode();
        String init = initMode.getInit();
        long seed = initMode.getSeed();

        //checking if there are more AIs than symbols
        if (commandArguments.length > model.getMaxAI()) {
            return new CommandResult(CommandResultType.FAILURE, NOT_ENOUGH_SYMBOLS_FOR_AIS);
        }
        //AI Array with Position/Symbols
        AIobject[] runningAI = new AIobject[commandArguments.length];
        int numAIs = runningAI.length;
        for (int i = 0; i < numAIs; i++) {
            String[] symbols = model.getSymbolAI();
            String name = commandArguments[i];
            int position = model.getSizePlayfield() * i / numAIs;
            runningAI[i] = new AIobject();
            runningAI[i].setAIobject(name, position, symbols[i * 2], symbols[i * 2 + 1], true);
        }
        model.setAIs(runningAI);
        //checking AIs
        handleDuplicateAIName(runningAI);
        //ERROR HANDLING
        int maximumcommands = model.getSizePlayfield() / numAIs;
        for (int i = 0; i < numAIs; i++) {
            String name = commandArguments[i];
            int length = aimap.get(name).length;
            if (maximumcommands < length && i < numAIs - 1) {
                return new CommandResult(CommandResultType.FAILURE, INVALID_AIS_TOO_MUCH_COMMANDS_FOR_AIS);
            } else {
                maximumcommands = model.getSizePlayfield() - model.getAIs()[i].getPosition();
                if (maximumcommands < length) {
                    return new CommandResult(CommandResultType.FAILURE, INVALID_AIS_TOO_MUCH_COMMANDS_FOR_AIS);
                }
            }
        }
        //Playfield with INIT + INIT_SEED + AI commands
        Playfield playfield = model.getPlayfield();
        playfield.setPlayfield(playfield.fillplayfield(init, playfield.getPlayfieldsize(), model.getStartSymbol(), seed));
        playfield.setPlayfield(playfield.aiplayfield(model.getNextAICommand(), model.getNextAIsCommands(), playfield.getPlayfield(),
                commandArguments, aimap, runningAI));
        model.setCurrentPhase(Phase.GAME);
        return  new CommandResult(CommandResultType.SUCCESS, GAME_STARTED);
    }
    @Override
    public int getNumberOfArguments() {
        return MAXIMUM_AIS;
    }

    private void handleDuplicateAIName(AIobject[] runningAI) {
        for (AIobject iobject : runningAI) {
            int common = countCommonAIObjects(iobject, runningAI);
            if (common > 1) {
                updateDuplicateAIName(iobject, common, runningAI);
            }
        }
    }

    private int countCommonAIObjects(AIobject iobject, AIobject[] runningAI) {
        int common = 0;
        String name = iobject.getName();
        for (AIobject aIobject : runningAI) {
            if (Objects.equals(name, aIobject.getName()) && !name.contains(S)) {
                common++;
            }
        }
        return common;
    }

    private void updateDuplicateAIName(AIobject iobject, int common, AIobject[] runningAI) {
        int namenumber = common;
        String name = iobject.getName();
        for (int r = runningAI.length - 1; r >= 0; r--) {
            if (name.equals(runningAI[r].getName()) && !name.contains(S)) {
                runningAI[r].setName(name + S + (namenumber - 1));
                namenumber--;
            }
        }
    }
}
