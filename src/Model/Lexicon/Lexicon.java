package Model.Lexicon;

import Model.Syntactic.Syntactic;
import Util.ReadFiles;
import Util.StringHandler;

import java.util.*;

public class Lexicon {

    private String query;
    private List<String> symbolTable;
    private Queue<String> queue;
    private List<String> identifiers;

    public Lexicon() {
        symbolTable = new ArrayList<>();
        queue = new LinkedList<>();
        identifiers = new ArrayList<>(Arrays.asList("como", "qual", "para", "lista", "clima"));
    }

    public void init(String query) {
        removeStopWords(query);
        addInQueue();
        removeExpectedWords();
        addInSymbolTable();
    }

    public void checkAlphabet(String query) {
        query = StringHandler.processSpecialCharacters(query);

        ArrayList<String> words = StringHandler.splitQuery(query);

        for(int i = 0; i < words.size(); i++) {
            if (!isPartOfTheAlphabet(words.get(i)))
                words.remove(i);
        }

        this.query = StringHandler.concatenateWords(words);
    }

    public String removeStopWords(String query) {
        checkAlphabet(query);

        List<String> stopWords = ReadFiles.readStopWordsFile();

        List<String> allWords = StringHandler.splitQuery(this.query);

        allWords.removeAll(stopWords);
        this.query = StringHandler.concatenateWords(allWords);

        return this.query;
    }

    public String removeExpectedWords() {
        List<String> expectedWords = ReadFiles.readExpectedWordsFile();

        List<String> allWords = StringHandler.splitQuery(this.query);

        allWords.removeAll(expectedWords);

        this.query = StringHandler.concatenateWords(allWords);

        return this.query;
    }

    public void addInSymbolTable() {
        List<String> words = StringHandler.splitQuery(this.query);
        words.removeAll(identifiers);
        symbolTable.addAll(words);
    }

    public void addInQueue() {
        queue.addAll(StringHandler.splitQuery(this.query));
    }

    public void addInQueue(List<String> tokens) {
        queue.addAll(tokens);
    }

    private boolean isPartOfTheAlphabet(String content) {
        String regex = "[\\p{L}\\p{N}\\p{P}\\p{Zs}@]+";

        if (content.matches(regex))
            return true;

        return false;
    }

    public List<String> getSymbolTable() {
        return symbolTable;
    }

    public Queue<String> getQueue() {
        return queue;
    }
}