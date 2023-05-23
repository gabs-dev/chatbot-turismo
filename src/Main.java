import Lexicon.Lexicon;
import Syntactic.Syntactic;

public class Main {
    public static void main(String[] args) {

        Lexicon lexicon = new Lexicon();

        //String query = "O clima vai estar chuvoso em fortaleza no natal?";
        String query = "Lista de restaurantes em Londres";

        lexicon.init(query);
    }
}