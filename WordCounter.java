/**
 * Created by yasin_000 on 30.12.2017.
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WordCounter {
    public static void main(String args[]) {
        Map<String, Integer> tokenCounter = new HashMap<String, Integer>();
        Map<String, ArrayList<Integer>> tokens = new HashMap<>();
        Path path = Paths.get("text.txt");

        List<String> list = null;

        try {
            list = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer rowCounter = 1;
        for (String row : list) {
            StringTokenizer tk = new StringTokenizer(row,
                    "?:;'!>.)<,/}-0123456789”“’… ");
            while (tk.hasMoreTokens()) {
                String s = tk.nextToken().toLowerCase();
                if (s.length() == 0)  continue;
                Integer currentCount = tokenCounter.get(s);
                if (currentCount == null) currentCount = 0;
                tokenCounter.put(s, ++currentCount);
                if (tokens.containsKey(s)){
                    if(!tokens.get(s).contains(rowCounter))
                        tokens.get(s).add(rowCounter);
                }
                else {
                    ArrayList<Integer> arrList = new ArrayList<Integer>();
                    arrList.add(rowCounter);
                    tokens.put(s, arrList);
                }
            }
            rowCounter++;

        }

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the word you want to search: ");
        String word = scan.nextLine();
        scan.close();
        ArrayList<Integer> lines = tokens.get(word.toLowerCase());
        if (lines != null) {
            System.out.println("Kelime var. Ayrýca þu satýrlarda geçiyor: ");
            for (Integer i : lines)
                System.out.print(i + "   ");
            System.out.println("\n It passes" + tokenCounter.get(word.toLowerCase())
                    + " times in the file");
        } else
            System.out.println("There is no such word");
    }

}