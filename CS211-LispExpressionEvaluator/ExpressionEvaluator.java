import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ExpressionEvaluator {
   /**
    * Accepts an expression and returns whether it is balanced or not. A Lisp
    * expression is considered balanced if it is properly parenthesized.
    * @param expr - expression to be checked
    * @return - whether the expression is balanced or not
    */
   public static boolean isBalanced(String expr) {
      // We are going to count the number of '(' and ')' characters and make
      // sure that they are equal.
      int lParenCount = 0;
      int rParenCount = 0;
      // Count up all parenthesis
      for (int i = 0; i < expr.length(); i++) {
         if (expr.charAt(i) == '(') {
            lParenCount++;
         }
         if (expr.charAt(i) == ')') {
            rParenCount++;
         }
      }
      // Return if they are equal
      return lParenCount == rParenCount;
   }

   /**
    * Accepts a Lisp expression and returns the result. Returns RunTimeException
    * if the accepted expression is not a valid Lisp expression.
    *
    * Exception will be thrown if:
    * - Expression is empty
    * - Expression is unbalanced
    * - Expression is invalid, examples: {(/ * wl), (a + b + c), (/)}
    * - Does not adhere to basic Lisp rules
    *
    * This method considers letters as operands and will prompt the programmer
    * for values of the operands and use those values for the operation. The
    * values must be integers.
    * @param expr - expression to be evaluated
    * @return - whether the provided expression is valid
    */
   public static Double evaluate (String expr) throws RuntimeException {
      // Preliminary checks to ensure expression is parsable on a basic level
      if (expr.equals("()") || !ExpressionEvaluator.isBalanced(expr)) {
         throw new RuntimeException();
      } 

      // This ArrayList contains all the tokens in expr individually and helps makes
      // our life much easier.
      ArrayList<Token> tokens = parseTokens(expr);
      // This Stack will be used as a tracer as operations are carried out on
      // the tokens.
      Stack<Token> trace = new Stack<>();

      // We are going to add every token one by one
      while (tokens.size() != 0) {
         // Push our token onto the stack..
         trace.push(tokens.remove(0));
         // If we get to a closing parentheses... --> ')'
         if (trace.peek().getOperator() == ')') {
            // We are going to have to carry out an operation so we will store
            // all our operands in an ArrayList
            ArrayList<Token> operands = new ArrayList<>();
            // We add every operand to our ArrayList
            // until we get to the beginning of our parentheses
            while (trace.peek().getOperator() != '(') {
               operands.add(trace.pop());
            }
            // We remove our opening parentheses
            trace.pop();
            // We push the simplified result of the expression onto our stack
            // and carry on
            trace.push(simplify(operands));
         }
      }
      return trace.peek().getValue();
   }

   /**
    * Takes an ArrayList of operands with an operator and applies the operator to
    * all operands. Returns a single operand Token with the result. Throws RuntimeException
    * if it encounters any problems
    * @param operands - ArrayList to be simplified
    * @return - a Token with the result of the operation
    * @throws RuntimeException - if operation is invalid
    */
   private static Token simplify(ArrayList<Token> operands) throws RuntimeException {
      // The first value in our ArrayList will always be a ')' character so it can
      // be safely removed
      operands.remove(0);

      // If the ArrayList contains more than one operand it is an invalid expression
      // so we can simply throw an Exception
      if (!validOperatorCount(operands)) {
         throw new RuntimeException();
      }

      // If the expression is valid the last element in our ArrayList should
      // always be the operator, so we remove it and store it for use. Our ArrayList
      // should now only contain Double operands.
      Token operator = operands.remove(operands.size() - 1);

      // This will take care of expressions with only one operator
      // in them such as (*), (+), (-), (/). Since at this point our ArrayList contains only operands,
      // if it is empty we know that the expression only contained an operator.
      if (operands.size() == 0 && !operator.takesNoOperands()) {
         throw new RuntimeException();
      }
      else if (operands.size() == 0 && operator.takesNoOperands()) {
         if (operator.getOperator() == '+') {
            return new Token(0.0);
         }
         else {
            return new Token(1.0);
         }
      }
      // If we get to this point we know we have operands and can begin to compute our
      // result
      double result = 0.0;
      // It is necessary to check for single operand operations because different behavior
      // is expected when there is only one operand in the expression
      if (operands.size() == 1) {
         return new Token(operator.applyOperator(operator.getIdentity(), operands.get(0).getValue()));
      }
      // Multi-operand expressions are taken care of here
      else {
         // We work from the last value backwards to account for non-commutative operations (subtraction
         // and division) and continually apply the operator to our stored result until we run out of operands.
         result = operands.remove(operands.size() - 1).getValue();
         while (operands.size() != 0) {
            result = operator.applyOperator(result, operands.remove(operands.size() - 1).getValue());
         }
      }
      return new Token(result);
   }

   /**
    * Goes through a String expression and returns an ArrayList of Tokens containing each
    * individual token whether the expression is valid or not. (Couldn't figure out how
    * to use Scanner to do this easily, so this solution is pretty hacky. Still works though.)
    * @param expression - expression to be parsed
    * @return - Token ArrayList of all individual tokens in expression
    */
   private static ArrayList<Token> parseTokens(String expression) {
      // This will store our tokens
      ArrayList<Token> tokens = new ArrayList<Token>();
      // In case of letter variables in our expression
      Scanner scan = new Scanner(System.in);

      // For every character in our expression...
      for (int i = 0; i < expression.length(); i++) {
         // If the current character is whitespace we skip..
         if (Character.isWhitespace(expression.charAt(i))) {
            continue;
         }
         // If it is a digit we make sure we are grabbing the whole number in case
         // it has multiple digits (ex: 367 instead of 3,6,7 individually).
         if (Character.isDigit(expression.charAt(i))) {
            StringBuilder fullDigit = new StringBuilder();
            while (Character.isDigit(expression.charAt(i))) {
               fullDigit.append(expression.charAt(i++));
            }
            tokens.add(new Token(new Double(Integer.parseInt(fullDigit.toString()))));
            i--;
            continue;
         }
         // If it is a letter we ask the user to specify what integer it is and then
         // add that to tokens in its place..
         if (Character.isAlphabetic(expression.charAt(i))) {
            System.out.println("What is the value of '" + expression.charAt(i) + "'?");
            tokens.add(new Token(new Double(scan.nextInt())));
            continue;
         }
         // Otherwise we just add the current character as it will just be an operator
         tokens.add(new Token(expression.charAt(i)));
      }
      return tokens;
   }

   /**
    * Simple process to check if our expression has a valid number of operators (1)
    * @param operands - ArrayList of operands to be checked
    * @return - whether the ArrayList contains only one operand
    */
   private static boolean validOperatorCount(ArrayList<Token> operands) {
      int count = 0;
      for (Token token : operands) {
         if (token.isOperator()) {
            count++;
         }
      }
      return count == 1;
   }
}

