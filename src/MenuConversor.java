import java.util.Map;
import java.util.Scanner;

public class MenuConversor {
    public static void main(String[] args) {
        TaxasCambioService service = new TaxasCambioService();
        Map<String, Double> taxas = service.obterTaxasCambio();
        ConversorMoedas conversor = new ConversorMoedas(taxas);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n********************************************");
            System.out.println("Seja bem-vindo/a ao Conversor de Moedas =]");
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileiro");
            System.out.println("4) Real brasileiro =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Sair");
            System.out.print("Escolha uma opção válida: ");
            System.out.println("********************************************");

            int opcao = scanner.nextInt();
            if (opcao == 7) {
                System.out.println("Encerrando o programa...");
                break;
            }

            String moedaOrigem = (opcao % 2 == 1) ? "USD" : (opcao == 2 ? "ARS" : opcao == 4 ? "BRL" : "COP");
            String moedaDestino = (opcao % 2 == 0) ? "USD" : (opcao == 1 ? "ARS" : opcao == 3 ? "BRL" : "COP");

            System.out.print("Digite o valor a ser convertido: ");
            double valor = scanner.nextDouble();

            try {
                double resultado = conversor.converter(moedaOrigem, moedaDestino, valor);
                System.out.printf("Resultado: %.2f %s\n", resultado, moedaDestino);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: Moeda inválida.");
            }
        }

        scanner.close();
    }
}
