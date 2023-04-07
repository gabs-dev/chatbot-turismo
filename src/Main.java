import Lexico.Lexico;

public class Main {
    public static void main(String[] args) {

        Lexico lexico = new Lexico();

        lexico.removeStopWords("Qual profissão você trabalha?");

        lexico.similar("Gabriel", "Gabrel", 2);
    }
}