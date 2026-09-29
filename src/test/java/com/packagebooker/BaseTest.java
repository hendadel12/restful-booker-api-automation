package com.packagebooker;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;

public class BaseTest {
    public static String token;

    @BeforeSuite
    public void generateToken() {
        String body = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        token = given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .path("token");

        System.out.println("Generated Token: " + token);
    }
}
