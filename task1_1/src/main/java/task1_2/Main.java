package task1_2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import task1_2.models.ArrayToSort;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Main {
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        String dir = "task1_1\\src\\main\\java\\task1_2\\";
        String fileName = "test.json";
        String resultName = "result.json";
        try {
            File file = new File(dir + fileName);

            ArrayToSort deserializedArray = deserialize(file);
            if (deserializedArray.getTime() == 0) {
                writeJson(dir, resultName, "");
            } else {
                writeJson(dir, resultName, serialize(deserializedArray));
            }
        } catch (Exception e) {
            writeJson(dir, resultName, "");
        }
    }

    public static void writeJson(String dir, String resultName, String serialized) throws IOException{
        String json;
        if (Objects.equals(serialized, "")) {
            json = "{\n" +
                    "    \"errorMessage\": \"Array is null\"\n" +
                    "}";
        } else {json = serialized;}
        File result = new File(dir + resultName);
        FileWriter writer = new FileWriter (result);
        writer.write(json);
        writer.close();
    }

    public static ArrayToSort deserialize(File json) throws IOException {
        return mapper.readValue(json, ArrayToSort.class);
    }

    public static String serialize(ArrayToSort array) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(array);
    }
}
