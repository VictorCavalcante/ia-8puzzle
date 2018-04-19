package br.ic.ufal.ia.puzzle;

import br.ic.ufal.ia.utils.QueueUtils;
import br.ic.ufal.ia.utils.Utilities;

import java.util.*;

public class SearchTree {
    private Node root;
    private String goalSate;

    public SearchTree(Node root, String goalSate) {
        this.root = root;
        this.goalSate = goalSate;
    }

    /*
    * Breadth First Search
    *
    * Start with the initial state
    * Check if it is goal or not and expand its children if it is not the goal
    * Pop the first element of the queue and check if it is goal node
    * If not add its children to the queue
    * Repeat the process untill the solution is found
    * */
    public void breadthFirstSearch() {
        System.out.println(">>> Breadth First Search\n");

        Set<String> stateSets = new HashSet<String>();
        int time = 0;
        Node node = new Node(root.getState());
        Queue<Node> queue = new LinkedList<Node>();
        Node currentNode = node;

        while (!currentNode.getState().equals(goalSate)) {
            stateSets.add(currentNode.getState());
            List<String> nodeSuccessors = Utilities.getNodeSuccessors(currentNode.getState());
            for (String n : nodeSuccessors) {
                if (stateSets.contains(n))
                    continue;
                stateSets.add(n);
                Node child = new Node(n);
                currentNode.addChild(child);
                child.setParent(currentNode);
                queue.add(child);

            }
            currentNode = queue.poll();
            time += 1;
        }
        Utilities.printSolution(currentNode, stateSets, root, time);
    }

    /*
     * Depth First Search
     * */
    public void depthFirstSearch() {
        System.out.println(">>> Depth First Search\n");

        // stateSet is a set that contains node that are already visited
        Set<String> stateSets = new HashSet<String>();
        int time = 0;
        Node node = new Node(root.getState());
        // QueueUtils that store nodes that we should expand
        QueueUtils<Node> mainQueue = new QueueUtils<>();
        // QueueUtils that contains the successors of the expanded node
        QueueUtils<Node> successorsQueue = new QueueUtils<>();
        Node currentNode = node;
        while (!currentNode.getState().equals(goalSate)) {
            stateSets.add(currentNode.getState());
            List<String> nodeSuccessors = Utilities.getNodeSuccessors(currentNode.getState());
            for (String n : nodeSuccessors) {
                if (stateSets.contains(n))
                    continue;
                stateSets.add(n);
                Node child = new Node(n);
                currentNode.addChild(child);
                child.setParent(currentNode);
                successorsQueue.enqueue(child);

            }
            // Add the queue that contains the successors of the visited node to the beginning of the main queue
            mainQueue.addQueue(successorsQueue);
            // Successors queue should be cleared in order to store the next expanded's successors
            successorsQueue.clear();
            currentNode = mainQueue.dequeue();
            time += 1;
            nodeSuccessors.clear();
        }
        Utilities.printSolution(currentNode, stateSets, root, time);
    }

    /*
     * Depth First Search (with Iterative Deepening)
     * The depth limit starts from 1 and it increases to the defined value by user that passed to the method
     * */
    public void iterativeDeepening(int depthLimit) {
        System.out.println(">>> Depth First Search (with Iterative Deepening)\n");

        Node currentNode = new Node(root.getState());
        boolean solutionFound = false;
        // stateSet is a set that contains node that are already visited
        Set<String> stateSets = new HashSet<String>();
        Set<String> totalVisitedStates = new HashSet<String>();
        int time = 0;
        for (int maxDepth = 1; maxDepth < depthLimit; maxDepth++) {
            //we should clear the visited list in each iteration
            stateSets.clear();
            //the queue that store nodes that we should expand
            QueueUtils<Node> mainQueue = new QueueUtils<Node>();
            //the queue that stores the successors of the expanded node
            QueueUtils<Node> successorsQueue = new QueueUtils<Node>();
            Node node = new Node(root.getState());
            mainQueue.enqueue(node);
            currentNode = node;
            List<String> nodeSuccessors;
            stateSets.add(currentNode.getState());
            while (!mainQueue.isEmpty()) {
                currentNode = mainQueue.dequeue();
                time += 1;
                if (currentNode.getState().equals(goalSate)) {
                    solutionFound = true;
                    break;
                }
                if (currentNode.getDepth() < maxDepth) {
                    nodeSuccessors = Utilities.getNodeSuccessors(currentNode.getState());
                    for (String n : nodeSuccessors) {
                        if (stateSets.contains(n))
                            continue;

                        stateSets.add(n);
                        Node child = new Node(n);
                        currentNode.addChild(child);
                        child.setParent(currentNode);
                        child.setVisited(true);
                        child.setDepth(currentNode.getDepth() + 1);
                        successorsQueue.enqueue(child);

                    }
                    // Add the queue that contains the successors of the visited node to the beginning of the main queue
                    mainQueue.addQueue(successorsQueue);
                    // Successors queue should be cleared in order to store the next expanded's successors
                    successorsQueue.clear();
                }
            }
            if (solutionFound)
                break;
            totalVisitedStates.addAll(stateSets);
        }
        if (!solutionFound)
            System.out.println("No solution Found!");
        else {
            Utilities.printSolution(currentNode, totalVisitedStates, root, time);
        }
    }
}