package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Coverage 100%
 */
class VariableStorageTest {

    private VariableStorage variableStorage;

    @BeforeEach
    void setUp() {
        variableStorage = new VariableStorage();
    }

    @Test
    void testStoreVariable() {
        String variableName = "x";
        BigDecimal value = new BigDecimal("5");
        variableStorage.storeVariable(variableName, value);
        assertEquals(value, variableStorage.getVariable(variableName), "The variable should be stored with the correct value.");
    }

    @Test
    void testStoreMultipleVariables() {
        String variableName1 = "a";
        BigDecimal value1 = new BigDecimal("10");
        String variableName2 = "b";
        BigDecimal value2 = new BigDecimal("20");
        variableStorage.storeVariable(variableName1, value1);
        variableStorage.storeVariable(variableName2, value2);
        assertEquals(value1, variableStorage.getVariable(variableName1), "The variable 'a' should have the correct value.");
        assertEquals(value2, variableStorage.getVariable(variableName2), "The variable 'b' should have the correct value.");
    }

    @Test
    void testStoreVariableWithZeroValue() {
        String variableName = "y";
        BigDecimal value = BigDecimal.ZERO;
        variableStorage.storeVariable(variableName, value);
        assertEquals(value, variableStorage.getVariable(variableName), "The variable should be stored with a zero value.");
    }

    @Test
    void testStoreVariableWithNegativeValue() {
        String variableName = "z";
        BigDecimal value = new BigDecimal("-7");
        variableStorage.storeVariable(variableName, value);
        assertEquals(value, variableStorage.getVariable(variableName), "The variable should be stored with the correct negative value.");
    }

    @Test
    void testGetVariableThatDoesNotExist() {
        String variableName = "nonExistentVariable";
        assertNull(variableStorage.getVariable(variableName), "The variable should not exist and return null.");
    }

    @Test
    void testContainsVariable() {
        String variableName1 = "a";
        BigDecimal value1 = new BigDecimal("10");
        String variableName2 = "b";
        variableStorage.storeVariable(variableName1, value1);
        assertTrue(variableStorage.containsVariable(variableName1), "Variable 'a' should exist.");
        assertFalse(variableStorage.containsVariable(variableName2), "Variable 'b' should not exist.");
    }
}