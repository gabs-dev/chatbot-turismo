package Util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static List<String> readStopWordsFile() {
        // Fonte stopWords.txt: https://github.com/stopwords-iso/stopwords-pt/blob/master/stopwords-pt.txt
        List<String> stopWords = reader("stopWords");

        return stopWords;
    }
    public static List<String> readExpectedWordsFile() {
        List<String> expectedWords = reader("expectedWords");

        return expectedWords;
    }

    public static List<String> readResponseDocuments(String docName) {
        String path = ("docs/docName.txt").replace("docName", docName);

        List<String> lines = new ArrayList<>();

        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isBlank() || !line.isEmpty())
                    lines.add(line);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static <T> void createFile(List<T> data, String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            for (T value : data) {
                bw.write(value.toString());
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    public static List<String> reader(String fileName) {
        List<String> words = new ArrayList<>();
        fileName += ".txt";

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String word;

            while((word = br.readLine()) != null) {
                words.add(word.toLowerCase());
            }

            br.close();
        } catch (IOException e) {
            System.err.println("Arquivo " + fileName + " não encontrado!");
        }

        return words;
    }

}
