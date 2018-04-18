package br.ic.ufal.ia.utils;

import br.ic.ufal.ia.puzzle.Direction;
import br.ic.ufal.ia.puzzle.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Utilities {

    /* Returns the successors of a node */
    public static List<String> getNodeSuccessors(String state) {
        List<String> successors = new ArrayList<String>();

        switch (state.indexOf("0")) {
            case 0: {
                successors.add(
                        state.replace(state.charAt(0), '*').replace(state.charAt(1),
                        state.charAt(0)).replace('*', state.charAt(1)));
                successors.add(
                        state.replace(state.charAt(0), '*').replace(state.charAt(3),
                        state.charAt(0)).replace('*', state.charAt(3)));
                break;
            }
            case 1: {
                successors.add(
                        state.replace(state.charAt(1), '*').replace(state.charAt(0),
                        state.charAt(1)).replace('*', state.charAt(0)));
                successors.add(
                        state.replace(state.charAt(1), '*').replace(state.charAt(2),
                        state.charAt(1)).replace('*', state.charAt(2)));
                successors.add(
                        state.replace(state.charAt(1), '*').replace(state.charAt(4),
                        state.charAt(1)).replace('*', state.charAt(4)));
                break;
            }
            case 2: {
                successors.add(
                        state.replace(state.charAt(2), '*').replace(state.charAt(1),
                        state.charAt(2)).replace('*', state.charAt(1)));
                successors.add(
                        state.replace(state.charAt(2), '*').replace(state.charAt(5),
                        state.charAt(2)).replace('*', state.charAt(5)));
                break;
            }
            case 3: {
                successors.add(
                        state.replace(state.charAt(3), '*').replace(state.charAt(0),
                        state.charAt(3)).replace('*', state.charAt(0)));
                successors.add(
                        state.replace(state.charAt(3), '*').replace(state.charAt(4),
                        state.charAt(3)).replace('*', state.charAt(4)));
                successors.add(
                        state.replace(state.charAt(3), '*').replace(state.charAt(6),
                        state.charAt(3)).replace('*', state.charAt(6)));
                break;
            }
            case 4: {
                successors.add(
                        state.replace(state.charAt(4), '*').replace(state.charAt(1),
                        state.charAt(4)).replace('*', state.charAt(1)));
                successors.add(
                        state.replace(state.charAt(4), '*').replace(state.charAt(3),
                        state.charAt(4)).replace('*', state.charAt(3)));
                successors.add(
                        state.replace(state.charAt(4), '*').replace(state.charAt(5),
                        state.charAt(4)).replace('*', state.charAt(5)));
                successors.add(
                        state.replace(state.charAt(4), '*').replace(state.charAt(7),
                        state.charAt(4)).replace('*', state.charAt(7)));
                break;
            }
            case 5: {
                successors.add(
                        state.replace(state.charAt(5), '*').replace(state.charAt(2),
                        state.charAt(5)).replace('*', state.charAt(2)));
                successors.add(
                        state.replace(state.charAt(5), '*').replace(state.charAt(4),
                        state.charAt(5)).replace('*', state.charAt(4)));
                successors.add(
                        state.replace(state.charAt(5), '*').replace(state.charAt(8),
                        state.charAt(5)).replace('*', state.charAt(8)));
                break;
            }
            case 6: {
                successors.add(
                        state.replace(state.charAt(6), '*').replace(state.charAt(3),
                        state.charAt(6)).replace('*', state.charAt(3)));
                successors.add(
                        state.replace(state.charAt(6), '*').replace(state.charAt(7),
                        state.charAt(6)).replace('*', state.charAt(7)));
                break;
            }
            case 7: {
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(4),
                        state.charAt(7)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(6),
                        state.charAt(7)).replace('*', state.charAt(6)));
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(8),
                        state.charAt(7)).replace('*', state.charAt(8)));
                break;
            }
            case 8: {
                successors.add(
                        state.replace(state.charAt(8), '*').replace(state.charAt(5),
                        state.charAt(8)).replace('*', state.charAt(5)));
                successors.add(
                        state.replace(state.charAt(8), '*').replace(state.charAt(7),
                        state.charAt(8)).replace('*', state.charAt(7)));
                break;
            }
        }
        return successors;
    }

    /* Prints the transitions along with the states starting from the initial states to the goal state */
    public static void printSolution(Node goalNode, Set<String> visitedNodes, Node root, int time) {
        int totalCost = 0;

        Stack<Node> solutionStack = new Stack<Node>();
        solutionStack.push(goalNode);
        while (!goalNode.getState().equals(root.getState())) {
            solutionStack.push(goalNode.getParent());
            goalNode = goalNode.getParent();
        }
        String sourceState = root.getState();
        String destinationState;
        int cost = 0;
        for (int i = solutionStack.size() - 1; i >= 0; i--) {
            System.out.println("========================");
            destinationState = solutionStack.get(i).getState();
            if (!sourceState.equals(destinationState)) {
                System.out.println("> MOVE '" + destinationState.charAt(sourceState.indexOf('0')) + "' " + findTransition(sourceState, destinationState));
                cost = Character.getNumericValue(destinationState.charAt(sourceState.indexOf('0')));
                totalCost += cost;
            }

            sourceState = destinationState;
            System.out.println("> Movement Cost: " + cost);
            System.out.println("> Result:");
            System.out.println("-------");
            System.out.println("| " + solutionStack.get(i).getState().substring(0, 3)+" |");
            System.out.println("| " + solutionStack.get(i).getState().substring(3, 6)+" |");
            System.out.println("| " + solutionStack.get(i).getState().substring(6, 9)+" |");
            System.out.println("-------");

        }
        System.out.println("\n================================================ RESULTS");
        System.out.println("> " + (solutionStack.size() - 1) + "\tTransitions from initial state to goal");
        System.out.println("> " + visitedNodes.size() + "\tVisited states");
        System.out.println("> " + totalCost + "\tSolution cost");
        System.out.println("> " + time + "\tNodes poped from the queue: ");
    }

    /* Returns transition between two states */
    public static Direction findTransition(String source, String destination) {
        int zero_position_difference = destination.indexOf('0') - source.indexOf('0');
        switch (zero_position_difference) {
            case -3: return Direction.DOWN;
            case 3: return Direction.UP;
            case 1: return Direction.LEFT;
            case -1: return Direction.RIGHT;
        }
        return null;
    }
}



