package Util;

import Model.Document;
import Model.Word;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Indexing {

    private static Map<String, String> invertedFile;
    private static List<String> responseDocuments = getResponseDocumentsNames();
    private static final int  NUMBER_OF_RESPONSE_FILES = 7;

    public static void createInvertedFile() {
        invertedFile = new HashMap<>();
        List<Document> docs = new LinkedList<>();
        List<String> expectedWords = FileHandler.readExpectedWordsFile();
        List<Word> words = new LinkedList<>();

        if (!(new File("invertedFile.txt").exists())) {
            for (String doc : responseDocuments)
                docs.add(new Document(doc, FileHandler.readResponseDocuments(doc)));

            for (String word : expectedWords) {
                Word w = new Word(word);
                for (Document doc : docs) {
                    if (checkIfWordOccursInDocument(word, doc))
                        w.getDocuments().add(doc.getName());
                }
                words.add(w);
            }

            words = words.stream().distinct().collect(Collectors.toList());

            FileHandler.createFile(words, "invertedFile.txt");
        }
    }

    public static List<Word> loadInvertedFile() {
        if (!(new File("invertedFile.txt").exists()))
            createInvertedFile();

        List<Word> invertedFile = new LinkedList<>();
        List<String> file = FileHandler.reader("invertedFile");

        if (file.isEmpty() || file == null)
            throw new NullPointerException();

        for (String line : file) {
            String[] word = line.split("\\|");
            String[] docs = word[word.length - 1].isBlank() ? null :  word[word.length - 1].trim().split(" ");
            Word wordTemp = new Word(word[0].trim());
            if (docs != null) wordTemp.getDocuments().addAll(Arrays.asList(docs));
            invertedFile.add(wordTemp);
        }

        return invertedFile;
    }

    private static boolean checkIfWordOccursInDocument(String word, Document document) {
        boolean occursInDocument = false;
        List<String> lines = document.getLines();

        for (String line : lines)
            if (line.toLowerCase().contains(word))
                occursInDocument = true;

        return occursInDocument;
    }

    private static List<String> getResponseDocumentsNames() {
        List<String> docs = new ArrayList<>();

        for (int i = 1; i <= NUMBER_OF_RESPONSE_FILES; i++)
            docs.add("d" + i);

        return docs;
    }

    public static int numberOfResponseFiles() {
        return NUMBER_OF_RESPONSE_FILES;
    }

}
