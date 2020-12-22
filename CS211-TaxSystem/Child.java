public class Child extends Person {
   private String school;
   private float tuition;

   public Child(String name, String birthday, String ssn, float income, String school, float tuition) {
      super.setName(name);
      super.setBirthday(birthday);
      super.setSSN(ssn);
      super.setIncome(income);

      this.school = school;
      this.tuition = tuition;
   }

   public String toString() {
      return super.toString() + " " + school;
   }

   public float getTuition() {
      return tuition;
   }

   // Returns amount exempted from taxation for child
   public float deduction(Family family) {
      int   numChildren = family.getNumChildren();
      float deduction   = 0.0f;

      if (numChildren <= 2) {
         deduction += Taxation.childBaseExemption;
      }
      else {
         float exemption = Taxation.childBaseExemption;

         // 5% reduction per kid over 2 kids
         float percentReduction = (numChildren - 2) * 0.05f;

         // Maximum deduction can be 0.50
         if (percentReduction > 0.50f) {
            percentReduction = 0.50f;
         }

         deduction += exemption - (exemption * percentReduction);
      }

      // Deduction can't be more than adjusted income
      if (deduction > this.getIncome()) {
         deduction = this.getIncome();
      }

      return deduction;
   }
}
