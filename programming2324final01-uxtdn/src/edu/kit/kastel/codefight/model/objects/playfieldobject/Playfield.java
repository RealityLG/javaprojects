package edu.kit.kastel.codefight.model.objects.playfieldobject;

import edu.kit.kastel.codefight.model.objects.AIGameCommands;
import edu.kit.kastel.codefight.model.objects.InitMode;
import edu.kit.kastel.codefight.model.objects.aimap.AICommandObject;
import edu.kit.kastel.codefight.model.objects.aimap.AIMAP;
import edu.kit.kastel.codefight.model.objects.ais.AIobject;

import java.util.Objects;
import java.util.Random;

/**
 * Represents the playfield in the game, containing playfield cells with AI commands.
 * And the playfield size.
 *
 * @author uxtdn
 * @version 1.0
 */
public class Playfield {

    /**
     * STRING for INIT MODE.
     */
    public static final String STRING = "INIT_MODE_RANDOM";
    private static final String STOP = "STOP";
    private static final int NUMBER_OF_AICOMMANDS = 9;

    private static final String INIT_MODE_STOP = InitMode.STRING;

    private final int playfieldsize;

    private Playfieldobject[] playfield;

    /**
     * Constructs a Playfield object with the specified size.
     *
     * @param playfieldsize the size of the playfield
     */
    public Playfield(int playfieldsize) {
        this.playfieldsize = playfieldsize;
        this.playfield = new Playfieldobject[playfieldsize];
    }

    /**
     * Retrieves the playfield cells.
     *
     * @return the playfield cells
     */
    public Playfieldobject[] getPlayfield() {
        return playfield.clone();
    }

    /**
     * Retrieves a specific playfield cell.
     *
     * @param playfield the array of playfield cells
     * @param position the position of the cell to retrieve
     * @return the playfield cell at the specified position
     */
    public Playfieldobject getPlayfieldcell(Playfieldobject[] playfield, int position) {
        return playfield[position];
    }

    /**
     * Retrieves the size of the playfield.
     *
     * @return the size of the playfield
     */
    public int getPlayfieldsize() {
        return playfieldsize;
    }

    /**
     * Sets the playfield cells.
     *
     * @param playfield the playfield cells to set
     */
    public void setPlayfield(Playfieldobject[] playfield) {
        this.playfield = playfield.clone();
    }


    /**
     * Fills the playfield with initial values based on the specified mode.
     *
     * @param initmode the initialization mode (INIT_MODE_STOP or INIT_MODE_RANDOM)
     * @param sizePlayfield the size of the playfield
     * @param startsymbol the starting symbol for the playfield cells
     * @param seed the seed for random initialization (used if initmode is "INIT_MODE_RANDOM")
     * @return the filled playfield
     */
    public Playfieldobject[] fillplayfield(String initmode, int sizePlayfield, String startsymbol, long seed) {
        Playfieldobject[] fillplayfield = new Playfieldobject[sizePlayfield];

        if (Objects.equals(initmode, INIT_MODE_STOP)) {
            for (int i = 0; i < sizePlayfield; i++) {
                fillplayfield[i] = new Playfieldobject(startsymbol, STOP, 0, 0);
            }
        }
        // for INIT MODE RANDOM
        if (Objects.equals(initmode, STRING)) {
            Random random = new Random(seed);
            AIGameCommands aiCommands = new AIGameCommands();
            String[] commands = aiCommands.getAIGameCommands();
            for (int i = 0; i < sizePlayfield; i++) {
                int randomIndex = random.nextInt(NUMBER_OF_AICOMMANDS);
                String aicommand = commands[randomIndex];
                int argumentA = random.nextInt();
                int argumentB = random.nextInt();

                fillplayfield[i] = new Playfieldobject(startsymbol, aicommand, argumentA, argumentB);

            }
        }
        return fillplayfield;
    }

    /**
     * Fills the playfield cells with AI commands based on the provided data.
     *
     * @param nextsymbol the symbol for the next AI command
     * @param nextaisymbol the symbol for the next AI command from another AI
     * @param playfield the array representing the playfield
     * @param aisnames the names of the AIs
     * @param aimap the mapping of AI names to commands
     * @param ais the AI objects
     * @return the playfield with AI commands
     */
    public Playfieldobject[] aiplayfield(String nextsymbol, String nextaisymbol, Playfieldobject[] playfield, String[] aisnames,
                                         AIMAP aimap, AIobject[] ais) {
        for (int i = 0; i < ais.length; i++) {
            String name = aisnames[i];
            AICommandObject[] command = aimap.get(name);
            int position = ais[i].getPosition();
            int length = command.length;
            for (int x = 0; x < length; x++) {
                playfield[x + position].setPlayfieldobject(ais[i].getSymbol(), command[x].getCommand(),
                            command[x].getArgumentA(), command[x].getArgumentB());
                playfield[x + position].setlastCellBuilder(ais[i].getSymbol());
            }
            for (int x = 0; x < length; x++) {
                if (position == 0 && !Objects.equals(command[x].getCommand(), STOP)) {
                    playfield[x].setPlayfieldobject(nextsymbol, command[x].getCommand(),
                            command[x].getArgumentA(), command[x].getArgumentB());
                    ais[i].setPosition(position + x);
                    break;
                } else if (position != 0 && !Objects.equals(command[x].getCommand(), STOP)) {
                    playfield[position + x].setSymbol(nextaisymbol);
                    ais[i].setPosition(position + x);
                    break;
                }
            }
        }
        return playfield;
    }

    /**
     * Sets the specified playfield cell with the provided playfield object.
     *
     * @param cell the index of the playfield cell
     * @param playfieldobject the playfield object to set
     */
    public void setPlayfieldCell(int cell, Playfieldobject playfieldobject) {
        playfield[cell].setSymbol(playfieldobject.getAISymbol());
        playfield[cell].setArgumentA(playfieldobject.getArgumentA());
        playfield[cell].setArgumentB(playfieldobject.getArgumentB());
        playfield[cell].setAICommand(playfieldobject.getAICommand());
        playfield[cell].setlastCellBuilder(playfieldobject.getLastCellBuilder());
    }

    /**
     * Sets the symbol of the specified playfield cell.
     *
     * @param cell the index of the playfield cell
     * @param playfield the playfield object containing the cells
     * @param symbol the symbol to set
     */
    public void setPlayfieldCellSymbol(int cell, Playfield playfield, String symbol) {
        Playfieldobject[] playfieldobjects = playfield.getPlayfield();
        playfieldobjects[cell].setSymbol(symbol);
    }

    /**
     *  lol.
     * @param cell is cell.
     * @param playfield is playfield.
     * @param symbol is symbol.
     */
    public void setlastCellbuilder(int cell, Playfield playfield, String symbol) {
        Playfieldobject[] playfieldobjects = playfield.getPlayfield();
        playfieldobjects[cell].setlastCellBuilder(symbol);
    }
}
