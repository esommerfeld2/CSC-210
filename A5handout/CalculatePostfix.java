/**
 * The CalculatePostfix class provides methods to evaluate postfix expressions.
 */
public class CalculatePostfix {
    /**
     * Postfix to result function
     * @param tokens the equation to be solved
     * @return resulting number
     */
    public static Double postfixToResult(Queue<Object> tokens) {
        Stack<Object> numberStack = new Stack<>();
        while(!tokens.isEmpty()){
            if(tokens.peek() instanceof Double){
                numberStack.push(tokens.remove());
            }else{
                if(numberStack.isEmpty()){
                    throw new IllegalArgumentException("incorrect format of equation");
                }
                Double y = (Double) numberStack.pop();
                if(numberStack.isEmpty()){
                    throw new IllegalArgumentException("incorrect format of equation");
                }
                Double x = (Double) numberStack.pop();
                Object token = tokens.peek();
                if ((Character) token == '+'){
                    tokens.remove();
                    double val = x + y;
                    numberStack.push(val);
                }else if((Character) token == '-'){
                    tokens.remove();
                    double val = x - y;
                    numberStack.push(val);
                }else if((Character) token == '/'){
                    tokens.remove();
                    if(y == 0){
                        throw new IllegalArgumentException("can't divide by 0");
                    }
                    double val = x / y;
                    numberStack.push(val);
                }else if ((Character) token == '*'){
                    tokens.remove();
                    double val = x * y;
                    numberStack.push(val);
                }else if((Character) token == '^'){
                    tokens.remove();
                    double val = Math.pow(x, y);
                    numberStack.push(val);
                }else{
                    throw new IllegalArgumentException("not a valid operator");
                }
            }
        }
        Double finalVal = (Double) numberStack.pop();
        if(numberStack.isEmpty()){
            return finalVal;
        }else{
            throw new IllegalArgumentException("multiple values in stack");
        }
    }

    /**
     * Main for debugging
     * @param args for function
     */

    public static void main(String[] args) {
        String newInput = "9 1 - 1 -";
        Queue<Object> ourQueue = Tokenizer.readTokens(newInput);
        System.out.println(postfixToResult(ourQueue));
    }
}