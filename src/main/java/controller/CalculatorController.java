package controller;

import expression.ExpressionEvaluator;
import model.VariableStorage;
import org.slf4j.*;
import java.math.BigDecimal;
import java.util.Scanner;


/**
 * Controller class to manage user input, output, and execution of commands.
 * Handles the interaction between the user and the calculator system.
 */
public class CalculatorController {

    private Logger logger = LoggerFactory.getLogger(CalculatorController.class);
    private final VariableStorage variableStorage;
    private final ExpressionEvaluator evaluator;
    private final Scanner scanner;

    public CalculatorController() {
        this.variableStorage = new VariableStorage();
        this.evaluator = new ExpressionEvaluator(variableStorage);
        this.scanner = new Scanner(System.in);
    }

    public CalculatorController(VariableStorage variableStorage, ExpressionEvaluator evaluator, Scanner scanner, Logger logger) {
        this.variableStorage = variableStorage;
        this.evaluator = evaluator;
        this.scanner = scanner;
        this.logger = logger;
    }

    /**
     * Main program loop that continuously processes user commands.
     */
    public void run() {
        try {
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