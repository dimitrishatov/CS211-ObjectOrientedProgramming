public class Token {
   private Character operator;
   private Double operand;
   private boolean isOperator;

   /**
    * Constructor that initializes an operator with the given value, an operand
    * to 0.0, and isOperator to true.
    * @param operator - desired operator
    */
   public Token(Character operator) {
      this.operator = operator;
      this.operand = 0.0;
      this.isOperator = true;
   }

   /**
    * Constructor that initializes an operand with the given value and an
    * operator to ' ' and isOperator to false.
    * @param operand - desired operand
    */
   public Token(Double operand) {
      this.operator = ' ';
      this.operand = operand;
      this.isOperator = false;
   }

   /**
    * Applies the operator to the two values and returns the result
    * @param value1 - first value
    * @param value2 - second value
    * @return - result of applying the operator to the two values
    */
   public Double applyOperator(Double value1, Double value2) {
      switch(this.operator) {
         case '+':
            return value1 + value2;
         case '-':
            return value1 - value2;
         case '*':
            return value1 * value2;
         case '/':
            return value1 / value2;
         default:
            return -1.0;
      }
   }

   /**
    * Returns the value that gives the operand when the operator is applied to
    * the value and operand ( x + 0 = x, x * 1 = x, x / 1 = x, x - 0 = x)
    * @return - identity value of an operator
    */
   public Double getIdentity() {
      if (this.operator == '+' || this.operator == '-') {
         return 0.0;
      }
      else if (this.operator == '*' || this.operator == '/') {
         return 1.0;
      }
      return -1.0;
   }

   /**
    * Decides if an operator can be used without an operand in the expression.
    * - Addition       (+)  returns 0   (TRUE)
    * - Subtraction    (-a) returns -a  (FALSE)
    * - Multiplication (*)  returns 1   (TRUE)
    * - Division       (/a) returns 1/a (FALSE)
    * @return - t/f depending on if operator can be used without operand or not
    */
   public boolean takesNoOperands() {
      return this.operator == '+' || this.operator == '*';
   }

   // *-*-*-* Getter Methods *-*-*-* //

   public boolean isOperator() {
      return this.isOperator;
   }

   public Double getValue() {
      return this.operand;
   }

   public Character getOperator() {
      return this.operator;
   }
}
