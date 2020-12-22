public class Profile {
   private int    age;
   private double height; // in meters
   private double weight; // in kg
   private Gender gender;

   public Profile(int age, double height, double weight, Gender gender) {
      this.age    = age;
      this.height = height;
      this.weight = weight;
      this.gender = gender;
   }

   /**
    * Calculates and returns BMI of user
    * @return BMI of user
    */
   public double calcBMI() {
      return weight / Math.pow(height, 2);
   }

   /**
    * Returns rough daily calorie intake necessary to
    * maintain the current weight based on BMR of user.
    * @return BMR of user
    */
   public double dailyCalorieIntake() {
      switch (gender) {
         case MALE:
            return 66.47 + (13.75 * weight) + (5.003 * (height * 100)) - (6.755 * age);
         case FEMALE:
            return 655.1 + (9.563 * weight) + ( 1.85 * (height * 100)) - (4.676 * age);
         default:
            return 0.0;
      }
   }

   public void setAge(int age) {
      this.age = age;
   }

   public int getAge() {
      return this.age;
   }

   public void setHeight(double height) {
      this.height = height;
   }

   public double getHeight() {
      return height;
   }

   public void setWeight(double weight) {
      this.weight = weight;
   }

   public double getWeight() {
      return weight;
   }

   public void setGender(Gender gender) {
      this.gender = gender;
   }

   public Gender getGender() {
      return gender;
   }

   @Override
   public String toString() {
      return String.format("Age %d, Weight %.1fkg, Height %.1fm, Gender %s", age, weight, height, gender);
   }
}
