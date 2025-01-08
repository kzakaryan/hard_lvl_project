package expression;

import static org.junit.jupiter.api.Assertions.*;
import model.VariableStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.mockito.Mockito.*;

/**
 * Coverage 100%
 */
class ExpressionEvaluatorTest {

    private VariableStorage variableStorage;
    private ExpressionEvaluator evaluator;

    @BeforeEach
    void setUp() {
        variableStorage = mock(VariableStorage.class);
        evaluator = new ExpressionEvaluator(variableStorage);
    }

    @Test
    void testProcessEquationValid() {
        String equation = "x = 3 + 5 * 2";
        BigDecimal expected = new BigDecimal("13");
        evaluator.processEquation(equation);
        verify(variableStorage).storeVariable("x", expected);
    }

    @Test
    void testEvaluateValidExpression() {
        String expression = "3 + 5 * 2";
        BigDecimal expected = new BigDecimal("13");
        BigDecimal result = evaluator.evaluate(expression);
        assertEquals(expected, result);
    }

    @Test
    void testEvaluateExpressionWithParentheses() {
        String expression = "(3 + 5) * 2";
        BigDecimal expected = new BigDecimal("16");
        BigDecimal result = evaluator.evaluate(expression);
        assertEquals(expected, result);
    }

    @Test
    void testEvaluateExpressionWithDivision() {
        String expression = "10 / 2 + 3";
        BigDecimal expected = new BigDecimal("8");
        BigDecimal result = evaluator.evaluate(expression);
        assertEquals(expected, result);
    }

    @Test
    void testEvaluateExpressionWithExponentiation() {
        String expression = "2 ^ 3 + 4";
        BigDecimal expected = new BigDecimal("12");
        BigDecimal result = evaluator.evaluate(expression);
        assertEquals(expected, result);
    }

    @Test
    void testEvaluateInvalidExpression() {
        String expression = "3 + * 5";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> evaluator.evaluate(expression));
        assertEquals("Invalid expression: not enough operands for operator +", exception.getMessage());
    }

    @Test
    void testProcessEquationWithInvalidExpression() {
        String equation = "y = 3 + * 5";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> evaluator.processEquation(equation));
        assertEquals("Invalid expression: not enough operands for operator +", exception.getMessage());
    }

    @Test
    void testProcessEquationWithStoredResult() {
        String equation = "z = 3 + 5";
        BigDecimal expected = new BigDecimal("8");
        evaluator.processEquation(equation);
        verify(variableStorage).storeVariable("z", expected);
    }

    @Test
    void testEvaluateExpressionWithNegativeResult() {
        String expression = "5 - 10";
        BigDecimal expected = new BigDecimal("-5");
        BigDecimal result = evaluator.evaluate(expression);
        assertEquals(expected, result);
    }
}