package launcher;

import controller.CalculatorController;

/**
 * Main entry point for the calculator application.
 * Starts the calculator and manages user input and command processing.
 */
public class CalculatorApp {

    /**
     * Starts the calculator program.
     */
    public static void main(String[] args) {
        CalculatorController controller = new CalculatorController();
        controller.run();
    }
}