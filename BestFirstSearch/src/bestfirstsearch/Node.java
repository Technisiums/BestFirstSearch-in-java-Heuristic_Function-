package bestfirstsearch;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nimra Aziz
 */
public class Node {

    public int box[][];
    public ArrayList<Node> children;
    public int queen[];
    public int heuristic_value;
    public int level;
    int node_id;
    public static int id = 0;

    Node(int values[][]) {
        queen = new int[2];
        box = values;
        this.heuristic_value = -1;
        children = new ArrayList<Node>();
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (values[i][j] == -1) {
                    queen[0] = i;
                    queen[1] = j;
                    break;
                }
            }
        }
        id++;
       // System.out.println(id);
        node_id = id;
        level = 0;
    }

    public String get_possible_moves() {
        String moves = "";
        switch (queen[0]) {
            case 0:
                moves += "d";
                break;
            case 1:
                moves += "ud";
                break;
            case 2:
                moves += "u";
                break;
        }
        switch (queen[1]) {
            case 0:
                moves += "r";
                break;
            case 1:
                moves += "lr";
                break;
            case 2:
                moves += "l";
                break;
        }
        return moves;
    }

    public void add_child(Node child) {
        children.add(child);//add child of root in the list
    }

    public ArrayList<Node> get_child() {
        return children; //to get the children list
    }

    public Node queen_move(char move) {
        int si = 0;
        int sj = 0;

        switch (move) {
            case 'u':
                si = queen[0] - 1;
                sj = queen[1];
                break;
            case 'd':
                si = queen[0] + 1;
                sj = queen[1];
                break;
            case 'l':
                si = queen[0];
                sj = queen[1] - 1;
                break;
            case 'r':
                si = queen[0];
                sj = queen[1] + 1;
                break;

        }

        return swap(queen[0], queen[1], si, sj);

    }

    public Node swap(int qi, int qj, int si, int sj) {
        int[][] newValues = new int[3][3];
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box.length; j++) {
                newValues[i][j] = box[i][j];
            }
        }
        //create child
        Node child = new Node(newValues);

        int x = child.box[qi][qj];//queen position
        child.box[qi][qj] = child.box[si][sj];
        child.box[si][sj] = x;

        //update queen positions
        child.queen[0] = si;
        child.queen[1] = sj;
        return child;
    }

    public void display() {
        String child_ids = "";
        for (Node temp : children) {
            child_ids += temp.node_id + " ";
        }
        if (child_ids.isEmpty()) {
            child_ids = "Leaf Node";
        }
        System.out.println("Node_id:" + node_id  + " Child IDs:" + child_ids+ " H.V=" + heuristic_value + " Level:" + level);
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box.length; j++) {
                System.out.print(box[i][j] + " ");
            }
            System.out.println("");
        }

    }

}