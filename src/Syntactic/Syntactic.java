package Syntactic;

import java.util.*;

import static Syntactic.TokenType.*;

public class Syntactic {

    private Queue<String> tokens;
    private Token token;

    public Syntactic() {
        token = new Token();
    }

    public Syntactic(Queue<String> tokens) {
        this.tokens = tokens;
        token = new Token();
    }

    public void checkRule(Queue<String> tokens) {
        String rule = tokens.peek().toLowerCase();

        switch (rule) {
            case "como":
                processComo();
                break;
            case "qual":
                processQual();
                break;
            case "para":
                processPara();
                break;
            case "lista":
                processLista();
                break;
            case "clima":
                processClima();
                break;
            default:
                System.out.println("Não entendi");
        }
    }

    private void processComo() {
        Queue<String> copyTokens = new LinkedList<>();
        copyTokens.addAll(tokens);
        copyTokens.poll();

    }

    private void processQual() {

    }

    private void processPara() {

    }

    private void processLista() {
        Object[] copyTokens = copyOfTokenList().toArray();
        List<TokenType> expectedTokens = Arrays.asList(ESTABELECIMENTO, CIDADE);

        for (int i = 0; i < copyTokens.length; i++) {
            isTokenType(expectedTokens.get(i), (String) copyTokens[i]);
        }
    }

    private void processClima() {

    }

    private boolean isTokenType(TokenType type, String token) {
        Map listOfTokens = this.token.getListOfTokens();
        if (listOfTokens.containsKey(type)) {
            List<String> values = (List<String>) listOfTokens.get(type);
            if (values.contains(token))
                return true;
        }
        return false;
    }

    private Queue<String> copyOfTokenList() {
        Queue<String> copyTokens = new LinkedList<>();
        copyTokens.addAll(tokens);
        copyTokens.poll();

        return copyTokens;
    }

}