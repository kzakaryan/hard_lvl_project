package expression;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;

/**
 * Coverage 97%
 * Coverage for Operator Priority 100%
 */
class PostfixConverterTest {

    PostfixConverter converter = new PostfixConverter();

    @Test
    void testConvertToPostfixSimpleExpression() {
        String expression = "3 + 5";
        List<String> expected = List.of("3", "5", "+");
        List<String> result = converter.convertToPostfix(expression);
        assertEquals(expected, result);
    }

    @Test
    void testConvertToPostfixComplexExpression() {
        String expression = "3 + 5 * 2";
        List<String> expected = List.of("3", "5", "2", "*", "+");
        List<String> result = converter.convertToPostfix(expression);
        assertEquals(expected, result);
    }

    @Test
    void testConvertToPostfixWithParentheses() {
        String expression = "(3 + 5) * 2";
        List<String> expected = List.of("3", "5", "+", "2", "*");
        List<String> result = converter.convertToPostfix(expression);
        assertEquals(expected, result);
    }

    @Test
    void testConvertToPostfixWithDivision() {
        String expression = "10 / 2 + 3";
        List<String> expected = List.of("10", "2", "/", "3", "+");
        List<String> result = converter.convertToPostfix(expression);
        assertEquals(expected, result);
    }

    @Test
    void testConvertToPostfixWithExponentiation() {
        String expression = "2 ^ 3 + 4";
        List<String> expected = List.of("2", "3", "^", "4", "+");
        List<String> result = converter.convertToPostfix(expression);
        assertEquals(expected, result);
    }

    @Test
    void testCalculatePostfixSimpleExpression() {
        List<String> postfix = List.of("3", "5", "+");
        BigDecimal expected = new BigDecimal("8");
        BigDecimal result = converter.calculatePostfix(postfix);
        assertEquals(expected, result);
    }

    @Test
    void testCalculatePostfixComplexExpression() {
        List<String> postfix = List.of("3", "5", "2", "*", "+");
        BigDecimal expected = new BigDecimal("13");
        BigDecimal result = converter.calculatePostfix(postfix);
        assertEquals(expected, result);
    }

    @Test
    void testCalculatePostfixWithDivision() {
        List<String> postfix = List.of("10", "2", "/", "3", "+");
        BigDecimal expected = new BigDecimal("8");
        BigDecimal result = converter.calculatePostfix(postfix);
        assertEquals(expected, result);
    }

    @Test
    void testCalculatePostfixWithExponentiation() {
        List<String> postfix = List.of("2", "3", "^", "4", "+");
        BigDecimal expected = new BigDecimal("12");
        BigDecimal result = converter.calculatePostfix(postfix);
        assertEquals(expected, result);
    }

    @Test
    void testCalculatePostfixWithNegativeResult() {
        List<String> postfix = List.of("5", "10", "-", "3", "+");
        BigDecimal expected = new BigDecimal("-2");
        BigDecimal result = converter.calculatePostfix(postfix);
        assertEquals(expected, result);
    }
}