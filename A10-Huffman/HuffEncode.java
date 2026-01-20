import java.util.Scanner;

public class HuffEncode {
    /**
     * Main method of file
     * @param args to be read
     */
    public static void main(String[] args) {
        HuffTree tree = HuffTree.readHuffTree("DefaultTree.txt");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for(int i = 0; i < line.length(); i++ ){
                HuffTree current = tree;
                char currentletter = line.charAt(i);
                String stringPath = tree.findPath(current, currentletter, "");
                System.out.print(stringPath);
            }
        }
        scanner.close();
    }
}
