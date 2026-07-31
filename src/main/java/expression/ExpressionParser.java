package expression;

import model.VariableStorage;

/**
 * Class to parse expressions, handle variables, and clean the input.
 */
public class ExpressionParser {

    private final VariableStorage variableStorage;

    public ExpressionParser(VariableStorage variableStorage) {
        this.variableStorage = variableStorage;
    }

    public String clean(String expression) {
        return expression.replaceAll("\\s+", "")
                .replaceAll("(--)+", "+")
                .replaceAll("\\++", "+")
                .replaceAll("(\\+-)+", "-")
                .replaceAll("(-\\+)+", "-");
    }
}