package org.saucelabs.api.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.saucelabs.api.models.RegisterResponse;
import org.saucelabs.api.service.RegisterService;
import org.saucelabs.utilty.apiUtils.PayloadManager;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class RegisterSteps
{

    private JSONObject payload;
    private Response response;
    private RegisterService service;

    @Given("the user prepares register payload with username {string} email {string} password {string}")
    public void preparePayload(String username, String email, String password)
    {
        payload = PayloadManager.getPayload("register_api");
        Map<String, Object> updates = new HashMap<>();
        updates.put("username", username);
        updates.put("email", email);
        updates.put("password", password);
        PayloadManager.updateFields(payload, updates);
    }

    @Given("the user sends the register API request")
    public void callApi()
    {
        service = new RegisterService();
        response = service.register(payload);
    }

    @Then("the status code should be {int}")
    public void verifyStatus(int code)
    {
        assertEquals(response.statusCode(), code);
    }

    @Then("the register response must contain id and token")
    public void verifyResponse()
    {
        RegisterResponse res = response.as(RegisterResponse.class);

        assertTrue(res.getId() > 0, "ID is missing");
        assertNotNull(res.getToken(), "Token is missing");
    }
}
