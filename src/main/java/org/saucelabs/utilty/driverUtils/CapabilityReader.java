package org.saucelabs.utilty.driverUtils;

import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CapabilityReader
{

    public static JSONObject loadCapabilities(String platFormName)
    {
        try
        {
            String content = new String(
                    Files.readAllBytes(Paths.get("src/main/resources/capabilities.json")));
            JSONObject jsonObject = new JSONObject(content);
            return jsonObject.getJSONObject(platFormName.toLowerCase());
        } catch (Exception exp)
        {
            throw new RuntimeException("Error reading capabilities: " + exp.getMessage());
        }
    }
}
