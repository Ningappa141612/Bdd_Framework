package org.saucelabs.utilty;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;


public class CommonRestUtils
{
    // Core request builder
    private static RequestSpecification baseRequest()
    {
        return RestAssured.given().relaxedHTTPSValidation();
    }

    // 1. GET without anything
    public static Response get(String endpoint)
    {
        return baseRequest()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }

    // 2. GET with headers
    public static Response get(String endpoint, Map<String, String> headers)
    {
        return baseRequest()
                .headers(headers)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }

    // 3. GET with headers + queryParams
    public static Response get(String endpoint, Map<String, String> headers, Map<String, String> queryParams)
    {
        return baseRequest()
                .headers(headers)
                .queryParams(queryParams)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }

    // 4. POST with payload
    public static Response post(String endpoint, Object payload)
    {
        return baseRequest()
                .log().all()
                .body(payload)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    // 5. POST with headers + payload
    public static Response post(String endpoint, Map<String, String> headers, Object payload)
    {
        return baseRequest()
                .headers(headers)
                .body(payload)
                .when()
                .post(endpoint)
                .then()
                .extract().response();
    }

    // 6. ANY METHOD – Universal utility
    public static Response sendRequest(String method,
                                       String endpoint,
                                       Map<String, String> headers,
                                       Map<String, String> queryParams,
                                       Object payload)
    {

        RequestSpecification req = baseRequest();
        if (headers != null) req.headers(headers);
        if (queryParams != null) req.queryParams(queryParams);
        if (payload != null) req.body(payload);

        return req.when()
                .request(method.toUpperCase(), endpoint)
                .then()
                .extract().response();
    }
}
