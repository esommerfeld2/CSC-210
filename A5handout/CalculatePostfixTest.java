import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class CalculatePostfixTest {

    //inputs that should work
    String stringPlus = "3 2 +";
    Queue<Object> plus = Tokenizer.readTokens(stringPlus);
    String stringSub = "3 2 -";
    Queue<Object> sub = Tokenizer.readTokens(stringSub);
    String stringMultiply = "3 2 *";
    Queue<Object> multiply = Tokenizer.readTokens(stringMultiply);
    String stringDivide = "6 3 /";
    Queue<Object> divide = Tokenizer.readTokens(stringDivide);
    String stringMulti = "3 2 + 4 -";
    Queue<Object> multi = Tokenizer.readTokens(stringMulti);

    //inputs that shouldn't work
    String stringManyNums = "3 2 + 5";
    Queue<Object> manyNums = Tokenizer.readTokens(stringManyNums);
    String stringWrongOp = "3 2 &";
    Queue<Object> wrongOp = Tokenizer.readTokens(stringWrongOp);
    String stringOp = "-";
    Queue<Object> op = Tokenizer.readTokens(stringOp);
    String stringNotNums = "3 -";
    Queue<Object> notNums = Tokenizer.readTokens(stringNotNums);

    @Test
    public void check_add() {
        assertEquals((Double) 5.0, CalculatePostfix.postfixToResult(plus));
        
    }

    @Test
    public void check_subtract() {
        assertEquals((Double)1.0, CalculatePostfix.postfixToResult(sub));
        
    }

    @Test
    public void check_multiply() {
        assertEquals((Double) 6.0, CalculatePostfix.postfixToResult(multiply));
        
    }

    @Test
    public void check_divide() {
        assertEquals((Double) 2.0, CalculatePostfix.postfixToResult(divide));
        
    }

    @Test
    public void check_multiple() {
        assertEquals((Double) 1.0, CalculatePostfix.postfixToResult(multi));
        
    }

    @Test
    public void check_too_many_numbers() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CalculatePostfix.postfixToResult(manyNums);
        });
        assertEquals("multiple values in stack", exception.getMessage());
        
    }

    @Test
    public void check_invalid_operator() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CalculatePostfix.postfixToResult(wrongOp);
        });
        assertEquals("not a valid operator", exception.getMessage());
        
    }

    @Test
    public void check_operator_wrong_place_no_numbers() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CalculatePostfix.postfixToResult(op);
        });
        assertEquals("incorrect format of equation", exception.getMessage());
    }

    @Test
    public void check_operator_wrong_place_one_number() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CalculatePostfix.postfixToResult(notNums);
        });
        assertEquals("incorrect format of equation", exception.getMessage());
    }
    
}
