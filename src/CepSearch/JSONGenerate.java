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