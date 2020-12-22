public class Plyometrics extends Anaerobic {
   /**
    * Returns muscles targeted
    * @return Muscle[] of muscles targeted
    */
   @Override
   public Muscle[] muscleTargeted() {
      return new Muscle[] {Muscle.Abs, Muscle.Legs, Muscle.Glutes};
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
      double[] intensities = {7.4, 4.8, 2.5};
      return calculateCalorieLoss(intensity, intensities, weight, duration);
   }

   /**
    * Returns description of exercise
    * @return name of class
    */
   @Override
   public String description() {
      return "Plyometrics";
   }
}
