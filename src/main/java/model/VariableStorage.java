package model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for storing and managing variables used in calculations.
 */
public class VariableStorage {

    private final Map<String, BigDecimal> variables = new HashMap<>();

    /**
     * Retrieves the value of a stored variable.
     *
     * @param variableName the name of the variable.
     * @return the value of the variable, or null if not found.
     */
    public BigDecimal getVariable(String variableName) {
        return variables.get(variableName);
    }

    /**
     * Stores a variable and its corresponding value.
     *
     * @param variableName the name of the variable.
     * @param value the value of the variable.
     */
    public void storeVariable(String variableName, BigDecimal value) {
        variables.put(variableName, value);
    }

    /**
     * Checks if a variable exists in storage.
     *
     * @param variableName the name of the variable.
     * @return true if the variable exists, otherwise false.
     */
    public boolean containsVariable(String variableName) {
        return variables.containsKey(variableName);
    }
}