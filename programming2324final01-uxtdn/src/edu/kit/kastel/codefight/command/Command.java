/*
 * Copyright (c) 2024, KASTEL. All rights reserved.
 */

package edu.kit.kastel.codefight.command;

import edu.kit.kastel.codefight.codefight.CodeFight;

/**
 * This interface represents an executable edu.kit.kastel.codefight.command.
 *
 * @author Programmieren-Team
 */
public interface Command {



    /**
     * Executes the edu.kit.kastel.codefight.command.
     *
     * @param model            the model to execute the edu.kit.kastel.codefight.command on
     * @param commandArguments the arguments of the edu.kit.kastel.codefight.command
     * @return the result of the edu.kit.kastel.codefight.command
     */
    CommandResult execute(CodeFight model, String[] commandArguments);




    /**
     * Returns the number of arguments that the edu.kit.kastel.codefight.command expects.
     * 
     * @return the number of arguments that the edu.kit.kastel.codefight.command expects
     */
    int getNumberOfArguments();
}
