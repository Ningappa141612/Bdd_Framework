package org.saucelabs.utilty.apiUtils;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class PayloadManager
{
    private static final String PAYLOAD_FILE = "src/main/resources/requestPayload.json";

    /**
     * Read the entire json file
     */
    private static JSONObject loadPayLoadFile()
    {
        try
        {
            String content = new String(Files.readAllBytes(Paths.get(PAYLOAD_FILE)));
            return new JSONObject(content);
        } catch (IOException e)
        {
            throw new RuntimeException("Error loading payload file: " + PAYLOAD_FILE, e);
        }
    }

    /**
     * Get specific API payload by key
     */
    public static JSONObject getPayload(String key)
    {
        JSONObject jsonFile = loadPayLoadFile();
        if (!jsonFile.has(key))
        {
            throw new RuntimeException("Payload key not found: " + key);
        }
        return jsonFile.getJSONObject(key);
    }

    /**
     * Update multiple fields dynamically
     */
    public static JSONObject updateFields(JSONObject payload, Map<String, Object> updates)
    {
        updates.forEach(payload::put);
        return payload;
    }
}
