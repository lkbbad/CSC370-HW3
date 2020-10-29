import java.util.*;
import java.util.Random;

/** A switching-based Rock-Paper-Scissors player.
  * 
  * @author Lindy Bustabad and Abby Santiago
  */
public class SwitchBot implements RoShamBot {

    int TRIALS = 10000;
    ArrayList<Action> my_history = new ArrayList<Action>();
    Random rand = new Random();
 
    /** Returns a random number in range.
     * 
     * @param min
     * @param max
     * @return int
     */
    public int getRandomNumber(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    /** Never repeat the previous pick
      * 
      * @param lastOpponentMove the action that was played by the opponent on
      *        the last round (this is disregarded).
      * @return the next action to play.
      */
    public Action getNextMove(Action lastOpponentMove) {

        if (my_history.size() == 0){
            my_history.add(Action.ROCK);
        }
       
        Action myLastMove = my_history.get(my_history.size() - 1); 
        //get a random action that isnt the previous action
        if(myLastMove ==  Action.ROCK){
            int rand = getRandomNumber(1, 5);
            if (rand == 1)
                return Action.SPOCK;
            else if (rand == 2)
                return Action.PAPER;
            else if (rand == 3)
                return Action.SCISSORS;
            else return Action.LIZARD;
        }

        else if(lastOpponentMove ==  Action.PAPER){
            int rand = getRandomNumber(1, 5);
            if (rand == 1)
                return Action.SPOCK;
            else if (rand == 2)
                return Action.ROCK;
            else if (rand == 3)
                return Action.SCISSORS;
            else return Action.LIZARD;
        }
        else if(lastOpponentMove ==  Action.SCISSORS){
            int rand = getRandomNumber(1, 5);
            if (rand == 1)
                return Action.SPOCK;
            else if (rand == 2)
                return Action.PAPER;
            else if (rand == 3)
                return Action.ROCK;
            else return Action.LIZARD;
        }
        else if(lastOpponentMove ==  Action.LIZARD){
            int rand = getRandomNumber(1, 5);
            if (rand == 1)
                return Action.SPOCK;
            else if (rand == 2)
                return Action.PAPER;
            else if (rand == 3)
                return Action.SCISSORS;
            else return Action.ROCK;
        }
        else{
            int rand = getRandomNumber(1, 5);
            if (rand == 1)
                return Action.ROCK;
            else if (rand == 2)
                return Action.PAPER;
            else if (rand == 3)
                return Action.SCISSORS;
            else return Action.LIZARD;
        }
    }
    
}