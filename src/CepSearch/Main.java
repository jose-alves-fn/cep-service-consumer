package CepSearch;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, CepException {

        // Input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o CEP (somente numeros): ");
        String cep = scanner.nextLine();
        scanner.close();

        // Request
        CepService cepService = new CepService();
        try {
            Adress adress = cepService.fetchAddress(cep);
            System.out.println(adress);

            // to Json
            JSONGenerate json = new JSONGenerate();
            json.toJson(adress);

        } finally {
            System.out.println("Fim da busca.");
        }
    }
}