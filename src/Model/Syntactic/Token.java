package Model.Syntactic;

import Model.Enum.TokenType;

import java.util.*;

import static Model.Enum.TokenType.*;

public class Token {

    private List<String> situacao = Arrays.asList("crianças", "sozinho", "casal", "amigos", "namorado", "encontro");
    private List<String> algo = Arrays.asList("transporte", "hospedagem", "ingressos", "passagem", "malas", "hotel", "reservas");
    private List<String> quando = Arrays.asList("noite", "tarde", "manhã", "sol", "pôr do sol");
    private List<String>  pacote = Arrays.asList("viagem", "hospedagem", "ingressos", "passeios");
    private List<String> lugar = Arrays.asList("praia", "museu", "shopping", "parque", "trilha", "cachoeira");
    private List<String> forma = Arrays.asList("rápido", "carro", "pé", "andando", "ônibus", "metrô");
    private List<String> epoca = Arrays.asList("natal", "ano novo", "reveillon", "carnaval");
    private List<String> cidade = Arrays.asList("fortaleza", "gramado", "orlando", "paris", "salvador", "londres", "natal", "maceió");
    private List<String> estabelecimento = Arrays.asList("pousada", "shopping", "restaurantes", "sorveteria", "lanchonete", "farmácia", "cafeteria");
    private List<String> clima = Arrays.asList("frio", "chuvoso", "nublado", "nevando", "ensolarado");

    private Map<TokenType, List<String>> listOfTokens;

    public Token() {
        listOfTokens = new HashMap<>();
        listOfTokens.put(TokenType.SITUACAO, situacao);
        listOfTokens.put(ALGO, algo);
        listOfTokens.put(QUANDO, quando);
        listOfTokens.put(PACOTE, pacote);
        listOfTokens.put(LUGAR, lugar);
        listOfTokens.put(FORMA, forma);
        listOfTokens.put(EPOCA, epoca);
        listOfTokens.put(CIDADE, cidade);
        listOfTokens.put(ESTABELECIMENTO, estabelecimento);
        listOfTokens.put(CLIMA, clima);
    }

    public Map<TokenType, List<String>> getListOfTokens() {
        return this.listOfTokens;
    }

}
