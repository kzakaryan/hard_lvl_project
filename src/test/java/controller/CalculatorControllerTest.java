package controller;

import expression.ExpressionEvaluator;
import model.VariableStorage;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.Scanner;

import static org.mockito.Mockito.*;

/**
 * Coverage 70%
 */
class CalculatorControllerTest {

    @Mock
    private Logger logger;

    @Mock
    private VariableStorage variableStorage;

    @Mock
    private ExpressionEvaluator evaluator;

    @Mock
    private Scanner scanner;

    private CalculatorController calculatorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        calculatorController = new CalculatorController(variableStorage, evaluator, scanner, logger);
    }

    @Test
    void testExitCommand() {
        when(scanner.nextLine()).thenReturn("/exit");
        calculatorController.run();
        verify(logger, times(1)).info("Exiting the calculator.");
    }

    @Test
    void testEvaluateValidExpression() {
        when(scanner.nextLine()).thenReturn("2 + 3", "/exit");
        when(evaluator.evaluate("2 + 3")).thenReturn(BigDecimal.valueOf(5));
        calculatorController.run();
        verify(evaluator, times(1)).evaluate("2 + 3");
        verify(logger, never()).warn(anyString());
    }

    @Test
    void testEvaluateInvalidExpression() {
        when(scanner.nextLine()).thenReturn("3 + * 5", "/exit");
        when(evaluator.evaluate("3 + * 5")).thenThrow(new IllegalArgumentException("Invalid expression"));
        calculatorController.run();
        verify(logger, times(1)).warn("Invalid input: 3 + * 5");
    }

    @Test
    void testProcessEquation() {
        when(scanner.nextLine()).thenReturn("x = 2 + 3", "/exit");
        when(evaluator.evaluate("2 + 3")).thenReturn(BigDecimal.valueOf(5));
        calculatorController.run();
        verify(evaluator, times(1)).processEquation("x = 2 + 3");
    }

    @Test
    void testInvalidEquation() {
        when(scanner.nextLine()).thenReturn("y = 3 + * 5", "/exit");
        doThrow(new IllegalArgumentException("Invalid expression")).when(evaluator).processEquation("y = 3 + * 5");
        calculatorController.run();
        verify(logger, times(1)).warn("Invalid input: y = 3 + * 5");
    }
}