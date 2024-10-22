package edu.kit.kastel.codefight.command;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.Phase;
import edu.kit.kastel.codefight.model.objects.ais.AIobject;

/**
 * This command shows AI.
 *
 * @author uxtdn
 */
public class ShowAICommand implements Command {


    private static final String INVALID_COMMANDS_ARGUMENT = "Invalid Commands argument.";
    private static final String DIE_KI_EXISTIERT_NICHT = "Die KI existiert nicht.";

    @Override
    public CommandResult execute(CodeFight model, String[] commandArguments) {
        if (model.getCurrentPhase() == Phase.BEFOREGAME) {
            return new CommandResult(CommandResultType.FAILURE, StartGameCommand.STRING);
        }
        if (commandArguments.length != 1) {
            return new CommandResult(CommandResultType.FAILURE, INVALID_COMMANDS_ARGUMENT);
        }
        String name = commandArguments[0];

        AIobject targetAI = null;
        for (AIobject ai : model.getAIs()) {
            if (ai.getName().equals(name)) {
                targetAI = ai;
                break;
            }
        }

        if (targetAI == null) {
            return new CommandResult(CommandResultType.FAILURE, DIE_KI_EXISTIERT_NICHT);
        }
        String message = model.getRunning(targetAI, model.getPlayfield());

        return new CommandResult(CommandResultType.SUCCESS, message);
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }
}
