import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HuffTree extends BinaryTree<Character>{
    
    /** This constructor creates a leaf node 
     * @param data the data for the node
    */
    public HuffTree(Character data){
        super(data);
    }

    /** This constructor creates a deep copy of the entire tree structure 
     * @param tree the tree to copy
     */ 
    public HuffTree(BinaryTree<Character> tree) {
        super(tree);
    }

    /** Override inherited manipulator to accept only HuffTree 
     * @param left the new left side
    */
    public void setLeft(HuffTree left) {
        if ((left==null)||(left instanceof HuffTree)) {
            super.setLeft(left);
        } else {
            throw new UnsupportedOperationException("Only HuffTree children allowed");
        }
    }

    /** Override inherited manipulator to accept only HuffTree 
     * @param right the new right side
    */
    public void setRight(HuffTree right) {
        if ((right==null)||(right instanceof HuffTree)) {
            super.setRight(right);
        } else {
            throw new UnsupportedOperationException("Only HuffTree children allowed");
        }
    }



    /**
     * Given a path getting a pointer at that node
     * @param path the path to follow
     * @return that nodes data
     */
    public Object getPath(String path){
        HuffTree currentNode = this;
        for(int i= 0; i < path.length(); i++){
            char currentLetter = path.charAt(i);
            if(currentLetter == '0'){
                if(currentNode.getLeft()== null){
                    currentNode.setLeft(new HuffTree('0'));
                }
                currentNode = (HuffTree) currentNode.getLeft();
            }else if(currentLetter== '1'){
                if(currentNode.getRight() == null){
                    currentNode.setRight(new HuffTree('0'));
                }
                currentNode= (HuffTree) currentNode.getRight();
            }else{
                throw new RuntimeException("Not a valid string.");
            }
        }
        return currentNode;
    }

    /**
     * Recursively Searching the tree to find a target generating a path string while searching
     * @param currentNode that we are at
     * @param targetNodeData which we want to find
     * @param finalString the current path string
     * @return the final path string
     */
    public String findPath(HuffTree currentNode, Object targetNode, String finalString){
        if(currentNode == null){
            return null;
        }
        if(currentNode.getData().equals(targetNode)){
            return finalString;
            //int value = Integer.parseInt("101");
        }
        String resultL = findPath((HuffTree) currentNode.getLeft(), targetNode, finalString + "0");
        if(resultL != null){
            return resultL;
        }
        String resultR = findPath((HuffTree) currentNode.getRight(), targetNode, finalString + "1");
        if(resultR != null){
            return resultR;
        }
        return null;
    }

        /**
     * Turning a file into a Decision Tree
     * @param filename the file you want to read
     * @return Huff tree which is created from the file
     */
    public static HuffTree readHuffTree(String filename){
        try (Scanner scanner = new Scanner(new File(filename))) {
            HuffTree finalTree;
            Character data = '0';
            finalTree = new HuffTree(data);
            while (scanner.hasNextLine()) {
                //Get the line and break it apart
                String line = scanner.nextLine();

                //Getting elements
                String[] split = line.split(" ");
                String path = split[0];
                path = path.substring(0, path.length()-1);
                int asciiInt = Integer.parseInt(split[1]);
                char letter = (char) asciiInt;
                String position = line.substring(line.indexOf(" ")-1, line.indexOf(" "));
                

                //Create node
                HuffTree current = new HuffTree(letter);
                HuffTree parent = finalTree;
                if(path.length() > 0){
                    parent = (HuffTree) finalTree.getPath(path);
                }
                if(position.equals("0")){
                    parent.setLeft(current);
                }
                if(position.equals("1")){
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
     * Used to debugg
     * @param args for main
     */
    public static void main(String[] args) {
        //HuffTree tree = readHuffTree("simple.txt");
        //System.out.println(tree);
    }
        
    }

    