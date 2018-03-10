package bestfirstsearch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nimra Aziz
 */
public class Tree {

    private Deque<Node> Open;
    private Deque<Node> Close;
    public Node root;
    public static Node Goal;

    private Deque<Node> all_nodes;
     int goal_node_id;

    Tree(Node root, Node goal) {
        this.root = root;
        Tree.Goal = goal;
        goal_node_id=0;
        Open = new ArrayDeque<>();
        Close = new ArrayDeque<>();
        all_nodes = new ArrayDeque<>();
    }

    public void Best_First_Search(Node root) {
        Open.add(root);
//        System.out.println("root");
        // root.display();
        while (!Open.isEmpty()) {
            Node x = Open.poll();
            if (Arrays.deepEquals(x.box, Goal.box)) {
                goal_node_id=x.node_id;
                System.out.println("Success");
                return;
            } else {
                String moves = x.get_possible_moves();
                for (int i = 0; i < moves.length(); i++) {
                    Node newChild = x.queen_move(moves.charAt(i));
                    newChild.level = x.level + 1;
                    x.add_child(newChild);
                    Node cl = IsExist(newChild, Close);
                    Node op = IsExist(newChild, Open);
                    if (cl == null && op == null) {

                        newChild.heuristic_value = Calculate_hv(newChild, newChild.level);

                        Open.add(newChild);
                    }
//                    if (op != null) {
//                        if (op.heuristic_value > newChild.heuristic_value) {
//                            Open.add(newChild);
//                        }
//                        //op.display();
//                    
//                    }
//                    if (cl != null) {
//                         if (cl.heuristic_value > newChild.heuristic_value) {
//                            Open.add(newChild);
//                        }  
//                        // cl.display();
//                    }

                }

            }
            Close.add(x);
            Open = Sort(Open);
        
        }//end of while

        System.out.println("Goal Not found");
    }

    public Deque<Node> Sort(Deque<Node> op) {
        ArrayList<Node> templist = new ArrayList<>(op);
        for (int i = 0; i < templist.size(); i++) {
            for (int j = templist.size() - 1; j > i; j--) {
                if (templist.get(i).heuristic_value > templist.get(j).heuristic_value) {
                    Node temp = templist.get(i);
                    templist.set(i, templist.get(j));
                    templist.set(j, temp);

                }
            }
        }
        Deque<Node> queue = new ArrayDeque<>(templist);
        return queue;
    }

    public static int Calculate_hv(Node n, int level) {
        int displacements = 0;
        for (int i = 0; i < Goal.box.length; i++) {
            for (int j = 0; j < Goal.box.length; j++) {
                if (n.box[i][j] != Goal.box[i][j]) {
                    displacements++;
                }
            }
        }
        return displacements + level;

    }

    public Node IsExist(Node n, Deque<Node> list) {
        for (Node tempn : list) {
            if (Arrays.deepEquals(n.box, tempn.box)) {
                //es ko all nodes wali list pass krni e  System.out.println("isExist chal rah hai");
                return tempn;
            }
        }
        return null;
    }

    public void display(Node temp) {
        if (temp == null) {
            return;
        }
        temp.display();
        for (Node node : temp.get_child()) {
            display(node);
        }
    }
}
