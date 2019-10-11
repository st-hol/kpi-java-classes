package ua.procamp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * {@link FileStats} provides an API that allow to get character statistic based on text file. All whitespace characters
 * are ignored.
 */
public class FileStats {

    private URI path;

    public void setPath(URI path) {
        this.path = path;
    }

    /**
     * Creates a new immutable {@link FileStats} objects using data from text file received as a parameter.
     *
     * @param fileName input text file name
     * @return new FileStats object created from text file
     */
    public static FileStats from(String fileName) throws URISyntaxException {
        URI path;
        try {
            path = Objects.requireNonNull(FileStats.class
                    .getClassLoader().getResource(fileName)).toURI();
//            File file = new File(path);
        } catch (NullPointerException ex) {
            throw new FileStatsException("surprise!");
        }
        return new FileStats() {{
            setPath(path);
        }};
    }

    /**
     * Returns a number of occurrences of the particular character.
     *
     * @param character a specific character
     * @return a number that shows how many times this character appeared in a text file
     */
    public int getCharCount(char character) {
        Scanner scannerFile;

        try {
            System.out.println(path);
            scannerFile = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new FileStatsException("surprise!");
        }

        int count = 0;
        while (scannerFile.hasNext()) {
            String str = scannerFile.next();
            int index = 0;
            while (index < str.length()) {
                if (str.charAt(index) == character) {
                    count++;
                }
                index++;
            }
//            System.out.println(starNumber);
        }
        return count;
    }

    /**
     * Returns a character that appeared most often in the text.
     *
     * @return the most frequently appeared character
     */
    public char getMostPopularCharacter() {
        HashMap<Character, Integer> wordCountMap = new HashMap<>();
        Character mostRepeatedWord = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(path.getPath()))) {
            // uri
            String currentLine = reader.readLine();
            while (currentLine != null) {
                String line = currentLine.toLowerCase();//.split(" ");
                //Iterating each symb
                for (Character symb : line.toCharArray()) {
                    if (symb == ' ') {      // skips spaces if needed
                        continue;
                    }
                    if (wordCountMap.containsKey(symb)) {
                        wordCountMap.put(symb, wordCountMap.get(symb) + 1);
                    } else {
                        wordCountMap.put(symb, 1);
                    }
                }
                currentLine = reader.readLine();
            }
            mostRepeatedWord = getKeyByBiggestValue(wordCountMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mostRepeatedWord;
    }


    private char getKeyByBiggestValue(Map<Character, Integer> map) {
        Character maxKey = null;
        for (Character key : map.keySet()) {
            if (maxKey == null || map.get(key) > map.get(maxKey)) {
                maxKey = key;
            }
        }
        return maxKey;
    }

    /**
     * Returns {@code true} if this character has appeared in the text, and {@code false} otherwise
     *
     * @param character a specific character to check
     * @return {@code true} if this character has appeared in the text, and {@code false} otherwise
     */
    public boolean containsCharacter(char character) {
        HashMap<Character, Integer> wordCountMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.getPath()))) {
            // uri
            String currentLine = reader.readLine();
            while (currentLine != null) {
                String line = currentLine.toLowerCase();//.split(" ");
                //Iterating each symb
                for (Character symb : line.toCharArray()) {
                    if (symb == ' ') {      // skips spaces if needed
                        continue;
                    }
                    if (symb == character) {      // skips spaces if needed
                        return true;
                    }
                    if (wordCountMap.containsKey(symb)) {
                        wordCountMap.put(symb, wordCountMap.get(symb) + 1);
                    } else {
                        wordCountMap.put(symb, 1);
                    }
                }
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
