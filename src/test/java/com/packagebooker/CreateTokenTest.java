package com.packagebooker;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateTokenTest {
    public static String token;

    @Test
    public void shouldBeAbleToCreateTokenSuccessfully() {
        String body = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        // 2️⃣ تخزين القيمة المستخرجة في المتغير token بدلاً من مجرد التأكد منها
        token = given().baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/auth")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("token", notNullValue())
                .extract()
                .path("token"); // استخراج التوكن

        System.out.println("Extracted Token: " + token);
    }
    @Test

    public void shouldNotBeAbleToCreateTokenWithInvalidData(){
        String body="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password12\"\n" +
                "}";
        given().baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/auth")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("reason", equalTo("Bad credentials"));

    }
}