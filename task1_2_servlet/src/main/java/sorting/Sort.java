package sorting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import sorting.models.ArrayToSort;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Sort {
    public static final ObjectMapper mapper = new ObjectMapper();

    public static String sort(String json) throws IOException {
        ArrayToSort deserializedArray = deserialize(json);
        String serializedArray = serialize(deserializedArray);
//        System.out.println("deserializedCar = " + deserializedArray);
        return serializedArray;

    }

    public static String serialize(ArrayToSort ar) throws JsonProcessingException {
        return mapper
//            .writerWithDefaultPrettyPrinter()
                .writeValueAsString(ar);
    }

    public static ArrayToSort deserialize(String json) throws JsonProcessingException {
        return mapper.readValue(json, ArrayToSort.class);
    }
}
