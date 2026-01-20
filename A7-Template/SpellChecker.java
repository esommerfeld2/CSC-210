import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Interacts with user about checking spelling
 */
public class SpellChecker{

    /**
     * Main method
     * @param args what string you are checking for spelling errors
     */
    public static void main(String[] args) {
        //Setting up things needed in both
        WordValidation dictionary = new WordValidation("words.txt");
        ArrayList<String> suggestions = new ArrayList<>();


        if(args.length == 0){
            HashSet<String> missSpelled = new HashSet<>();
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNext()){
                String currentWord = scanner.next();
                suggestions= dictionary.nearMisses(currentWord);
                if(suggestions.isEmpty()){
                    //be silent
                }else{
                    if(!missSpelled.contains(currentWord)){
                        System.out.println("Not found: " + currentWord);
                        System.out.println("Suggestions: " + suggestions);
                        missSpelled.add(currentWord);
                    }
                }
            }
            scanner.close();
        }
        for(int i = 0; i < args.length; i++){
            //Setting up constants
            String currentWord = args[i];

            //Method
            suggestions= dictionary.nearMisses(currentWord);
            if(suggestions.isEmpty()){
                System.out.println(currentWord + " is spelled correctly!");
            }else{
                System.out.println("Not found: " + currentWord);
                System.out.println("Suggestions: " + suggestions);
            }
        }
    }
}