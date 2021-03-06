package lab5.client.utility;


import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import lab5.client.data.Coordinates;
import lab5.client.exceptions.IncorrectData;

/**
 * Type adapter of Coordinates for json deserialization.
 */
public class CoordinatesDeserializer implements JsonDeserializer<Coordinates> {

    @Override
    public Coordinates deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Coordinates coordinates = new Coordinates();
        try {
            coordinates.setX(jsonObject.get("x").getAsDouble());
            coordinates.setY(jsonObject.get("y").getAsLong());
        } catch (IncorrectData e) {
            return null;
        }
        return coordinates;
    }
}