package edu.kit.kastel.codefight.command;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.AIGameCommands;
import edu.kit.kastel.codefight.model.objects.aimap.AIMAP;
import edu.kit.kastel.codefight.model.objects.aimap.AICommandObject;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfield;
import edu.kit.kastel.codefight.model.objects.Phase;

import static edu.kit.kastel.codefight.model.objects.aimap.AICommandHandler.parseCommands;
import static edu.kit.kastel.codefight.model.objects.aimap.AICommandHandler.splitInput;

/**
 * Represents a command for adding an AI to the game.
 *
 * @author uxtdn
 * @version 1.0
 */
final class AddAICommand implements Command {


    private static final String CANNOT_EXECUTE_COMMAND2 = "Cannot execute command.";
    private static final String CANNOT_EXECUTE_COMMAND = CANNOT_EXECUTE_COMMAND2;
    private static final String COMMANDS_OR_NAME_MISSING = "Commands or name missing.";
    private static final String AI_WITH_NAME = "AI with name '";
    private static final String AI_WITH_NAME1 = "AI with name' ";
    private static final String INVALID_COMMANDS_PROVIDED = "Invalid commands provided.";
    private static final String INVALID_3 = "Invalid 3";
    private static final String INVALID_COMMANDS = "Invalid Commands";
    private static final String TOO_MUCH_COMMANDS = "Too much commands";
    private static final int THREESELEMENT = 3;
    private static final String ALREADY_EXISTS = "' already exists.";
    private static final String HAS_SPACE_IN_NAME = "' has space in name.";
    private static final String SPACE = " ";
    public static final String CANNOT_EXECUTE_COMMAND1 = CANNOT_EXECUTE_COMMAND2;

    @Override
    public CommandResult execute(CodeFight model, String[] commandArguments) {
        AIMAP aimap = model.getAIswithCommands();
        if (model.getCurrentPhase() != Phase.BEFOREGAME) {
            return new CommandResult(CommandResultType.FAILURE, CANNOT_EXECUTE_COMMAND);
        }
        if (commandArguments.length < 2) {
            return new CommandResult(CommandResultType.FAILURE, COMMANDS_OR_NAME_MISSING);
        }
        String name = commandArguments[0];
        if (aimap.contains(name)) {
            return new CommandResult(CommandResultType.FAILURE, AI_WITH_NAME + name + ALREADY_EXISTS);
        }
        if (name.contains(SPACE)) {
            return new CommandResult(CommandResultType.FAILURE, AI_WITH_NAME1 + name + HAS_SPACE_IN_NAME);
        }
        String[] elements = splitInput(commandArguments[1]);
        if (!validateCommands(elements)) {
            return  new CommandResult(CommandResultType.FAILURE, INVALID_COMMANDS_PROVIDED);
        }
        if (elements.length % THREESELEMENT != 0) {
            return new CommandResult(CommandResultType.FAILURE, INVALID_3);
        }
        //second arg to array list
        AICommandObject[] aiObjects;
        if (parseCommands(elements) == null) {
            return new CommandResult(CommandResultType.FAILURE, INVALID_COMMANDS);
        } else {
            aiObjects = parseCommands(elements);
        }

        //looking for another Artifical Intellegienc
        Playfield playfield = model.getPlayfield();
        int sizepfield = playfield.getPlayfieldsize();
        //looking for to many aicommands
        int maximum;
        if (sizepfield % 2 == 1) {
            maximum = sizepfield / 2 + 1;
        } else {
            maximum = sizepfield / 2;
        }
        assert aiObjects != null;
        if (maximum < aiObjects.length) {
            return new CommandResult(CommandResultType.FAILURE, TOO_MUCH_COMMANDS);
        }

        aimap.addAI(name, aiObjects);
        return new CommandResult(CommandResultType.SUCCESS, name);
    }


    private boolean validateCommands(String[] aiObjects) {
        String[] aIGameCommands = new AIGameCommands().getAIGameCommands();
        for (int i = 0; i < aiObjects.length; i += THREESELEMENT) {
            String command = aiObjects[i];
            boolean commandFound = false;
            for (String validCommand : aIGameCommands) {
                if (validCommand.equals(command)) {
                    commandFound = true;
                    break;
                }
            }
            if (!commandFound) {
                return false;
            }
        }
        return true;
    }
    @Override
    public int getNumberOfArguments() {
        return 2;
    }
}

