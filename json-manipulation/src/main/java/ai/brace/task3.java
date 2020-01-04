package ai.brace;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.stream.Stream;

//This is a Solution for A1.json
public class Main
{
    public static Path getPathForResource(final String path)
    {
        try
        {
            return Paths.get(ClassLoader.getSystemResource(path).toURI());
        }
        catch (URISyntaxException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args)
    {
        JsonParser parser = new JsonParser();
        try (Stream<String> stream = Files.lines(getPathForResource("a1.json")))
        {
            String json = stream.toString();
            JsonElement element = parser.parse(json);
            if (element.isJsonObject()) {
                JsonObject books = element.getAsJsonObject();
                System.out.println(books.get("title").getAsString());
                JsonArray contents = books.getAsJsonArray("textArray");
                for (int i = 0; i < contents.size(); i++) {
                    JsonObject dataset = contents.get(i).getAsJsonObject();
                    System.out.println(dataset.get("id").getAsString());
                }

                Map<String, Integer> wordFrequency = new HashMap<>();
                for (String w : words) {
                    Integer n = map.get(w);
                    n = (n == null) ? 1 : ++n;
                    map.put(w, n);
                    System.out.println(wordFrequency.getKey() + " : " + wordFrequency.getValue());
                }
            }
            else   
            {
                System.out.println("Not a JSON Object");
            }
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
