# ☕ CEP Service Consumer (Java) 

**CEP Service Consumer** is a Java project that interacts with the ViaCEP API to fetch address details based on a Brazilian postal code (CEP). The application provides an easy-to-use interface for retrieving address information and converting it to JSON format.

API: https://viacep.com.br/

---

## 🔭 Project Overview 

This project consists of a simple command-line application that:
1. Prompts the user to enter a postal code (CEP).
2. Sends a request to the ViaCEP API to retrieve address information.
3. Parses the response and displays the address details.
4. Converts the address details to JSON format and saves it to a file.

---

## 📦 Classes

### `Main`

The `Main` class is the entry point of the application. It handles user input, invokes the `CepService` to fetch address details, and uses `JSONGenerate` to generate a JSON representation of the address.

```java
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
```
---
### `CepService`

The `CepService` class is responsible for making HTTP requests to the ViaCEP API and parsing the response. It uses the `Gson` library to convert the JSON response into an `AddressFields` object, which is then used to create an `Adress` object.

```java
package CepSearch;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CepService {

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

        AddressFields campos = gson.fromJson(json, AddressFields.class);
        Adress endereco = new Adress(campos);

        if (endereco.getCep() == null) {
            throw new CepException("CEP não encontrado: " + cep);
        }

        return endereco;
    }
}
```
---
### `Adress`

The `Adress` class represents the address details returned from the ViaCEP API. It contains fields for the postal code, street, complement, neighborhood, city, and state, and provides a `toString` method for displaying the address information.

```Java
package CepSearch;

public class Adress {

    private String cep;
    private String rua;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    public Adress(AddressFields campos) {
        this.cep = campos.cep();
        this.rua = campos.logradouro();
        this.complemento = campos.complemento();
        this.bairro = campos.bairro();
        this.cidade = campos.localidade();
        this.estado = campos.uf();
    }

    // Getters and setters

    @Override
    public String toString() {
        return "Endereco retornado:" +
                "\n cep: " + cep +
                "\n rua: " + rua +
                "\n complemento: " + complemento +
                "\n bairro: " + bairro +
                "\n cidade: " + cidade +
                "\n estado: " + estado;
    }
}
```
---
### `CepException`

The `CepException` class is a custom exception used to handle errors related to CEP processing.

```Java
package CepSearch;

public class CepException extends RuntimeException {

    public CepException(String message) {
        super(message);
    }

    public CepException(String message, Throwable cause) {
        super(message, cause);
    }
}
```
---
### `AddressFields`

The `AddressFields` class is a record used to map the JSON response from the ViaCEP API to a Java object.

```Java
package CepSearch;

public record AddressFields(String cep, String logradouro, String complemento, String bairro, String localidade,
                            String uf) {
}
```
---
### `JSONGenerate`

The `JSONGenerate` class is responsible for converting the `Adress` object to a JSON file. It uses the `Gson` library to format the JSON output and save it to a file named based on the postal code.

```Java
package CepSearch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class JSONGenerate {

    public void toJson(Adress adress) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try (FileWriter writer = new FileWriter("endereço_CEP_" + adress.getCep() + ".json")) {
            gson.toJson(adress, writer);
        }
    }
}
```
---
### 💻 Requirements


- Java 11 or later
- Gson library (https://mvnrepository.com/artifact/com.google.code.gson/gson)

### 👟 How to Run

---
1. Clone the repository: git clone
```sh 
https://github.com/yourusername/cep-service-consumer.git
```

2. Navigate to the project directory: cd cep-service-consumer
```sh
cd cep-service-consumer
```

3. Compile and run the project:

```sh
javac -d bin src/CepSearch/*.java
java -cp bin CepSearch.Main
```
4. Follow the prompt to enter a postal code and view the results.
---

😉 Enjoy!!!






