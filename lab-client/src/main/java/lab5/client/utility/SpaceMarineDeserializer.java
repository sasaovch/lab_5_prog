package lab5.client.utility;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Objects;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import lab5.client.data.AstartesCategory;
import lab5.client.data.Chapter;
import lab5.client.data.Coordinates;
import lab5.client.data.SpaceMarine;
import lab5.client.exceptions.IncorrectData;


/**
 * Type adapter of SpaceMarine for json deserialization.
 */
public class SpaceMarineDeserializer implements JsonDeserializer<SpaceMarine> {

	@Override
	public SpaceMarine deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		SpaceMarine spMar = new SpaceMarine();
		try {
			spMar.setID((long) jsonObject.get("id").getAsInt());
			spMar.setCoordinates((Coordinates) context.deserialize(jsonObject.get("coordinates"), Coordinates.class));
			spMar.setHealth(jsonObject.get("health").getAsInt());
			spMar.setHeartCount(jsonObject.get("heartCount").getAsInt());
			spMar.setTime((LocalDateTime) context.deserialize(jsonObject.get("creationDateTime"), LocalDateTime.class));
			JsonElement loyal = jsonObject.get("loyal");
			if ((Objects.equals(loyal, null)) || (loyal.getAsString().equals(""))) {
				spMar.setLoyal(null);
			} else {
				spMar.setLoyal(Boolean.parseBoolean(loyal.getAsString()));
			}
			spMar.setCategory((AstartesCategory) context.deserialize(jsonObject.get("category"), AstartesCategory.class));
			spMar.setChapter((Chapter) context.deserialize(jsonObject.get("chapter"), Chapter.class));
		} catch (IncorrectData e) {
			return null;
		}
		return spMar;
	}
}