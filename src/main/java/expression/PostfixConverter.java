package expression;

import java.math.*;
import java.util.*;

/**
 * Converts infix expressions to postfix notation and calculates the result.
 */
public class PostfixConverter {

    private final OperatorPriority operatorPriority = new OperatorPriority();

    /**
     * Converts an infix expression to postfix notation.
     *
     * @param expression the infix expression to convert.
     * @return the postfix expression as a list of strings.
     */
    public List<String> convertToPostfix(String expression) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (Character.isDigit(currentChar)) {
                StringBuilder number = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    number.append(expression.charAt(i));
                    i++;
                }
                result.add(number.toString());
                i--;
            } else if (currentChar == '(') {
                stack.push(String.valueOf(currentChar));
            } else if (currentChar == ')') {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    result.add(stack.pop());
                }
                stack.pop();
            } else if ("+-*/^".indexOf(currentChar) >= 0) {
                while (!stack.isEmpty() && operatorPriority.getPriority(String.valueOf(currentChar)) <= operatorPriority.getPriority(stack.peek())) {
                    result.add(stack.pop());
                }
                stack.push(String.valueOf(currentChar));
            }
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    /**
     * Calculates the result of a postfix expression.
     *
     * @param postfix the postfix expression to calculate.
     * @return the result of the calculation.
     */
    public BigDecimal calculatePostfix(List<String> postfix) {
        ArrayDeque<BigDecimal> stack = new ArrayDeque<>();

        for (String token : postfix) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                stack.push(new BigDecimal(token));
            } else {
                BigDecimal b = stack.pop();
                BigDecimal a = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(a.add(b));
                        break;
                    case "-":
                        stack.push(a.subtract(b));
                        break;
                    case "*":
                        stack.push(a.multiply(b));
                        break;
                    case "/":
                        stack.push(a.divide(b, RoundingMode.CEILING));
                        break;
                    case "^":
                        stack.push(a.pow(b.intValue()));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operator: " + token);
                }
            }
        }

        return stack.pop();
    }
}