package Util;

import Model.Document;
import Model.Word;

import java.util.List;

public class TFIDFCalculator {

    /**
     * Calcula o número de vezes em que um termo ocorre em um documento.
     * @param term
     * @param d
     * @return
     */
    public static double tf(Word term, Document d) {
        double result = 0;

        List<String> lines = d.getLines();

        for (String line : lines) {
            String[] aux = line.split(" ");
            for (String word : aux)
                if (word.toLowerCase().contains(term.getWord()))
                    result++;
        }

        return result / d.getSize();
    }

    /**
     * Calcula a importância de um termo em relação a todos os documentos.
     * É calculado através da divisão total do número de documentos pelo número de documentos que contém o termo
     * e então é calculado o logaritmo do resultado.
     * @param term
     * @return
     */
    public static double idf(Word term) {
        return Math.log(Indexing.numberOfResponseFiles() / term.getDocuments().size());
    }

    public static double tfIdf(Word term, Document doc) {
        return tf(term, doc) * idf(term);
    }

}
