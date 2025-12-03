package org.saucelabs.api.service;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.saucelabs.utilty.CommonRestUtils;

public class RegisterService extends CommonRestUtils
{
    private final String baseUri = "https://reqres.in/api/register";

    public Response register(JSONObject body)
    {
        return post(baseUri, body.toString());
    }
}
