import Lexico.Lexico;

public class Main {
    public static void main(String[] args) {

        Lexico lexico = new Lexico();

        String query = "O clim@ vai estar chuvoso em fortaleza no natal?";

        lexico.removeStopWords(query);
        lexico.addInSymbolTable();
        lexico.addInQueue();

    }
}