import java.util.*;

/** A pattern-based Rock-Paper-Scissors player.
  * 
  * @author RR
  */
  public class FoxtrotBot implements RoShamBot {

    static int turn = 1;
    ArrayList<Integer> my_history = new ArrayList<Integer>();
    
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /** Choice of action follows set pattern: rand prev+2 rand prev+1 rand prev+0 repeat.
      * 
      * @param lastOpponentMove the action that was played by the opposing 
      *        agent on the last round.
      *
      * @return the next action to play.
    */
    public Action getNextMove(Action lastOpponentMove) {
        if (my_history.size() == 0) {
            my_history.add(0); // Rock = 0
        } 
        turn++;
        int move;
        int myLastMove = my_history.get(my_history.size() - 1); 
        if ((turn % 2) != 0) { // odd numbered turns
            move = getRandomNumber(0, 5); 
        } else { // even numbered turns
            move = (myLastMove + turn) % 5; 
        }

        if (move == 0) { // Rock
            my_history.add(move);
            return Action.ROCK;
        } else if (move == 1) { // Paper
            my_history.add(move);
            return Action.PAPER;
        } else if (move == 2) { // Scissors
            my_history.add(move);
            return Action.SCISSORS;
        } else if (move == 3) { // Lizard
            my_history.add(move);
            return Action.LIZARD;
        } else {
            my_history.add(move); // Spock
            return Action.SPOCK;
        }
    }
}