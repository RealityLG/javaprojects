package edu.kit.kastel.codefight.model.objects.playfieldobject;
/**
 * Combines Playfield objects into a string representation.
 * This class provides a method to convert an array of Playfield objects into a string representation.
 *
 * @author uxtdn
 * @version 1.0
 */
public class PlayfieldStringCombiner {
    /**
     * Converts an array of Playfield objects into a string representation.
     *
     * @param playfield An array of Playfield objects to be converted.
     * @return A string representation of the Playfield objects.
     */
    public String convertToString(Playfieldobject[] playfield) {
        StringBuilder sb = new StringBuilder();
        for (Playfieldobject playfieldobject : playfield) {
            sb.append(playfieldobject.getAISymbol());
        }
        return sb.toString().trim();
    }
}
