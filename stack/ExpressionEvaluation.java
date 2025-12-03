package stack;

import java.util.*;

public class ExpressionEvaluation {
    // Function to get precedence of operators
    private static int precedence(char op) {
        switch(op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
        }
        return -1;
    }

    // Convert infix to postfix using stack
    private static List<String> infixToPostfix(String[] tokens) {
        List<String> output = new ArrayList<>();
        Stack<Character> stack = new Stack<>();

        for(String token : tokens) {
            if(Character.isDigit(token.charAt(0))) {
                output.add(token);
            } else if(token.equals("(")) {
                stack.push('(');
            } else if(token.equals(")")) {
                while(!stack.isEmpty() && stack.peek() != '(') {
                    output.add(String.valueOf(stack.pop()));
                }
                stack.pop(); // remove '('
            } else { // operator
                char op = token.charAt(0);
                while(!stack.isEmpty() && precedence(stack.peek()) >= precedence(op)) {
                    output.add(String.valueOf(stack.pop()));
                }
                stack.push(op);
            }
        }
        while(!stack.isEmpty()) {
            output.add(String.valueOf(stack.pop()));
        }
        return output;
    }

    // Evaluate postfix using stack
    private static int evaluatePostfix(List<String> postfix) {
        Stack<Integer> stack = new Stack<>();
        for(String token : postfix) {
            if(Character.isDigit(token.charAt(0)) || token.length() > 1) {
                stack.push(Integer.parseInt(token));
            } else {
                int b = stack.pop();
                int a = stack.pop();
                switch(token.charAt(0)) {
                    case '+': stack.push(a + b); break;
                    case '-': stack.push(a - b); break;
                    case '*': stack.push(a * b); break;
                    case '/': stack.push(a / b); break;
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
        String[] tokens = expression.split(" ");

        List<String> postfix = infixToPostfix(tokens);
        int result = evaluatePostfix(postfix);

        System.out.println(result);
        sc.close();
    }
}
