package edu.kit.kastel.codefight.command;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.ais.AIobject;
import edu.kit.kastel.codefight.model.objects.Phase;
import java.util.ArrayList;
import java.util.List;


/**
 * This command ends current game.
 *
 * @author uxtdn
 */
public class EndGameCommand implements Command {

    private static final String NOT_RIGHT_GAMEPHASE = "not right gamephase.";
    private static final String STR = "Running AIs: ";
    private static final String DELIMITER = ", ";
    private static final String STOPPED_AI = "Stopped AIs: ";
    private static final String ENTER = "\n";


    @Override
    public CommandResult execute(CodeFight model, String[] commandArguments) {
        if (model.getCurrentPhase() == Phase.BEFOREGAME) {
            return new CommandResult(CommandResultType.FAILURE, NOT_RIGHT_GAMEPHASE);
        }
        model.setCurrentPhase(Phase.BEFOREGAME);

        List<String> runningAINames = new ArrayList<>();
        List<String> stoppedAINames = new ArrayList<>();
        for (AIobject ai : model.getAIs()) {
            if (ai.getRunning()) {
                runningAINames.add(ai.getName());
            } else {
                stoppedAINames.add(ai.getName());
            }
        }


        //builts result
        StringBuilder resultBuilder = new StringBuilder();
        if (!runningAINames.isEmpty()) {
            resultBuilder.append(STR).append(String.join(DELIMITER, runningAINames));
        }
        if (!stoppedAINames.isEmpty()) {
            if (!resultBuilder.isEmpty()) {
                resultBuilder.append(ENTER);
            }
            resultBuilder.append(STOPPED_AI).append(String.join(DELIMITER, stoppedAINames));
        }
        String resultMessage = resultBuilder.toString();

        model.reset();

        return new CommandResult(CommandResultType.SUCCESS, resultMessage);
    }

    @Override
    public int getNumberOfArguments() {
        return 0;
    }
}
