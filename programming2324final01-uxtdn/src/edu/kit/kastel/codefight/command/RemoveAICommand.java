package edu.kit.kastel.codefight.command;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.Phase;
import edu.kit.kastel.codefight.model.objects.aimap.AIMAP;

/**
 * This command removes an AI from game.
 *
 * @author uxtdn
 */
public class RemoveAICommand implements Command {


    private static final String CANNOT_EXECUTE_COMMAND = AddAICommand.CANNOT_EXECUTE_COMMAND1;
    private static final String NOT_ENOUGH_ARGUMENTS = "Not enough arguments";
    private static final String AI_NOT_FOUND = "AI not found";

    @Override
    public CommandResult execute(CodeFight model, String[] commandArguments) {
        AIMAP aiMap = model.getAIswithCommands();
        if (model.getCurrentPhase() != Phase.BEFOREGAME) {
            return new CommandResult(CommandResultType.FAILURE, CANNOT_EXECUTE_COMMAND);
        }
        if (commandArguments.length < 1) {
            return new CommandResult(CommandResultType.FAILURE, NOT_ENOUGH_ARGUMENTS);
        }
        String name = commandArguments[0];
        if (!aiMap.contains(name)) {
            return new CommandResult(CommandResultType.FAILURE, AI_NOT_FOUND);
        }
        aiMap.removeAI(name);
        return new CommandResult(CommandResultType.SUCCESS, name);

    }

    @Override
    public int getNumberOfArguments() {
        return 1;
    }
}
