import java.util.Map;

public class ConversorMoedas {
    private final Map<String, Double> taxasCambio;

    public ConversorMoedas(Map<String, Double> taxasCambio) {
        this.taxasCambio = taxasCambio;
    }

    public double converter(String moedaOrigem, String moedaDestino, double valor) {
        if (!taxasCambio.containsKey(moedaOrigem) || !taxasCambio.containsKey(moedaDestino)) {
            throw new IllegalArgumentException("Moeda não encontrada!");
        }
        return (valor / taxasCambio.get(moedaOrigem)) * taxasCambio.get(moedaDestino);
    }
}
