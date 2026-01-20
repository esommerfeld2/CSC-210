import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Validates if words exist and gives spelling suggestions if not
 */
public class WordValidation implements SpellingOperations{
    //Attributes
    private HashSet<String> correctWords = new HashSet<>();

    /**
     * Constructor
     * @param filename of the dictionary you are using to spell check
     */
    public WordValidation(String filename){
        Scanner file = null;
        try {
        file = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.err.println("Cannot locate file.");
            System.exit(-1);
        }
        while (file.hasNextLine()) {
            String line = file.nextLine();
            correctWords.add(line);
        }
        file.close();
    }

   /**
    * Checks if the word is in the dictionary 
   *  @param query the word to check
   *  @return true if the query word is in the dictionary.
   */
  public boolean containsWord(String query){
    return correctWords.contains(query);
  }
  
  /**
   * Returns a list of all the words your word could be
   *  @param query the word to check
   *  @return a list of all valid words that are one edit away from the query
   */
public ArrayList<String> nearMisses(String query){

    //Defining structures
    ArrayList<String> missSpelledWords = new ArrayList<>();
    HashSet<String> missSpelled = new HashSet<>();
    String abcs = "abcdefghijklmnopqrstuvwxyz";
    query= query.toLowerCase();
    query = query.replaceAll("[^a-zA-Z]", "");
    if(containsWord(query)){
        return missSpelledWords;
    }

    //To get all letters before and after i
    String wordHead;
    String wordTail;
    for(int i = 0; i < query.length(); i++){
        //Create a string which is all the letters before the one we are at
        wordHead = "";
        for(int j = 0; j < i; j++){
            String current = Character.toString(query.charAt(j));
            wordHead= wordHead + current;
        }
        //Our letter
        String currentLetter = Character.toString(query.charAt(i));
        //Create a string which is all the letters after the one we are at
        wordTail = "";
        for(int j = i+1; j < query.length(); j++){
            String current = Character.toString(query.charAt(j));
            wordTail= wordTail + current;
        }
        //wordHead + currentLetter+ wordTail should be equal to query

        //Insertions:
        for(int j= 0; j < abcs.length(); j++){
            String insert = wordHead + currentLetter + Character.toString(abcs.charAt(j)) + wordTail;
            if(containsWord(insert)){
                if(!missSpelled.contains(insert)){
                    missSpelledWords.add(insert);
                    missSpelled.add(insert);
                }
            }
            if(i== 0){
                insert = Character.toString(abcs.charAt(j)) + currentLetter + wordTail;
                if(containsWord(insert)){
                    if(!missSpelled.contains(insert)){
                        missSpelledWords.add(insert);
                        missSpelled.add(insert);
                    }
                }
            }
        }

        //Deletations 
        String delete = wordHead + wordTail;
        if(containsWord(delete)){
                if(!missSpelled.contains(delete)){
                    missSpelledWords.add(delete);
                    missSpelled.add(delete);
                }
            }
        
        //Substitutions 
        for(int j= 0; j < abcs.length(); j++){
            String substitute = wordHead + Character.toString(abcs.charAt(j)) + wordTail;
            if(containsWord(substitute)){
                if(!missSpelled.contains(substitute)){
                    missSpelledWords.add(substitute);
                    missSpelled.add(substitute);
                }
            }
        }

        //Splits
        String splitHigh= wordHead + currentLetter;
        String splitHighWord= wordHead + currentLetter + " " + wordTail;
        String splitLow= currentLetter + wordTail;
        String splitLowWord= wordHead +  " " + currentLetter + wordTail;
        if(containsWord(splitHigh) & containsWord(wordTail)){
                if(!missSpelled.contains(splitHighWord)){
                    missSpelledWords.add(splitHighWord);
                    missSpelled.add(splitHighWord);
                }
            }
        if(containsWord(wordHead) & containsWord(splitLow)){
                if(!missSpelled.contains(splitLowWord)){
                    missSpelledWords.add(splitLowWord);
                    missSpelled.add(splitLowWord);
                }
            }
    }
    //Transpositions
    for(int i = 0; i < query.length()-1; i++){
        //Create a string which is all the letters before the one we are at
        wordHead = "";
        for(int j = 0; j < i; j++){
            String current = Character.toString(query.charAt(j));
            wordHead= wordHead + current;
        }
        //Our letter
        String currentLetter = Character.toString(query.charAt(i));
        String letterAhead = Character.toString(query.charAt(i+1));
        //Create a string which is all the letters after the one we are at
        wordTail = "";
        for(int j = i+2; j < query.length(); j++){
            String current = Character.toString(query.charAt(j));
            wordTail= wordTail + current;
        }
        //Now query should equal wordHead + currentLetter + letterAhead + wordTail
        String transposition = wordHead + letterAhead + currentLetter + wordTail;
        if(containsWord(transposition)){
                if(!missSpelled.contains(transposition)){
                    missSpelledWords.add(transposition);
                    missSpelled.add(transposition);
                }
            }
    }


    return missSpelledWords;
  }

/**
 * Main method (with all my tests)
 * @param args for main
 */
  public static void main(String[] args) {
    WordValidation test = new WordValidation("words.txt");

    //Deletions
    //System.out.println(test.nearMisses("catttle")); // middle letter
    //System.out.println(test.nearMisses("wwonder")); // beginning letter
    //System.out.println(test.nearMisses("wonderr")); // end letter
    
    //Insertions
    //System.out.println(test.nearMisses("catle")); // middle letter
    //System.out.println(test.nearMisses("onder")); // begininng letter
    //System.out.println(test.nearMisses("wonde")); // middle letter

    //Substiutions
    //System.out.println(test.nearMisses("caxtle")); // middle letter
    //System.out.println(test.nearMisses("xonder")); // begininng letter
    //System.out.println(test.nearMisses("wondex")); // middle letter

    //Transpositions
    //System.out.println(test.nearMisses("cattel")); // middle letter
    //System.out.println(test.nearMisses("ownder")); // begininng letter
    //System.out.println(test.nearMisses("wondre")); // middle letter

    //Splits
    System.out.println(test.nearMisses("cattell")); // middle letter
    System.out.println(test.nearMisses("alover")); // begininng letter
    System.out.println(test.nearMisses("wisha")); // middle letter

    //Checking caps and punctuation
    //System.out.println(test.nearMisses("ONDER"));
    //System.out.println(test.nearMisses("onder."));
  }
}