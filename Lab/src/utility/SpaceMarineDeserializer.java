package utility;

import com.google.gson.*;
import spaceMarine.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class SpaceMarineDeserializer implements JsonDeserializer<SpaceMarine> {
    @Override
    public SpaceMarine deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
try{
        long id = jsonObject.get("id").getAsLong();
        String name = jsonObject.get("name").getAsString();
        Coordinates coordinates = jsonDeserializationContext.deserialize(jsonObject.get("coordinates"), Coordinates.class);
        LocalDateTime creationDate = LocalDateTime.parse(jsonObject.get("creationDate").getAsString());
        Long health = jsonObject.get("health").getAsLong();
        int heartCount = jsonObject.get("heartCount").getAsInt();
        AstartesCategory astartesCategory = jsonObject.has("astartesCategory") ? AstartesCategory.valueOf(jsonObject.get("astartesCategory").getAsString()) : null;
        MeleeWeapon meleeWeapon = jsonObject.has("meleeWeapon") ?MeleeWeapon.valueOf(jsonObject.get("meleeWeapon").getAsString()) : null;
        Chapter chapter = jsonObject.has("chapter") ? jsonDeserializationContext.deserialize(jsonObject.get("chapter"), Chapter.class) : null;
            SpaceMarine spaceMarine = new SpaceMarine(id, name, coordinates, creationDate, health, heartCount, astartesCategory, meleeWeapon, chapter);
            if (spaceMarine.validate()){
                return spaceMarine;
            } else{
                System.out.println("Поля были изменены некорректно");
            }
        } catch (Exception e){
            System.out.println("Некорректные данные в сохранении. " + e.getMessage());
        }
        return null;
    }
}
