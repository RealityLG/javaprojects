package edu.kit.kastel.codefight.model.objects.aimap;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a map containing AI mappings.
 *
 * @author uxtdn
 */
public class AIMAP {
    private final Map<String, AICommandObject[]> aiMap;

    /**
     * Constructs an AIMAP object.
     */
    public AIMAP() {
        aiMap = new HashMap<>();
    }

    /**
     * Adds an AI with its corresponding command objects to the map.
     *
     * @param name      The name of the AI.
     * @param aiObjects The array of AI command objects associated with the AI.
     */
    public void addAI(String name, AICommandObject[] aiObjects) {
        aiMap.put(name, aiObjects);
    }

    /**
     * Removes an AI from the map.
     *
     * @param name The name of the AI to be removed.
     */
    public void removeAI(String name) {
        aiMap.remove(name);
    }


    /**
     * Checks if the map contains an AI with the given name.
     *
     * @param name The name of the AI to check for.
     * @return True if the map contains the AI, otherwise false.
     */
    public boolean contains(String name) {
        return aiMap.containsKey(name);
    }


    /**
     * Retrieves the array of AI command objects associated with the given AI name.
     *
     * @param name The name of the AI.
     * @return The array of AI command objects associated with the AI.
     */
    public AICommandObject[] get(String name) {
        return aiMap.get(name);
    }

}
