package player;

public class TennisPlayer extends Player {
  
  public TennisPlayer() {
    super();
  }

  public TennisPlayer(Type type, int delta) {
      super(type, delta);
   }

  @Override
  public int playerEndurance() {
      return ComputeEnduranceAlgorithm.basicEndurance
         (this.getDelta());
  }

  @Override
  public String toString() {
    return "Tennis Player";
  }
}
