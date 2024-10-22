/*
 * Copyright (c) 2024, KASTEL. All rights reserved.
 */

package edu.kit.kastel.codefight.command;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.Phase;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfield;
import edu.kit.kastel.codefight.model.objects.playfieldobject.PlayfieldStringCombiner;
import edu.kit.kastel.codefight.model.objects.playfieldobject.Playfieldobject;

/**
 * This command show current playfield.
 *
 * @author uxtdn
 */
final class Showmemory implements Command {


    public static final String PHASE_NOT_READY = "Phase not ready";
    private static final int NUMBER_OF_ARGUMENTS = 1;
    private static final String PLAYFIELD_DOES_NOT_EXIST = "Playfield does not exist.";
    private static final String INVALID_COMMAND = "Invalid Command.";
    private static final String INVALID_NUMBER = "Invalid Number.";
    private static final String PERCENT = "%";
    private static final String S = "s";
    private static final String STRING = " | ";
    private static final String DOUBLEPOINT = ": ";
    private static final int TEN = 10;
    private static final int NINE = 9;
    private static final int FOUR = 4;
    private static final int THREE = 3;
    private static final String SPACE = " ";


    @Override
    public CommandResult execute(CodeFight model, String[] commandArguments) {
        if (model.getCurrentPhase() != Phase.GAME) {
            return new CommandResult(CommandResultType.FAILURE, PHASE_NOT_READY);
        }
        if (model.getPlayfield() == null) {
            return new CommandResult(CommandResultType.FAILURE, PLAYFIELD_DOES_NOT_EXIST);
        }

        Playfield originalPlayfield = model.getPlayfield();
        String bereichsgrenze = model.getBordersymbol();
        Playfieldobject[] field = originalPlayfield.getPlayfield();

        if (commandArguments.length == 1) {
            if (!isNumber(commandArguments[0])) {
                return new CommandResult(CommandResultType.FAILURE, INVALID_COMMAND);
            }
            if (Integer.parseInt(commandArguments[0]) >= model.getSizePlayfield() || Integer.parseInt(commandArguments[0]) < 0) {
                return new CommandResult(CommandResultType.FAILURE, INVALID_NUMBER);
            }

            int startCell = (Integer.parseInt(commandArguments[0]) + model.getSizePlayfield()) % model.getSizePlayfield();
            int endCell = (startCell - 1 + model.getSizePlayfield()) % model.getSizePlayfield();

            if (model.getSizePlayfield() >= TEN && endCell < 0) {
                endCell = model.getSizePlayfield() - 1;
            }
            if (model.getSizePlayfield() > TEN) {
                endCell = (startCell + NINE) % model.getSizePlayfield();
            }

            int[] columnWidths = getColumnWidths(field, startCell);
            StringBuilder sb = new StringBuilder();
            buildPlayfieldRow(sb, field, originalPlayfield.getPlayfieldsize(), startCell, endCell, bereichsgrenze);
            System.out.println(sb);

            if (model.getSizePlayfield() < TEN) {
                for (int i = 0; i < model.getSizePlayfield(); i++) {
                    int cell = (i + startCell) % model.getSizePlayfield();
                    String message = getString(field, cell, columnWidths);
                    System.out.println(message);
                }
            } else {
                for (int i = 0; i < TEN; i++) {
                    int cell = (i + startCell) % model.getSizePlayfield();
                    String message = getString(field, cell, columnWidths);
                    System.out.println(message);
                }
            }
        } else {
            PlayfieldStringCombiner combiner = new PlayfieldStringCombiner();
            String message = combiner.convertToString(field);
            System.out.println(message);
        }
        return new CommandResult(CommandResultType.SUCCESS, null);
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

    private void buildPlayfieldRow(StringBuilder sb, Playfieldobject[] field,
                                   int playfieldSize, int startCell, int endCell, String borderSymbol) {
        for (int i = 0; i < playfieldSize; i++) {
            if (i == startCell) {
                sb.append(borderSymbol);
                sb.append(field[i].getAISymbol());
            } else if (i == endCell) {
                sb.append(field[i].getAISymbol());
                sb.append(borderSymbol);
            } else {
                sb.append(field[i].getAISymbol());
            }
        }
    }

    private static int[] getColumnWidths(Playfieldobject[] field, int startCell) {
        int[] columnWidths = new int[NINE];
        for (int i = 0; i < TEN; i++) {
            int cell = (i + startCell) % field.length;
            columnWidths[0] = Math.max(columnWidths[0], field[cell].getAISymbol().length());
            columnWidths[1] = Math.max(columnWidths[1], String.valueOf(cell).length());
            columnWidths[2] = Math.max(columnWidths[2], field[cell].getAICommand().length());
            columnWidths[THREE] = Math.max(columnWidths[THREE], String.valueOf(field[cell].getArgumentA()).length());
            columnWidths[FOUR] = Math.max(columnWidths[FOUR], String.valueOf(field[cell].getArgumentB()).length());
        }
        return columnWidths;
    }

    private static String getString(Playfieldobject[] field, int cell, int[] columnWidths) {
        String symbol = field[cell].getAISymbol();
        String position = String.format(PERCENT + columnWidths[1] + S, cell);
        String command = String.format(PERCENT + columnWidths[2] + S, field[cell].getAICommand());
        String argumenta = String.format(PERCENT + columnWidths[THREE] + S, field[cell].getArgumentA());
        String argumentb = String.format(PERCENT + columnWidths[FOUR] + S, field[cell].getArgumentB());
        return symbol + SPACE + position + DOUBLEPOINT + command + STRING + argumenta + STRING + argumentb;
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
