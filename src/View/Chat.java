package View;

import Model.Lexicon.Lexicon;
import Model.Syntactic.Syntactic;

import java.util.Scanner;

public class Chat {

    private Lexicon lexicon;
    private Syntactic syntactic;

    public Chat() {

    }
    public void init() {
        Scanner input = new Scanner(System.in);

        System.out.println("Olá! Como posso ajudar?");

        while (true) {
            this.lexicon = new Lexicon();
            this.syntactic = new Syntactic();
            String query = "";
            query = input.nextLine();

            lexicon.init(query);
        }

    }

}
