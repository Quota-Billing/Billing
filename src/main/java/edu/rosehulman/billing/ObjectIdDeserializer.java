package edu.rosehulman.billing;

import java.io.IOException;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class ObjectIdDeserializer extends JsonDeserializer<ObjectId> {

    @Override
    public ObjectId deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        System.out.println("Deserializing!!!!");
        System.out.println((JsonNode)p.readValueAsTree());
        System.out.println((JsonNode)p.readValueAsTree());
        System.out.println((JsonNode)p.readValueAsTree());
        System.out.println((JsonNode)p.readValueAsTree());
        JsonNode oid = ((JsonNode)p.readValueAsTree()).get("objectId");
        System.out.println(((JsonNode)p.readValueAsTree()).get("objectId"));
        System.out.println(111);
        System.out.println("what: "+oid.toString());
        System.out.println(122);
        ObjectId obj = new ObjectId(oid.asText());
        System.out.println(obj.toString());
        return new ObjectId(oid.asText());
    }

}