import java.util.*;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Collections;

/** A history-based Rock-Paper-Scissors player.
  * 
  * @author RR
  */
  public class HistoryBot implements RoShamBot {

    String opp_history = new String();
    int WindowSize = 2;
    int turn = 0;
    String sequence = new String();
    int seqcount = 0;
    String finalmove = new String();
    Action finalaction;
    
    /** Returns Action[] of actions that defeat a given action.
     * 
     * @param action
     * @return Action[] winning_moves 
     */
    public Action[] defeatedBy(Action action) {
        Action[] winning_moves = new Action[2];
        if (action.equals(Action.ROCK)) {
            winning_moves[0] = Action.SPOCK;
            winning_moves[1] = Action.PAPER;
        } else if (action.equals(Action.PAPER)) {
            winning_moves[0] = Action.LIZARD;
            winning_moves[1] = Action.SCISSORS;
        } else if (action.equals(Action.SCISSORS)) {
            winning_moves[0] = Action.SPOCK;
            winning_moves[1] = Action.ROCK;
        } else if (action.equals(Action.LIZARD)) {
            winning_moves[0] = Action.ROCK;
            winning_moves[1] = Action.SCISSORS;
        } else {
            winning_moves[0] = Action.LIZARD;
            winning_moves[1] = Action.PAPER;
        }
        return winning_moves;
    }

    /** Returns char representation of action.
     * 
     */
    public String getMoveChar(Action move) {
        if (move.equals(Action.ROCK)) {
            return "R";
        } else if (move.equals(Action.PAPER)) {
            return "P";
        } else if (move.equals(Action.SCISSORS)) {
            return "S";
        } else if (move.equals(Action.LIZARD)) {
            return "L";
        } else { return "K"; }
    } 

    /** Returns a count of the number of occurrences of a substring in a given string.
     * 
     * @param str
     * @param substring
     * @return count
     */
    public int countSubstringOccurrences(String str, String substring) {
        int count = 0, fromIndex = 0;
        
        while ((fromIndex = str.indexOf(substring, fromIndex)) != -1 ){
            count++;
            fromIndex++;
        }
        return count;
    }

    /** Returns a random number in range.
     * 
     * @param min
     * @param max
     * @return int
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /** Considers a set window size and parses opponent history to exploit sequence occurrences.
      * 
      * @param lastOpponentMove the action that was played by the opposing 
      *        agent on the last round.
      *
      * @return the next action to play.
    */
    public Action getNextMove(Action lastOpponentMove) {
        if (opp_history.length() == 0) {
            opp_history += getMoveChar(Action.ROCK);
        } else { opp_history += getMoveChar(lastOpponentMove); }

        String[] actions = {"R", "P", "S", "L", "K"};
        Boolean found = false;
        int[] scores = new int[5];
        for (int l = WindowSize; l > 0; l--) {
            if (l <= opp_history.length()) {
                sequence = opp_history.substring(opp_history.length()-l, opp_history.length());
                for (int i = 0; i <= 4; i++) {
                    scores[i] = 0;
                }
                for (int i = 0; i < actions.length; i++) {
                    seqcount = countSubstringOccurrences(opp_history, sequence + actions[i]);
                    scores[i] = seqcount;
                }
                for (int i = 0; i < scores.length; i++) {
                    if (scores[i] > 0) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            } else {
                continue;
            } 
        }
        // Find max value of the scores
        int maxValue = scores[0];
        int maxIndex = 0;
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > maxValue) {
                maxValue = scores[i];
                maxIndex = i;
            }
        }  
        // Count all of the scores that share the maxValue
        ArrayList<Integer> ties = new ArrayList<Integer>();
        ArrayList<String> tie_actions = new ArrayList<String>();
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == maxValue) {
                ties.add(i);
                tie_actions.add(actions[i]);
            }
        }
        int[] tie_breaker_scores = new int[tie_actions.size()];
        if (tie_actions.size() != 1) {
            // Count the total occurrences of the ties in the entire history
            for (int i = 0; i < tie_actions.size(); i++) {
                tie_breaker_scores[i] = countSubstringOccurrences(opp_history, tie_actions.get(i));
            }
            // Find the max value of total occurrences of the ties
            int maxTieValue = tie_breaker_scores[0];
            int maxTieIndex = 0;
            for (int i = 1; i < tie_breaker_scores.length; i++) {
                if (tie_breaker_scores[i] > maxTieValue) {
                    maxTieValue = tie_breaker_scores[i];
                    maxTieIndex = i;
                }
            } 
            // Count the number of ties in the tie breakers
            ArrayList<Integer> finalties = new ArrayList<Integer>();
            ArrayList<String> finalties_actions = new ArrayList<String>();
            for (int i = 0; i < tie_breaker_scores.length; i++) {
                if (tie_breaker_scores[i] == maxTieValue) {
                    finalties.add(i);
                    finalties_actions.add(actions[i]);
                }
            }
            // If there's another tie, just pick a random action.
            if (finalties.size() != 1) {
                int random = getRandomNumber(0, finalties_actions.size()-1);
                finalmove = finalties_actions.get(random);
            } else {
                finalmove = finalties_actions.get(0);
            }
        } else {
            finalmove = tie_actions.get(0);
        }
        if (finalmove.equals("R")) {
            finalaction = Action.ROCK;
        } else if (finalmove.equals("P")) {
            finalaction = Action.PAPER;
        } else if (finalmove.equals("S")) {
            finalaction = Action.SCISSORS;
        } else if (finalmove.equals("L")) {
            finalaction = Action.LIZARD;
        } else {
            finalaction = Action.SPOCK;
        }

        turn++;
        int random = getRandomNumber(0, 1);
        return defeatedBy(finalaction)[random];
    }
}