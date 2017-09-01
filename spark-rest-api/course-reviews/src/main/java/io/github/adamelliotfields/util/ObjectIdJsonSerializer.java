package io.github.adamelliotfields.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.bson.types.ObjectId;

public class ObjectIdJsonSerializer extends JsonSerializer<ObjectId> {
  @Override
  public void serialize(ObjectId objectId, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
    try {
      if (objectId == null) {
        jsonGenerator.writeNull();
      } else {
        jsonGenerator.writeString(objectId.toHexString());
      }
    } catch (IOException error) {
      error.printStackTrace();
    }
  }
}
