package View;

import Model.Lexicon.Lexicon;
import Model.Response;
import Model.Syntactic.Syntactic;
import Model.Translator.Translator;
import Util.FileHandler;

import java.util.Scanner;

import static Model.Enum.QueryClassification.*;

public class Chat {

    private Lexicon lexicon;
    private Syntactic syntactic;
    private final Scanner input;

    public Chat() {
        input = new Scanner(System.in);
    }
    public void init() {
        System.out.println("Olá! Como posso ajudar?");

        while (true) {
            this.lexicon = new Lexicon();
            String query;

            query = input.nextLine();

            while (query.isBlank() || query.isEmpty())
                query =  input.nextLine();

            lexicon.init(query);

            syntactic = new Syntactic(lexicon.getQueue(), lexicon.getSymbolTable());
            Response response = syntactic.checkRule();

            if (response.getResponseType() == ERROR) {
                System.out.println(response.getMessage());
            } else if (response.getResponseType() == LESS) {
                response = handleMissingTokens(response);
            }

            if (response.getResponseType() == OK) {
                Translator translator = new Translator(lexicon.getQueue());

                Response answer = translator.generateAnswer();

                System.out.println(answer.getMessage());
            }

            System.out.println("Como posso ajudar?");
        }
    }

    private Response handleMissingTokens(Response response) {
        do {
            String query;
            System.out.println(response.getMessage());
            query = input.nextLine();
            while (query.isEmpty() || query.isBlank() || (!FileHandler.readExpectedWordsFile().contains(query.toLowerCase()))) {
                System.out.println("Não entendi.");
                System.out.println(response.getMessage());
                query = input.nextLine();
            }
            lexicon.removeStopWords(query);
            lexicon.addInQueue();

            response = syntactic.checkRule();
        } while (response.getResponseType() != OK);

        return response;
    }

}
