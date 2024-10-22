package edu.kit.kastel.codefight;
/*
 * Copyright (c) 2024, KASTEL. All rights reserved.
 */

import edu.kit.kastel.codefight.command.CommandHandler;


import edu.kit.kastel.codefight.codefight.CodeFight;


import java.util.Objects;
import java.util.Optional;

/**
 * This class is the entry point of the program.
 *
 * @author uxtdn
 */
public final class Main {

    private static final int NUMBER_OF_ARGUMENTS = 9;
    private static final int LOWEST_NUMBER_PLAYFIELD = 7;

    private static final int FIRST_FIVE_ARGS = 5;

    private static final int HIGHEST_NUMBER_PLAYFIELD = 1337;
    private static final String UTILITY_CLASS_CONSTRUCTOR_MESSAGE = "Utility classes cannot be instantiated";
    private static final String INVALID_ARGUMENTS_MESSAGE = "Error, invalid edu.kit.kastel.codefight.command line arguments.";
    private static final String WELCOME_TO_CODE_FIGHT_2024_ENTER_HELP_FOR_MORE_DETAILS
            = "Welcome to CodeFight 2024. Enter 'help' for more details.";
    private static final String SPACE = " ";


    private Main() {
        throw new UnsupportedOperationException(UTILITY_CLASS_CONSTRUCTOR_MESSAGE);
    }

    /**
     * Starts the program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Optional<CodeFight> codefight = getCodeFight(args);
        if (codefight.isPresent()) {
            CommandHandler commandHandler = new CommandHandler(codefight.get());
            commandHandler.handleUserInput();
        } else {
            System.err.println(INVALID_ARGUMENTS_MESSAGE);
        }
    }

    private static boolean isNumber(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean doubleArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String name = args[i];
            for (int x = 0; x < args.length; x++) {
                if (i != x && Objects.equals(args[x], name)) {
                    return true;
                }
            }
        }
        return false;
    }
    private static Optional<CodeFight> getCodeFight(String[] args) {
        //args ERROR Handling
        if (args.length < NUMBER_OF_ARGUMENTS) {
            return Optional.empty();
        }
        if (doubleArgs(args)) {
            return Optional.empty();
        }
        if (!isNumber(args[0])) {
            return Optional.empty();
        }
        int sizeplayfield = Integer.parseInt(args[0]);
        if (sizeplayfield < LOWEST_NUMBER_PLAYFIELD || sizeplayfield > HIGHEST_NUMBER_PLAYFIELD) {
            return Optional.empty();
        }
        for (int i = 1; i < args.length; i++) {
            if (args[i].contains(SPACE)) {
                return Optional.empty();
            }
        }

        int ai = args.length - FIRST_FIVE_ARGS;
        if (ai % 2 == 1) {
            return Optional.empty();
        }

        int maxAI = ai / 2;

        String[] symbolAI = new String[ai];
        System.arraycopy(args, FIRST_FIVE_ARGS, symbolAI, 0, args.length - FIRST_FIVE_ARGS);

        String[] symbolPlay = new String[FIRST_FIVE_ARGS];
        System.arraycopy(args, 0, symbolPlay, 0, args.length - ai);

        //Welcome Message
        System.out.println(WELCOME_TO_CODE_FIGHT_2024_ENTER_HELP_FOR_MORE_DETAILS);
        return Optional.of(new CodeFight(sizeplayfield, symbolPlay, symbolAI, maxAI));
    }
}
