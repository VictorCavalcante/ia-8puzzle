package br.ic.ufal.ia;

import br.ic.ufal.ia.puzzle.Node;
import br.ic.ufal.ia.puzzle.SearchTree;

public class App {
    final static private String GOAL_STATE = "123804765";
    final static private String INITIAL_STATE = "281043765";
//    final static private String INITIAL_STATE = "567408321"; // Worst case (reversed)

    public static void main(String[] args) {
        SearchTree search = new SearchTree(new Node(INITIAL_STATE), GOAL_STATE);
        long startTime = System.currentTimeMillis();

        search.breadthFirstSearch();
//        search.depthFirstSearch();
//        search.iterativeDeepening(10);

        long finishTime = System.currentTimeMillis();
        long totalTime = finishTime - startTime;
        System.out.println("\n[Elapsed time: " + totalTime + " milliseconds]");
        System.out.println("========================================================");
    }
}
