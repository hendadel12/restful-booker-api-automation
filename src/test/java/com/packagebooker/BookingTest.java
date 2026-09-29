package com.packagebooker;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class BookingTest extends BaseTest {

    @Test
    public void shouldBeAbleToGetBookingIds() {
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .when()
                .get("/booking")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[0].bookingid", notNullValue());
    }

    @Test
    public void shouldBeAbleToFilterByFirstName() {
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .queryParam("firstname", "sally")
                .when()
                .get("/booking")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void shouldBeAbleToFilterWithLastName() {
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .queryParams("firstname", "sally", "lastname", "brown")
                .when()
                .get("/booking")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void shouldBeAbleToFilterWithCheckInAndCheckOut() {
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .queryParams("checkin", "2014-03-13", "checkout", "2014-05-21")
                .when()
                .get("/booking")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void shouldBeAbleToGetBooking() {
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .when()
                .get("/booking/1")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void shouldBeAbleToGetFirstName() {
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .when()
                .get("/booking/1")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("firstname", notNullValue()); // استخدام notNullValue بدلاً من نص ثابت لضمان عدم فشل التست عند تغير البيانات
    }

    @Test
    public void shouldBeAbleToGetLastName() {
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .when()
                .get("/booking/1")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("lastname", notNullValue());
    }

    @Test
    public void shouldBeAbleToCreateBookingSuccessfully() {
        String body = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/booking")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("booking.firstname", equalTo("Jim"));
    }

    @Test
    public void shouldBeAbleToMakePartialUpdateBookingSuccessfully() {
        String body = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\"\n" +
                "}";

        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .body(body)
                .when()
                .patch("/booking/1")
                .then()
                .assertThat().statusCode(200);
    }

@Test
public void shouldBeAbleToDeleteBookingSuccessfully() {
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .when()
                .delete("/booking/1")
                .then()
                .assertThat().statusCode(201);
    }
}