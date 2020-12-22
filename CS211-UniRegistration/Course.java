import java.util.Objects;

public class Course {
   private String code;
   private int credits;

   public Course(String code, int credits) throws UniversityException {
      this.code = code;

      if (credits < 1 || credits > 10) {
         throw new UniversityException("Invalid number of credits for " + code);
      }
      this.credits = credits;
   }

   public int getCredits() {
      return credits;
   }

   public String getCode() {
      return code;
   }

   @Override
   public String toString() {
      return "GMU " + code + " | " + credits + " credits";
   }
}
