public class TargetWeightException extends RuntimeException {
   public TargetWeightException() {
      super("Only works to lose weight");
   }

   public TargetWeightException(String s) {
      super(s);
   }
}
