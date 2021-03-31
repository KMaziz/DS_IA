import java.util.*;

final class Minimax {

    private Minimax() {}

    public static State minimaxDecision(State state) {
        return state.getActions().stream()
                .max(Comparator.comparing(Minimax::minValue)).get();
    }

    private static double maxValue(State state) {
        if(state.isTerminal()){
            return state.getUtility();
        }
        return state.getActions().stream()
                .map(Minimax::minValue)
                .max(Comparator.comparing(Double::valueOf)).get();
    }

    private static double minValue(State state) {
        if(state.isTerminal()){
            return state.getUtility();
        }
        return state.getActions().stream()
                .map(Minimax::maxValue)
                .min(Comparator.comparing(Double::valueOf)).get();
    }

     static class State {

        final int state;
        final boolean firstPlayer;
        final boolean secondPlayer;
        final ArrayList<Integer> integers ;

        public State(int state, boolean firstPlayer, ArrayList<Integer> integers){
            this.state = state;
            this.firstPlayer = firstPlayer;
            this.secondPlayer = !firstPlayer;
            this.integers = integers;
        }

        Collection<State> getActions(){
            List<State> actions = new LinkedList<>();
            if(state == 7){
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(6);
                integers.add(1);
                actions.add(new State(state-1, secondPlayer, integers));
                integers = new ArrayList<>();
                integers.add(2);
                integers.add(5);

                actions.add(new State(state-2, secondPlayer, integers));
//                integers = new ArrayList<>();
//                integers.add(3);
//                integers.add(4);
//
//                actions.add(new State(state-3, secondPlayer, integers));
            }
            if(state ==6){
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(5);
                integers.add(1);

                actions.add(new State(state-1, secondPlayer, integers));
                integers = new ArrayList<>();
                integers.add(2);
                integers.add(4);
                actions.add(new State(state-2, secondPlayer, integers));
            }
            if(state == 5){
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(4);
                integers.add(1);
                actions.add(new State(state-1, secondPlayer, integers));
                integers = new ArrayList<>();
                integers.add(3);
                integers.add(2);
                actions.add(new State(state-2, secondPlayer, integers));
            }
            if(state == 4){
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(3);
                integers.add(1);
                actions.add(new State(state-1, secondPlayer, integers));
            }
            if(state == 3){
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(2);
                integers.add(1);
                actions.add(new State(state-1, secondPlayer, integers));
                }
            return actions;
        }

        boolean isTerminal() {
            return state < 3;
        }

        double getUtility() {
            if(firstPlayer)
                return -1;
            else
                return 1;
        }
    }
}
public class Main {

    public static void main(String[] args){
        boolean end = false;
        int val = 7;
        boolean first = true;
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(7);
        int l =7;
        while(!end) {

            if(val!=7)
            System.out.println((first?"player 1 ": "player 2 ") +" a choisit de diviser "+ l  +"  en " + integers );
            Minimax.State s = new Minimax.State(val, true, integers);
            Minimax.State decision = Minimax.minimaxDecision(s);

            l=val;
            val = decision.state;
            integers=decision.integers;
            if(decision.isTerminal()){
                System.out.println();
                end = true;
                System.out.println((first?"player 1 ": "player 2 ") +" a choisit de diviser "+ l  +"  en " + integers );
                System.out.println((first?"player 1 ": "player 2 ") +"a gagné");

                System.out.println("Game over !!!");
                break;
            }
            first =! first;

            System.out.println();
        }
    }
}