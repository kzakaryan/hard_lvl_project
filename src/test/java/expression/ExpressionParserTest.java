package expression;

import static org.junit.jupiter.api.Assertions.*;
import model.VariableStorage;
import org.junit.jupiter.api.Test;

/**
 * Coverage 100%
 */
class ExpressionParserTest {

    private final VariableStorage variableStorage = new VariableStorage(); // Assuming a mock or real VariableStorage object
    private final ExpressionParser parser = new ExpressionParser(variableStorage);

    @Test
    void testCleanSimpleExpression() {
        String expression = "3 + 5";
        String expected = "3+5";
        String result = parser.clean(expression);
        assertEquals(expected, result);
    }

    @Test
    void testCleanExpressionWithSpaces() {
        String expression = " 3  +   5 ";
        String expected = "3+5";
        String result = parser.clean(expression);
        assertEquals(expected, result);
    }

    @Test
    void testCleanExpressionWithMultipleOperators() {
        String expression = "3 ++ 5";
        String expected = "3+5";
        String result = parser.clean(expression);
        assertEquals(expected, result);
    }

    @Test
    void testCleanExpressionWithDoubleNegative() {
        String expression = "3 -- 5";
        String expected = "3+5";
        String result = parser.clean(expression);
        assertEquals(expected, result);
    }

    @Test
    void testCleanExpressionWithMixedSigns() {
        String expression = "3 +- 5";
        String expected = "3-5";
        String result = parser.clean(expression);
        assertEquals(expected, result);
    }

    @Test
    void testCleanExpressionWithNegativeSign() {
        String expression = "3 -+ 5";
        String expected = "3-5";
        String result = parser.clean(expression);
        assertEquals(expected, result);
    }

    @Test
    void testCleanExpressionWithMultipleConsecutiveSigns() {
        String expression = "3 -- ++ +- 5";
        String expected = "3-5";
        String result = parser.clean(expression);
        assertEquals(expected, result);
    }

    @Test
    void testCleanExpressionWithNoChangesNeeded() {
        String expression = "3 + 5 - 2";
        String expected = "3+5-2";
        String result = parser.clean(expression);
        assertEquals(expected, result);
    }

    @Test
    void testCleanEmptyExpression() {
        String expression = "";
        String expected = "";
        String result = parser.clean(expression);
        assertEquals(expected, result);
    }

    @Test
    void testCleanExpressionWithOnlyWhitespaces() {
        String expression = "   ";
        String expected = "";
        String result = parser.clean(expression);
        assertEquals(expected, result);
    }

    @Test
    void testCleanExpressionWithComplexSignMix() {
        String expression = "3 +- +- +- 5";
        String expected = "3-5";
        String result = parser.clean(expression);
        assertEquals(expected, result);
    }

    @Test
    void testCleanExpressionWithOnlyOperators() {
        String expression = "--+--";
        String expected = "+";
        String result = parser.clean(expression);
        assertEquals(expected, result);
    }
}