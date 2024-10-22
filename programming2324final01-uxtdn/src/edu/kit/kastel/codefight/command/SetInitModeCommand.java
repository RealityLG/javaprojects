package edu.kit.kastel.codefight.command;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.InitMode;
import edu.kit.kastel.codefight.model.objects.Phase;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfield;

import java.util.Objects;

/**
 * This command sets the init mode for the playfield.
 *
 * @author uxtdn
 */
public class SetInitModeCommand implements Command {
    private static final String INVALID_COMMAND = "Invalid Command";
    private static final String INVALID_NUMBER = "Invalid number";
    private static final int MAXIMUM_ARGUMENT_NUMBER = 2;
    private static final int MINIMUM_ARGUMENT_NUMBER = 1;
    private static final int SEED_NUMBER_COMMAND = 1;
    private static final int MAXIMUM_SEED_NUMBER = 1337;
    private static final int MINIMUM_SEED_NUMBER = -1337;
    private static final int FIRST_INIT_COMMAND = 0;
    private static final String STRING = "Changed init mode from ";
    private static final String CANNOT_EXECUTE_COMMAND = "Cannot execute command";
    private static final String TO = " to ";
    private static final String NO_NUMBER = "no number";
    private static final String SPACE = " ";


    @Override
    public CommandResult execute(CodeFight model, String[] commandArguments) {
        if (model.getCurrentPhase() != Phase.BEFOREGAME) {
            return new CommandResult(CommandResultType.FAILURE, CANNOT_EXECUTE_COMMAND);
        }
        if (commandArguments.length > MAXIMUM_ARGUMENT_NUMBER || commandArguments.length < MINIMUM_ARGUMENT_NUMBER) {
            return new CommandResult(CommandResultType.FAILURE, INVALID_COMMAND);
        }
        if (Objects.equals(commandArguments[FIRST_INIT_COMMAND], InitMode.STRING) && commandArguments.length != 1) {
            return new CommandResult(CommandResultType.FAILURE, INVALID_COMMAND);
        }
        if (!Objects.equals(commandArguments[FIRST_INIT_COMMAND], InitMode.STRING)
                && !Objects.equals(commandArguments[FIRST_INIT_COMMAND], Playfield.STRING)) {
            return new CommandResult(CommandResultType.FAILURE, INVALID_COMMAND);
        }
        String oldInit;
        //old initmode
        if (Objects.equals(model.getInitMode().getInit(), InitMode.STRING)) {
            oldInit = model.getInitMode().getInit();
        } else {
            oldInit = model.getInitMode().getInit() + SPACE + model.getInitMode().getSeed();
        }
        //checking if Init mode really changed
        if (Objects.equals(commandArguments[FIRST_INIT_COMMAND], model.getInitMode().getInit())
                && InitMode.STRING.equals(commandArguments[FIRST_INIT_COMMAND])) {
            return new CommandResult(CommandResultType.SUCCESS, null);
        } else if (Objects.equals(commandArguments[FIRST_INIT_COMMAND], model.getInitMode().getInit())
                && Integer.parseInt(commandArguments[SEED_NUMBER_COMMAND]) == model.getInitMode().getSeed()) {
            return new CommandResult(CommandResultType.SUCCESS, null);
        }
        //setting Original Init Mode
        InitMode initMode = model.getInitMode();

        if (Objects.equals(commandArguments[FIRST_INIT_COMMAND], InitMode.STRING)) {
            String mode = commandArguments[FIRST_INIT_COMMAND];
            initMode.setInitMode(mode, 0);
            return new CommandResult(CommandResultType.SUCCESS, STRING
                    + oldInit + TO + model.getInitMode().getInit());
        }
        if (Objects.equals(commandArguments[FIRST_INIT_COMMAND], Playfield.STRING)
                && commandArguments.length != MAXIMUM_ARGUMENT_NUMBER) {
            return new CommandResult(CommandResultType.FAILURE, INVALID_COMMAND);
        }
        if (!isNumber(commandArguments[1]) && Objects.equals(commandArguments[FIRST_INIT_COMMAND], Playfield.STRING)) {
            return new CommandResult(CommandResultType.FAILURE, NO_NUMBER);
        }
        if (Integer.parseInt(commandArguments[SEED_NUMBER_COMMAND]) > MAXIMUM_SEED_NUMBER
                || Integer.parseInt(commandArguments[SEED_NUMBER_COMMAND]) < MINIMUM_SEED_NUMBER) {
            return new CommandResult(CommandResultType.FAILURE, INVALID_NUMBER);
        }
        String mode = commandArguments[FIRST_INIT_COMMAND];
        initMode.setInitMode(mode, Integer.parseInt(commandArguments[SEED_NUMBER_COMMAND]));
        return new CommandResult(CommandResultType.SUCCESS, STRING + oldInit + TO
                + model.getInitMode().getInit() + SPACE + model.getInitMode().getSeed());
    }

    @Override
    public int getNumberOfArguments() {
        return MAXIMUM_ARGUMENT_NUMBER;
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


