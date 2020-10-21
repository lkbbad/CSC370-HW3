/** A non-equilibrium Rock-Paper-Scissors player.
  * 
  * @author RR
  */
  public class Random implements RoShamBot {
 
    /** Returns an action at random from the five choices.
      * 
      * @param lastOpponentMove the action that was played by the opponent on
      *        the last round (this is disregarded).
      * @return the next action to play.
      */
    public Action getNextMove(Action lastOpponentMove) {
        int rand = getRandomNumber(1, 5);
        if (rand == 1)
            return Action.ROCK;
        else if (rand == 2)
            return Action.PAPER;
        else if (rand == 3)
            return Action.SCISSORS;
        else if (rand == 4)
            return Action.LIZARD;
        else return Action.SPOCK;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
}