package Model.Translator;

import Model.Document;
import Model.Response;
import Model.Word;
import Util.FileHandler;
import Util.Indexing;
import Util.TFIDFCalculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static Model.Enum.QueryClassification.*;

public class Translator {

    private Queue<String> tokens;

    public Translator() {
    }

    public Translator(Queue<String> tokens) {
        this.tokens = tokens;
    }

    public Response generateAnswer() {
        // Palavras com frequências próximas de 0 aparecem em vários arquivos.
        // Indetificar a palavra que possui o valor de frequência maior,
        // pois aparecem em menos arquivos.
        // Comecar a construir a resposta a partir daí.

        // Identificar a palavra com a melhor frequência, comparar com quais arquivos das outras palavras elas coincidem
        // e retornar a linha do arquivo com a provavel resposta.

        // Percorrer cada linha do arquivo identificando se ela dá match com todos os tokens.
        // Se sim, essa é a linha com a provavel resposta.

        List<Word> wordsFromTokensList = wordsFromTokensList();

        for (Word word : wordsFromTokensList) {
            String doc = word.getDocuments().get(0);
            Document document = new Document(doc, FileHandler.readResponseDocuments(doc));
            word.setFrequency(TFIDFCalculator.tfIdf(word, document));
        }

        Word maxFrequencyWord = findMaxFrequencyWord(wordsFromTokensList);

        List<String> docs = findCommonDocuments(wordsFromTokensList, maxFrequencyWord);

        wordsFromTokensList.remove(maxFrequencyWord);

        for (String doc : docs) {
            List<String> responseDoc = FileHandler.readResponseDocuments(doc);
            String answer = findAnswer(responseDoc, maxFrequencyWord, wordsFromTokensList);

            if (answer != null)
                return new Response(OK, answer);
        }

        return new Response(ERROR, "Não entendi.");
    }

    private List<Word> wordsFromTokensList() {
        List<Word> invertedFile = Indexing.loadInvertedFile();
        List<Word> words = new LinkedList<>();

        for (String token : tokens) {
            for (Word word : invertedFile) {
                if (token.equalsIgnoreCase(word.getWord())) {
                    words.add(word);
                    break;
                }
            }
        }

        return words;
    }

    /**
     * Encontra a palavra que posui o maior valor de frequência.
     * Palavras com valor de frequência maiores ocorrem em menos documentos.
     * @param words
     * @return Retorna a word que possui o maior valor de frequência.
     */
    private Word findMaxFrequencyWord(List<Word> words) {
        Word maxFrequencyWord = null;
        double maxFrequency = 0;

        for (Word word : words) {
            if (word.getFrequency() > maxFrequency) {
                maxFrequency = word.getFrequency();
                maxFrequencyWord = word;
            }
        }

        return maxFrequencyWord;
    }

    private List<String> findCommonDocuments(List<Word> wordList, Word maxFrequencyWord) {
        List<String> commonDocuments = new ArrayList<>(maxFrequencyWord.getDocuments());

        for (Word word : wordList) {
            if (word != maxFrequencyWord) {
                commonDocuments.retainAll(word.getDocuments());
            }
        }

        return commonDocuments;
    }

    private String findAnswer(List<String> doc, Word maxFrequencyWord, List<Word> words) {

        for (String line : doc) {
            boolean allWordsPresent = true;
            if (line.toLowerCase().contains(maxFrequencyWord.getWord())) {
                if (words.size() == 0)
                    allWordsPresent = true;

                for (Word word : words) {
                    if (!line.toLowerCase().contains(word.getWord())) {
                        allWordsPresent = false;
                        break;
                    }
                }
            } else
                allWordsPresent = false;

            if (allWordsPresent)
                return line;
        }

        return null;
    }
}
