/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestfirstsearch;

/**
 *
 * @author Nimra Aziz
 */
public class BestFirstSearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int values[][] = {{1, -1, 3}, {2, 4, 5}, {6, 7, 8}};//almost unsolve able goal change this to sir wala goal
        int goal[][] = {{1,2,3}, {8,-1,4}, {7,6,5}};
        Node v=new Node(values);
        Node g=new Node(goal);
        Tree puzzle=new Tree(v,g);
        puzzle.Best_First_Search(puzzle.root);
        puzzle.display(puzzle.root);
        System.out.println("Node ID of Goal:"+puzzle.goal_node_id);
    }
    
}
