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

//This is a Solution for A1.json + A2.json
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
            Stream<String> streamB = Files.lines(getPathForResource("a2.json")
            String jsonB = streamB.toString();

            String fJson = json + " " + jsonB;
            JsonElement element = parser.parse(fJson);

            if (element.isJsonObject()) {
                JsonObject books = element.getAsJsonObject();
                System.out.println(books.get("title").getAsString());
                JsonArray contents = books.getAsJsonArray("textArray");
                for (int i = 0; i < contents.size(); i++) {
                    JsonObject dataset = contents.get(i).getAsJsonObject();
                    if(Integer.parseInt(dataset.get("id").getAsString)
                    System.out.println(dataset.get("id").getAsString());
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