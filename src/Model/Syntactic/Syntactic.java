package Model.Syntactic;

import Model.Enum.QueryClassification;
import Model.Enum.TokenType;
import Model.Response;

import java.util.*;

import static Model.Enum.QueryClassification.*;
import static Model.Enum.TokenType.*;

public class Syntactic {

    private Queue<String> tokens;
    private Token token;

    private List<String> symbolTable;

    public Syntactic() {
        token = new Token();
    }

    public Syntactic(Queue<String> tokens, List<String> symbolTable) {
        this.tokens = tokens;
        token = new Token();
        this.symbolTable = symbolTable;
    }

    public Response checkRule(Queue<String> tokens) {
        String rule = tokens.peek().toLowerCase();
        Response response = null;

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
                response =  processLista();
                break;
            case "clima":
                processClima();
                break;
            default:
                response = new Response(ERROR, "Não entendi.");
        }

        return response;
    }

    private Response processComo() {
        Queue<String> copyTokens = new LinkedList<>();
        copyTokens.addAll(tokens);
        copyTokens.poll();

        return null;
    }

    private Response processQual() {
        return null;
    }

    private Response processPara() {
        return null;
    }

    private Response processLista() {
        // SE LISTA DE TOKENS E EXPECTEDTOKENS VAZIA, TUDO OK
        // TOKENS VAZIA E EXPECTED NÃO, TOKENS FALTANDO
        // EXPECTED VAZIA E TOKENS NÃO, PALAVRAS DEMAIS
        //List[] copyTokens = copyOfTokenList().toArray();
        List<TokenType> expectedTokens = new LinkedList<>(Arrays.asList(ESTABELECIMENTO, CIDADE));

        return allTokensInQuery(expectedTokens);
    }

    private Response processClima() {
        return null;
    }

    private Response allTokensInQuery(List<TokenType> expectedTokens) {
        List<String> tokens = copyOfTokenList();
        List<String> filteredTokens = new LinkedList<>();
        List<TokenType> filteredExpectedTokens = new LinkedList<>();

        for (TokenType type : expectedTokens) {
            for (String token : tokens) {
                if (isTokenType(type, token)) {
                    filteredTokens.add(token);
                    filteredExpectedTokens.add(type);
                }
            }
        }

        tokens.removeAll(filteredTokens);
        expectedTokens.removeAll(filteredExpectedTokens);

        if (filteredTokens.size() == 0 && tokens.size() > 0) // Nenhuma palavra foi reconhecida
            return new Response(ERROR, "Não entendi.");

        return resolveTokens(tokens, expectedTokens);
    }

    // CRIAR UM OBJETO RESPONSE QUE VAI CONTER OS DADOS E SE DEU TUDO CERTO NA REQUISIÇÃO
    // VALIDAR ESSE OBJETO NA INTERAÇÃO COM O USUÁRIO E CASO ESTEJA FALTANDO ALGO PERGUNTAR
    private Response resolveTokens(List<String> tokens, List<TokenType> expectedTokens) {
        String defaultMessage = "Para qual %s você deseja?";
        Response response = new Response();

        if (tokens.size() > expectedTokens.size()) {
            this.symbolTable.addAll(tokens);
        } else if (tokens.size() < expectedTokens.size()) {
            String expected = "";
            for (int i = 0; i < expectedTokens.size(); i++) {
                if (i < expectedTokens.size() - 1)
                    expected += (expectedTokens.get(i).toString() + ", ");
                else if (i == expectedTokens.size() - 1)
                    expected += expectedTokens.get(i).toString();
            }
            response.setResponseType(LESS);
            response.setMessage(String.format(defaultMessage, expected));
        } else {
            response.setResponseType(OK);
        }

        return response;
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

    private List<String> copyOfTokenList() {
        Queue<String> copyTokens = new LinkedList<>();
        copyTokens.addAll(tokens);
        copyTokens.poll();

        List<String> tokens = new LinkedList<>();

        for (String token : copyTokens)
            tokens.add(token);

        return tokens;
    }

}