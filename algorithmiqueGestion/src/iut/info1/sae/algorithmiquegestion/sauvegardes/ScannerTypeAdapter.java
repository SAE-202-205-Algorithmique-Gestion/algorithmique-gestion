package iut.info1.sae.algorithmiquegestion.sauvegardes;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Scanner;

public class ScannerTypeAdapter extends TypeAdapter<Scanner> {
    @Override
    public void write(JsonWriter out, Scanner scanner) throws IOException {
        // Ignore writing the Scanner object
        out.nullValue();
    }

    @Override
    public Scanner read(JsonReader in) throws IOException {
        // Ignore reading the Scanner object
        in.nextNull();
        return null;
    }
}
