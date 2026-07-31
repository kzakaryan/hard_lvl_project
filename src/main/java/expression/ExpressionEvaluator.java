package expression;

import model.VariableStorage;
import org.slf4j.*;
import java.math.BigDecimal;

/**
 * Responsible for evaluating mathematical expressions and handling equations.
 */
public class ExpressionEvaluator {

    private static final Logger logger = LoggerFactory.getLogger(ExpressionEvaluator.class);
    private final VariableStorage variableStorage;
    private final PostfixConverter postfixConverter;
    private final ExpressionParser expressionParser;

    public ExpressionEvaluator(VariableStorage variableStorage) {
        this.variableStorage = variableStorage;
        this.postfixConverter = new PostfixConverter();
        this.expressionParser = new ExpressionParser(variableStorage);
    }

    /**
     * Processes an equation by storing the result in memory.
     *
     * @param equation the equation to process.
     */
    public void processEquation(String equation) {
        String[] parts = equation.split("=");
        String variable = parts[0].trim();
        String expression = parts[1].trim();

        BigDecimal result = evaluate(expression);
        variableStorage.storeVariable(variable, result);
        logger.info("Stored variable: {} = {}", variable, result);
    }

    /**
     * Evaluates a mathematical expression and returns the result.
     *
     * @param expression the mathematical expression.
     * @return the evaluated result as a string.
     */
    public BigDecimal evaluate(String expression) {
        try {
            String cleanedExpression = expressionParser.clean(expression);
            BigDecimal result = postfixConverter.calculatePostfix(postfixConverter.convertToPostfix(cleanedExpression));
            return result;
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid expression: {}", expression);
            throw new IllegalArgumentException("Invalid expression.");
        }
    }
}
