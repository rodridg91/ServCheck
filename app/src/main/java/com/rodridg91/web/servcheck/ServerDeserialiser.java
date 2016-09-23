package com.rodridg91.web.servcheck;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alumno on 31/08/2016.
 */
public class ServerDeserialiser implements JsonDeserializer<Server> {
    @Override
    public Server deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        final JsonObject jsonObject = json.getAsJsonObject();

        final JsonElement jsonName = jsonObject.get("name");
        final String name = jsonName.getAsString();

        final JsonElement jsonURL = jsonObject.get("url");
        final String url = jsonURL.getAsString();

        final JsonElement jsonState = jsonObject.get("state");
        final String state = jsonState.getAsString();


        final JsonArray jsonServicesArray = jsonObject.get("services").getAsJsonArray();
        final List<JsonPrimitive> services = new ArrayList<>(jsonServicesArray.size());
        for (int i = 0; i < services.size(); i++) {
            final JsonElement jsonService = jsonServicesArray.get(i);
           services.add(jsonService.getAsJsonPrimitive());
        }
        Server server = new Server(name,url,(List) services);
        return null;
    }
}
