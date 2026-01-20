/**
 * The CalculateInfix class provides methods to evaluate infix expressions.
 */
public class CalculateInfix {
    /**
     * Infix to postfix to solution
     * @param tokens the equation to be solved
     * @return resulting number
     */
    public static Double infixToPostfix(Queue<Object> tokens) {
        Stack<Object> opStack = new Stack<>();
        Queue<Object> finalQueue = new Queue<>();
        while(!tokens.isEmpty()){
            if(tokens.peek() instanceof Double){
                finalQueue.add(tokens.remove());
            }else if((Character) tokens.peek() == '+' | (Character) tokens.peek() == '-' | (Character) tokens.peek() == '*' | (Character) tokens.peek() == '/'| (Character) tokens.peek() == '^'){
                while(!opStack.isEmpty()){
                    if((Character) opStack.peek() == '^'){
                        if((Character) tokens.peek() == '^'){
                            break;
                        }
                        finalQueue.add(opStack.pop());
                    }else if((Character) opStack.peek() == '*' | (Character) opStack.peek() == '/'){
                        if((Character) tokens.peek() == '^'){
                            break;
                        }
                      finalQueue.add(opStack.pop());
                    }else if((Character) opStack.peek() == '+' | (Character) opStack.peek() == '-'){
                        if((Character) tokens.peek() == '^' | (Character) tokens.peek() == '*' | (Character) tokens.peek() == '/'){
                            break;
                        }
                        finalQueue.add(opStack.pop());
                    }else{
                        break;
                    }
                }
                opStack.push(tokens.remove());
            }else if ((Character) tokens.peek() == '('){
                opStack.push(tokens.remove());
            }else if ((Character) tokens.peek() == ')'){
                if(opStack.isEmpty()){
                    throw new IllegalArgumentException("missing ( invalid equation");
                }
                while((Character) opStack.peek() != '(' ){
                    finalQueue.add(opStack.pop());
                    if(opStack.isEmpty()){
                        throw new IllegalArgumentException("missing ( invalid equation");
                    }
                }
                if((Character) opStack.peek() == '(' ){
                    opStack.pop();
                }
                tokens.remove();
            }else{
                throw new IllegalArgumentException("not a valid operator");
            }
        }
        while(!opStack.isEmpty()){
            if((Character) opStack.peek() == '(' ){
                throw new IllegalArgumentException("missing ) invalid equation");
            }
            finalQueue.add(opStack.pop());
        }
        return CalculatePostfix.postfixToResult(finalQueue);
    }
    /**
     * Main for debugging
     * @param args for the function
     */

    public static void main(String[] args) {
        String stringPlus = "9-1-1";
        //and then implement PHASE 4 and you are done
        Queue<Object> plus = Tokenizer.readTokens(stringPlus);
        System.out.println(infixToPostfix(plus));
    }
}
