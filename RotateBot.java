import java.util.*;

/** A rotation-based Rock-Paper-Scissors player.
  * 
  * @author RR
  */
  public class RotateBot implements RoShamBot {

    /*  Full History Structure (global variables, accessible to the
                            current player during each match)

      - element 0 is the number of trials played so far
      - element i is the action taken on turn i (1 <= i <= trials ) */

    int TRIALS = 10000;
    ArrayList<Action> my_history = new ArrayList<Action>();
    
    /** Rotates choice of action each turn.
      * 
      * @param lastOpponentMove the action that was played by the opposing 
      *        agent on the last round.
      *
      * @return the next action to play.
    */
    public Action getNextMove(Action lastOpponentMove) {
        // HashTable<Action, int> dict = new HashTable<Action, int>
        if (my_history.size() == 0)
            my_history.add(Action.ROCK);
        
        Action myLastMove = my_history.get(my_history.size() - 1); 
        if (myLastMove.equals(Action.ROCK)) {
            my_history.add(Action.PAPER);
            return Action.PAPER;
        }
        else if (myLastMove.equals(Action.PAPER)) {
            my_history.add(Action.SCISSORS);
            return Action.SCISSORS;
        }
        else if (myLastMove.equals(Action.SCISSORS)) {
            my_history.add(Action.LIZARD);
            return Action.LIZARD;
        }
        else if (myLastMove.equals(Action.LIZARD)) {
            my_history.add(Action.SPOCK);
            return Action.SPOCK;
        }
        else {
            my_history.add(Action.ROCK);
            return Action.ROCK;
        }
    }
}