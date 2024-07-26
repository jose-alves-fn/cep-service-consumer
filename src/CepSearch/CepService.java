package CepSearch;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CepService {

    // Retorna um objeto Endereco instanciado e populado pelo retorno da requisiçao HTTP (GET)
    public Adress fetchAddress(String cep) throws IOException, InterruptedException, CepException {
        URI url = URI.create("http://viacep.com.br/ws/" + cep + "/json/");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        Gson gson = new Gson();

        // Usando uma Class com Record para retornar só alguns campos traduzidos
        // Cria um objeto CamposJson e o popula com os dados correspondentes
        AddressFields campos = gson.fromJson(json, AddressFields.class);
        Adress endereco = new Adress(campos);

        if (endereco.getCep() == null) {
            throw new CepException("CEP não encontrado: " + cep);
        }

        return endereco;
    }
}