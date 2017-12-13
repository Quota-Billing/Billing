package edu.rosehulman.billing;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class ObjectIdMapper extends ObjectMapper {

    public ObjectIdMapper() {
        SimpleModule module = new SimpleModule("ObjectIdmodule");
        module.addDeserializer(ObjectId.class, new ObjectIdDeserializer());
        this.registerModule(module);
    }

}