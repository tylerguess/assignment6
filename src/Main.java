import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        WordLadderGame doublets = new Doublets(new FileInputStream(new File("tiny.txt")));

//        System.out.println(doublets.getHammingDistance("tiger", "tiger"));
//        System.out.println(doublets.getHammingDistance("tiger", "eagle"));
//        System.out.println(doublets.getHammingDistance("war", "eagle"));
//        System.out.println(doublets.getHammingDistance("barner", "bammer"));
//
//        System.out.println(doublets.isWord("tiger"));
//        System.out.println(doublets.isWord("eagle"));
//        System.out.println(doublets.isWord("aubie"));
//
//        System.out.println(doublets.getWordCount());
//
//        System.out.println(doublets.isWordLadder(Arrays.asList("cat", "cot", "zot", "dot")));
//        System.out.println(doublets.isWordLadder(Arrays.asList("cat", "cot", "pot", "dot")));

//        System.out.println(doublets.getNeighbors("tiger"));

        System.out.println(doublets.getMinLadder("cat", "can"));
    }
}
