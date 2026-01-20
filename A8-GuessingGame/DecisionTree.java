import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Uses Binary Trees to Create Decision Trees
 */
public class DecisionTree extends BinaryTree<Object>{

    /**
     * Simply Constructor to create a leaf node
     * @param obj for node
     */
    public DecisionTree(Object obj){
        super(obj);
    }

    /**
     * Rewritten three value constructor
     * @param obj root data
     * @param left left data
     * @param right right data
     */
    public DecisionTree(Object obj, BinaryTree<Object> left, BinaryTree<Object> right){
        super(obj);
        this.setLeft(left);
        this.setRight(right);
    }

    /**
     * Overwritten Copy Constructor (Shallow)
     * @param tree to be copied
     */
    public DecisionTree(DecisionTree tree){
        super(tree.getData());
        this.setLeft(tree.getLeft());
        this.setRight(tree.getRight());
        
    }

    /**
     * Overwritten Manipulator Left
     * @param left node to set
     */
    public void setLeft(BinaryTree<Object> left){
        if(left == null || left instanceof DecisionTree){
            super.setLeft(left);
        }else{
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Overwritten Manipulator Right
     * @param right node to set
     */
    public void setRight(BinaryTree<Object> right){
        if(right == null || right instanceof DecisionTree){
            super.setRight(right);
        }else{
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Overwritten Accessor Left
     */
    public DecisionTree getLeft(){
        return (DecisionTree)super.getLeft();
    }

    /**
     * Overwritten Accessor Right
     */
    public DecisionTree getRight(){
        return (DecisionTree)super.getRight();
    }

    /**
     * Given a path getting a pointer at that node
     * @param path the path to follow
     * @return a pointer at that node
     */
    public DecisionTree getPath(String path){
        DecisionTree currentNode;
        currentNode = new DecisionTree(this);
        for(int i= 0; i < path.length(); i++){
            char currentLetter = path.charAt(i);
            if(currentLetter == 'Y'){
                currentNode = currentNode.getLeft();
            }else if(currentLetter== 'N'){
                currentNode= currentNode.getRight();
            }else{
                throw new RuntimeException("Not a valid string.");
            }
        }
        return currentNode;
    }
    /**
     * Recursively Searching the tree to find a target generating a path string while searching
     * @param currentNode that we are at
     * @param targetNode which we want to find
     * @param finalString the current path string
     * @return the final path string
     */
    public static String findPath(DecisionTree currentNode, DecisionTree targetNode, String finalString){
        if(currentNode == null){
            return null;
        }
        if(currentNode == targetNode){
            return finalString.toString();
        }
        String resultL = findPath(currentNode.getLeft(), targetNode, finalString + "Y");
        if(resultL != null){
            return resultL;
        }
        String resultR = findPath(currentNode.getRight(), targetNode, finalString + "N");
        if(resultR != null){
            return resultR;
        }
        return null;
    }

    /**
     * Turning a tree into a file
     * @param filename of the file you want to create
     */
    public void treeToFile(String filename){
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            Queue<DecisionTree> queue = new LinkedList<>();
            out.println(" " + this.getData());
            if(this.getLeft()!= null){
                    queue.add(this.getLeft());
                }
                if(this.getLeft() != null){
                    queue.add(this.getRight());
                }
            while(!queue.isEmpty()){
                DecisionTree currentNode = queue.remove();
                if(currentNode.getLeft()!= null){
                    queue.add(currentNode.getLeft());
                }
                if(currentNode.getLeft() != null){
                    queue.add(currentNode.getRight());
                }
                String s = "";
                String path = findPath(this, currentNode, s);
                out.println(path + " " + currentNode.getData());
            }
            out.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Turning a file into a Decision Tree
     * @param filename the file you want to read
     * @return Decision tree which is created from the file
     */
    public static DecisionTree fileToTree(String filename){
        try (Scanner scanner = new Scanner(new File(filename))) {
            DecisionTree finalTree;
            String line = scanner.nextLine();
            String data = line.substring(line.indexOf(" ")+1);
            finalTree = new DecisionTree(data);
            while (scanner.hasNextLine()) {
                //Get the line and break it apart
                line = scanner.nextLine();
                String path = line.substring(0,line.indexOf(" ")-1);
                String position = line.substring(line.indexOf(" ")-1, line.indexOf(" "));
                data = line.substring(line.indexOf(" ")+1);

                //Create node
                DecisionTree current = new DecisionTree(data);
                DecisionTree parent = finalTree;
                if(path.length() > 0){
                    parent = finalTree.getPath(path);
                }
                if(position.equals("Y")){
                    parent.setLeft(current);
                }
                if(position.equals("N")){
                    parent.setRight(current);
                }
            }
            return finalTree;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Main method where I messed around with building and testing Decision Trees
     * @param args for main
     */
    public static void main(String[] args) {
        //Want final tree to be:
        //Root: Root 1
        //Left: Root 2
        //Right: Root 3
        //Root: Root 2
        //Left: Leaf 4
        //Right:Leaf 6
        //Root: Root 3
        //Right: Root 5
        //Root: Root 5
        //Leaf: Leaf 7

        //Objects
        Object Root1 = "root 1";
        Object Root2 = "root 2";
        Object Root3 = "root 3";
        Object Leaf4 = "leaf 4";
        Object Root5 = "root 5";
        Object Leaf6 = "leaf 6";
        Object Leaf7 = "leaf 7";

        //Leaf Trees
        DecisionTree dleaf4 = new DecisionTree(Leaf4);
        DecisionTree dleaf6 = new DecisionTree(Leaf6);
        DecisionTree dleaf7 = new DecisionTree(Leaf7);

        //Making Final Tree
        DecisionTree evenTree = new DecisionTree(Root2, dleaf4, dleaf6);
        DecisionTree oddTree57 = new DecisionTree(Root5, dleaf7, null);
        DecisionTree oddTree = new DecisionTree(Root3, null, oddTree57);
        DecisionTree finalTree = new DecisionTree(Root1, evenTree, oddTree);
        System.out.println(finalTree);
        
        //Messing Around with Methods in Tree
        System.out.println(finalTree.getLeft().getRight().getData());
        System.out.println(finalTree.getPath("YN"));
        System.out.println(finalTree.getPath("NNY"));
        
        //Testing method
        DecisionTree Copyofdleaf4 = new DecisionTree(dleaf4);
        System.out.println(Copyofdleaf4);
        Copyofdleaf4.setLeft(dleaf7);
        System.out.println(Copyofdleaf4);
        System.out.println(dleaf4);

        //Testing Tree to file
        //String a = "A";
        //String b = "B";
        //String c = "C";
        //String d = "D";
        //String e = "E";
        //String f = "F";
        //String g = "G";
        //DecisionTree dTree = new DecisionTree(d);
        //DecisionTree eTree = new DecisionTree(e);
        //DecisionTree fTree = new DecisionTree(f);
        //DecisionTree gTree = new DecisionTree(g);

        //Building Tree
        //DecisionTree leftTree = new DecisionTree(b, dTree, eTree);
        //DecisionTree rightTree = new DecisionTree(c, fTree, gTree);
        //DecisionTree aTree = new DecisionTree(a, leftTree, rightTree);

        //Testing Tree to file
       //System.out.println(findPath(aTree, aTree.getLeft(), ""));
       //aTree.treeToFile("AnimalTree.txt");
       //System.out.println(fileToTree("AnimalTree.txt"));
    }
}