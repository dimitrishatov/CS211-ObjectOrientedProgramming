public class Swimming extends Aerobic{
   private SwimmingType swimmingType;

   /**
    * Constructor for the class
    * @param type user-selected swimming type
    */
   public Swimming (SwimmingType type) {
      this.swimmingType = type;
   }

   /**
    * Default constructor which sets
    * swimmingType to Freestyle automatically
    */
   public Swimming() {
      this.swimmingType = SwimmingType.Freestyle;
   }

   /**
    * Basic setter method for swimmingType
    * @param type type to be set
    */
   public void setSwimmingType(SwimmingType type) {
      this.swimmingType = type;
   }

   /**
    * Basic getter method for swimmingType
    * @return current swimmingType
    */
   public SwimmingType getSwimmingType() {
      return this.swimmingType;
   }

   /**
    * Returns muscles targeted based on swimmingType
    * @return Muscle[] of muscles targeted
    */
   @Override
   public Muscle[] muscleTargeted() {
      switch(swimmingType) {
         case Butterflystroke:
            return new Muscle[] { Muscle.Abs, Muscle.Back, Muscle.Shoulders, Muscle.Biceps, Muscle.Triceps };

         case Breaststroke:
            return new Muscle[] { Muscle.Glutes, Muscle.Cardio };

         case Freestyle:
            return new Muscle[] { Muscle.Arms, Muscle.Legs, Muscle.Cardio };

         default:
            return new Muscle[] {};
      }
   }

   /**
    * Calculates calories lost based on MET.
    *
    * @param intensity Intensity of exercise
    * @param weight weight of user
    * @param duration duration of exercise in minutes
    * @return calories lost
    */
   @Override
   public double calorieLoss(Intensity intensity, double weight, int duration) {
      double[] intensities = {10.0, 8.3, 6.0};
      return calculateCalorieLoss(intensity, intensities, weight, duration);
   }

   /**
    * Returns description of exercise
    * @return name of class
    */
   @Override
   public String description() {
      return "Swimming";
   }

}
