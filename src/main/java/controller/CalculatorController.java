package controller;

import expression.ExpressionEvaluator;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import model.VariableStorage;
import org.slf4j.*;
import java.math.BigDecimal;
import java.util.Scanner;


/**
 * Controller class to manage user input, output, and execution of commands.
 * Handles the interaction between the user and the calculator system.
 */
@RequiredArgsConstructor
public class CalculatorController {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);
    private final VariableStorage variableStorage;
    private final ExpressionEvaluator evaluator;

    public CalculatorController() {
        this.variableStorage = new VariableStorage();
        this.evaluator = new ExpressionEvaluator(variableStorage);
    }

    /**
     * Main program loop that continuously processes user commands.
     */
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            String input;
            while (true) {
                System.out.print("Enter command: ");
                input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("/exit")) {
                    logger.info("Exiting the calculator.");
                    break;
                }
                processInput(input);
            }
        } catch (Exception e) {
            logger.error("An unexpected error occurred: ", e);
        }
    }

    private void processInput(String input) {
        try {
            if (input.contains("=")) {
                evaluator.processEquation(input);
            } else {
                BigDecimal result = evaluator.evaluate(input);
                System.out.println(result);
            }
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid input: " + input);
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error processing input: ", e);
            System.out.println("An unexpected error occurred.");
        }
    }
}