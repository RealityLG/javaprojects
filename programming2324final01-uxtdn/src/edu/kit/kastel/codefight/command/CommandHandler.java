/*
 * Copyright (c) 2024, KASTEL. All rights reserved.
 */

package edu.kit.kastel.codefight.command;

import edu.kit.kastel.codefight.codefight.CodeFight;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Arrays;

/**
 * This class handles the user input and executes the commands.
 * 
 * @author Programmieren-Team
 */
public final class CommandHandler {
    private static final String COMMAND_SEPARATOR_REGEX = " +";
    private static final String ERROR_PREFIX = "Error, ";
    private static final String COMMAND_NOT_FOUND_FORMAT = "edu.kit.kastel.codefight.command '%s' not found!";
    private static final String WRONG_ARGUMENTS_COUNT_FORMAT = "wrong number of arguments for edu.kit.kastel.codefight.command '%s'!";
    private static final String PRINT_COMMAND_NAME = "show-memory";
    private static final String QUIT_COMMAND_NAME = "quit";
    private static final String SET_INIT_MODE = "set-init-mode";
    private static final String ADD_AI_COMMAND_NAME = "add-ai";
    private static final String START_COMMAND_NAME = "start-game";

    private static final String HELP_COMMAND_NAME = "help";
    private static final String INVALID_RESULT_TYPE_FORMAT = "Unexpected value: %s";
    private static final String REMOVE_COMMAND_NAME = "remove-ai";
    private static final String SHOWAI_COMMAND_NAME = "show-ai";
    private static final String ENDGAME_COMMAND_NAME = "end-game";
    private static final String NEXT_COMMAND_NAME = "next";

    private final CodeFight codefight;
    private final Map<String, Command> commands;
    private boolean running = false;

    /**
     * Constructs a new CommandHandler.
     *
     * @param codefight the codefight game that this instance manages
     */
    public CommandHandler(CodeFight codefight) {
        this.codefight = Objects.requireNonNull(codefight);
        this.commands = new HashMap<>();
        this.initCommands();
    }

    /**
     * Starts the interaction with the user.
     */
    public void handleUserInput() {
        this.running = true;

        try (Scanner scanner = new Scanner(System.in)) {
            while (running && scanner.hasNextLine()) {
                executeCommand(scanner.nextLine());
            }
        }
    }

    /**
     * Quits the interaction  with the user.
     */
    public void quit() {
        this.running = false;
    }

    private void executeCommand(String commandWithArguments) {
        String[] splittedCommand = commandWithArguments.trim().split(COMMAND_SEPARATOR_REGEX);
        String commandName = splittedCommand[0];
        String[] commandArguments = Arrays.copyOfRange(splittedCommand, 1, splittedCommand.length);

        executeCommand(commandName, commandArguments);
    }

    private void executeCommand(String commandName, String[] commandArguments) {
        if (!commands.containsKey(commandName)) {
            System.err.println(ERROR_PREFIX + COMMAND_NOT_FOUND_FORMAT.formatted(commandName));
        } else if (commands.get(commandName).getNumberOfArguments() < commandArguments.length) {
            System.err.println(ERROR_PREFIX + WRONG_ARGUMENTS_COUNT_FORMAT.formatted(commandName));
        } else {
            CommandResult result = commands.get(commandName).execute(codefight, commandArguments);
            String output = switch (result.getType()) {
                case SUCCESS -> result.getMessage();
                case FAILURE -> ERROR_PREFIX + result.getMessage();
            };
            if (output != null) {
                switch (result.getType()) {
                    case SUCCESS -> System.out.println(output);
                    case FAILURE -> System.err.println(output);
                    default -> throw new IllegalStateException(INVALID_RESULT_TYPE_FORMAT.formatted(result.getType()));
                }
            }
        }
    }

    private void initCommands() {
        this.addCommand(ADD_AI_COMMAND_NAME, new AddAICommand());
        this.addCommand(REMOVE_COMMAND_NAME, new RemoveAICommand());
        this.addCommand(PRINT_COMMAND_NAME, new Showmemory());
        this.addCommand(QUIT_COMMAND_NAME, new QuitCommand(this));
        this.addCommand(HELP_COMMAND_NAME, new HelpCommand());
        this.addCommand(SET_INIT_MODE, new SetInitModeCommand());
        this.addCommand(START_COMMAND_NAME, new StartGameCommand());
        this.addCommand(SHOWAI_COMMAND_NAME, new ShowAICommand());
        this.addCommand(ENDGAME_COMMAND_NAME, new EndGameCommand());
        this.addCommand(NEXT_COMMAND_NAME, new NextCommand());
    }

    private void addCommand(String commandName, Command command) {
        this.commands.put(commandName, command);
    }
}
