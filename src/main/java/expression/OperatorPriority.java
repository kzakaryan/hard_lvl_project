package expression;

/**
 * Determines the priority of mathematical operators.
 */
public class OperatorPriority {

    /**
     * Gets the priority of an operator.
     *
     * @param operator the operator symbol.
     * @return the priority of the operator.
     */
    public int getPriority(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return -1;
        }
    }
}