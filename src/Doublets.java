import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Your Name (you@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2019-03-29
 */
public class Doublets implements WordLadderGame {

    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
    /////////////////////////////////////////////////////////////////////////////
    // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
    // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
    // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
    // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
    // table with chaining).
    /////////////////////////////////////////////////////////////////////////////
    HashSet<String> lexicon;

    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
    public Doublets(InputStream in) {
        try {
            //////////////////////////////////////
            // INSTANTIATE lexicon OBJECT HERE  //
            //////////////////////////////////////
            lexicon = new HashSet<>();
            Scanner s =
                new Scanner(new BufferedReader(new InputStreamReader(in)));
            while (s.hasNext()) {
                String str = s.next();
                /////////////////////////////////////////////////////////////
                // INSERT CODE HERE TO APPROPRIATELY STORE str IN lexicon. //
                /////////////////////////////////////////////////////////////
                lexicon.add(str.toUpperCase());
                s.nextLine();
            }
            in.close();
        }
        catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }
    }

    @Override
    public int getHammingDistance(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return -1;
        }
        int hammingDistance = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                hammingDistance++;
            }
        }
        return hammingDistance;
    }

    @Override
    public List<String> getMinLadder(String start, String end) {
        HashSet<String> visited = new HashSet<>();
        Deque<Node> queue = new ArrayDeque<>();
        Node n = new Node(start, null);
        queue.addLast(n);
        visited.add(n.word);
        ArrayList<String> wordLadder = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node n2 = queue.removeFirst();
            if (n2.word.equals(end.toUpperCase())) {
                wordLadder.add(end);
                while (n2.previous != null) {
                    n2 = n2.previous;
                    wordLadder.add(n2.word);
                }
            }
            String word = n2.word;
            for (String neighbor : getNeighbors(word)) {
                if (!visited.contains(neighbor)) {
                    Node n3 = new Node(neighbor, n2);
                    visited.add(n3.word);
                    queue.addLast(new Node(n3.word, n2));
                }
            }

        }
        return wordLadder;
    }

    @Override
    public List<String> getNeighbors(String word) {
        word = word.toUpperCase();
        ArrayList<String> neighbors = new ArrayList<>();
        for (int letter = 0; letter < word.length(); letter++) {
            StringBuilder wordBuild = new StringBuilder(word);
            for (int alphabet = 65; alphabet < (65 + 26); alphabet++) {
                char currentAlphabetLetter = (char) alphabet;
                String str = "" + currentAlphabetLetter;
                wordBuild.replace(letter, letter + 1, str);
                String candidate = wordBuild.toString();
                if (!candidate.equals(word) && isWord(candidate)) {
                    neighbors.add(candidate);
                }
            }
        }
        return neighbors;
    }

    @Override
    public int getWordCount() {
        return lexicon.size();
    }

    @Override
    public boolean isWord(String str) {
        return lexicon.contains(str.toUpperCase());
    }

    @Override
    public boolean isWordLadder(List<String> sequence) {
        if (sequence.isEmpty()) {
            return false;
        }
        for (String str : sequence) {
            if (!isWord(str.toUpperCase())) {
                return false;
            }
        }
        for (int i = 0; i < sequence.size() - 1; i++) {
            String str1 = sequence.get(i);
            String str2 = sequence.get(i + 1);
            int hammingDistance = getHammingDistance(str1, str2);
            if (hammingDistance != 1) {
                return false;
            }
        }
        return true;
    }

    class Node {
        String word;
        Node previous;

        public Node(String word, Node previous) {
            this.word = word;
            this.previous = previous;

        }
    }


    //////////////////////////////////////////////////////////////
    // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
    //////////////////////////////////////////////////////////////


}

