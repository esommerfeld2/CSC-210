import java.util.Scanner;
/**
 * Twenty Question Game with Animals
 */
public class AnimalGuess{
    //Opens a scanner for the game
    private static final Scanner input = new Scanner(System.in);

    /**
     * Asks the user a question
     * @param message the question to ask
     * @return a boolean that if true means yes, and if false means no
     */
    public static boolean askUser(Object message){
        //Ask the question
        System.out.println(message);
        String response = input.nextLine();

        //Return true if they said yes; false if they said no
        Boolean yesOrNo = readResponse(message, response);
        return yesOrNo;
    }

    /**
     * A method that checks the users response
     * @param message the question asked
     * @param response the reponse given
     * @return if the response meant yes or no
     */
    public static boolean readResponse(Object message, String response){
        if(response.equals("Yes") || response.equals("yes") || response.equals("Y")|| response.equals("y")){
            return true;
        }else if(response.equals("No") || response.equals("no") || response.equals("N")|| response.equals("n")){
            return false;
        }else{
            System.out.println("Invalid Input Try Again.");
            return askUser(message);
        }
    }

    /**
     * Builds a tree after guessing wrong
     * @param current the current node that was wrong
     */
    public static void buildTree(DecisionTree current){
        System.out.println("I got it wrong.");
        System.out.println("Please help me to learn.");
        System.out.println("What was your animal?");
        String response = input.nextLine();
        System.out.println("Type a yes or no question that would distinguish between a " + response +  " and a " + current.getData());
        String newQuestion = input.nextLine();
        String message = "Would you answer yes to this question for the " + response + "?";
        boolean sideLeft = askUser(message);

        //Build tree
        DecisionTree animal = new DecisionTree(response);
        DecisionTree copyOfCurrent = new DecisionTree(current);
        current.setData(newQuestion);
        if(sideLeft){
            current.setLeft(animal);
            current.setRight(copyOfCurrent);
        }else{
            current.setRight(animal);
            current.setLeft(copyOfCurrent);
        }
    }

    /**
     * Main to run program
     * @param args for program
     */
    public static void main(String[] args) {
        //Creating a tree
        DecisionTree tree = DecisionTree.fileToTree("AnimalTree.txt");
        Boolean playing = true;
        while(playing){
            System.out.println("Think of an animal.");
            System.out.println("I'll try and guess it.");
            DecisionTree current = tree;
            while(current.isBranch()){
                //ask the user a question
                boolean yes;
                yes = askUser(current.getData());
                if(yes){
                    current = current.getLeft();
                }else{
                   current = current.getRight();
                }
            }
            boolean correct;
            String question = "Is your animal a(n) " + current.getData() +"?";
            correct = askUser(question);
            if(correct){
                System.out.println("I guessed it");
            }else{
                buildTree(current);
            }
            tree.treeToFile("AnimalTree.txt");
            playing = askUser("Play again?");
        }
    }
}