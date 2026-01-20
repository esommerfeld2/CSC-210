import java.util.Scanner;

public class HuffDecode {

    /**
     * Main method of file
     * @param args to be read
     */
    public static void main(String[] args) {
        HuffTree tree = HuffTree.readHuffTree("DefaultTree.txt");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            StringBuilder currentPath = new StringBuilder();
            int count = 0;
            for(int i = 0; i < line.length(); i++ ){
                char currentNumber = line.charAt(i);
                currentPath.append(currentNumber);
                String currentPathString = currentPath.toString();
                HuffTree letter = (HuffTree) tree.getPath(currentPathString);
                count ++;
                if(letter.getData() != '0'){
                    System.out.print(letter.getData());
                    currentPath.delete(0, count);
                    count = 0;
                }
            }
        }
        scanner.close();
    }
    
}
