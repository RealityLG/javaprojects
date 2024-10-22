/*
 * Copyright (c) 2024, KASTEL. All rights reserved.
 */

package edu.kit.kastel.codefight.command;

import edu.kit.kastel.codefight.codefight.CodeFight;

/**
 * This edu.kit.kastel.codefight.command quits a {@link CommandHandler edu.kit.kastel.codefight.command handler}.
 *
 * @author Programmieren-Team
 */
final class QuitCommand implements Command {

    private final CommandHandler commandHandler;

    /**
     * Constructs a new QuitCommand.
     * 
     * @param commandHandler the edu.kit.kastel.codefight.command handler to be quitted
     */
    QuitCommand(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public CommandResult execute(CodeFight model, String[] commandArguments) {
        commandHandler.quit();
        return new CommandResult(CommandResultType.SUCCESS, null);
    }


    @Override
    public int getNumberOfArguments() {
        return 0;
    }
}
