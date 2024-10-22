package edu.kit.kastel.codefight.command;

import edu.kit.kastel.codefight.codefight.CodeFight;
import edu.kit.kastel.codefight.model.objects.Phase;

/**
 * This command helps player with other commands.
 *
 * @author uxtdn
 */
final class HelpCommand implements Command {

    private static final String BEFOREGAME = """
            add-ai: Adds a AI, first argument AI-name, second command-arguments.
            help: Lookup for valid game commands.
            quit: Ends the game.
            remove-ai: Removes AI with its commands from the game.
            set-init-mode: set-init-mode [Mode] [Optional Parameters].
            start-game: Starts the game, with the AIs that will play the game specified after the command.""";
    private static final String INGAME = """
            end-game: Ends the game and shows which AIs have stopped and which ones are still ongoing.
            help: Lookup for valid game commands.
            next: next [Number of AI commands] for the next AI commands.
            quit: Ends the program.
            show-ai: show-ai [Name of the AI] shows if AI has stopped of is playing.
            show-memory: Shows current state of playfield.""";

    @Override
    public CommandResult execute(CodeFight model, String[] commandArguments) {
        String helpMessage;
        if (model.getCurrentPhase() == Phase.BEFOREGAME) {
            helpMessage = BEFOREGAME;
        } else {
            helpMessage = INGAME;
        }

        return new CommandResult(CommandResultType.SUCCESS, helpMessage);
    }

    @Override
    public int getNumberOfArguments() {
        return 0;
    }
}
