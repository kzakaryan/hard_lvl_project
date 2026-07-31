package model;

import java.math.BigDecimal;
import java.util.*;

/**
 * Responsible for storing and managing variables used in calculations.
 */
public class VariableStorage {

    private final Map<String, BigDecimal> variables = new HashMap<>();

    /**
     * Stores a variable and its corresponding value.
     *
     * @param variableName the name of the variable.
     * @param value the value of the variable.
     */
    public void storeVariable(String variableName, BigDecimal value) {
        variables.put(variableName, value);
    }

}