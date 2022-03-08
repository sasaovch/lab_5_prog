package lab5.client.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import com.google.gson.*;

import lab5.client.data.Chapter;
import lab5.client.data.Coordinates;
import lab5.client.data.SpaceMarine;

public class ParsingJSON {
  
    public boolean serialize(SpaceMarineCollection collection, File file) throws IOException {
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(java.time.LocalDate.class, new DateSerializer())
            .registerTypeAdapter(SpaceMarineCollection.class, new SpaceMarineCollectionSerializer())
            .registerTypeAdapter(SpaceMarine.class, new SpaceMarineSerializer())
            .create();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(gson.toJson(collection));
        writer.close();
        return true;
    }

    public SpaceMarineCollection deSerialize(String strData) throws JsonSyntaxException {
        Gson g = new GsonBuilder()
            .registerTypeAdapter(java.time.LocalDate.class, new DateDeserializer())
            .registerTypeAdapter(SpaceMarineCollection.class, new SpaceMarineCollectionDeserializer())
            .registerTypeAdapter(SpaceMarine.class, new SpaceMarineDeserializer())
            .registerTypeAdapter(Coordinates.class, new CoordinatesDeserializer())
            .registerTypeAdapter(Chapter.class, new ChapterDeserializer())
            .create();;
        //Type type = new TypeToken<SpaceMarineCollection>() {}.getType();
        if ("".equals(strData)) {
            return new SpaceMarineCollection();
        }
        SpaceMarineCollection deserCollection = g.fromJson(strData, SpaceMarineCollection.class);
        if (Objects.equals(deserCollection, null)) {
            return null;
        }
        return deserCollection;
    }
}