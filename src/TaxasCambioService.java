import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;

public class TaxasCambioService {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/825cb51a9add0c7ee16b63fd/latest/USD";

    public Map<String, Double> obterTaxasCambio() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

            Map<String, Double> taxasCambio = new HashMap<>();
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

            for (String moeda : new String[]{"USD", "BRL", "ARS", "BOB", "CLP", "COP"}) {
                taxasCambio.put(moeda, rates.get(moeda).getAsDouble());
            }

            return taxasCambio;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter taxas de câmbio!", e);
        }
    }
}
