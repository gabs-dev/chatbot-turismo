package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Word {

    private String word;
    private List<String> documents;
    private double frequency;

    public Word() {

    }

    public Word(String word) {
        this.word = word;
        documents = new LinkedList<>();
    }

    public Word(String word, List<String> documents) {
        this.word = word;
        this.documents = documents;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word1 = (Word) o;
        return Objects.equals(getWord(), word1.getWord()) && Objects.equals(getDocuments(), word1.getDocuments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWord(), getDocuments());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(word + " | ");
        for (String document : documents)
            sb.append(document + " ");

        return sb.toString();
    }
}

