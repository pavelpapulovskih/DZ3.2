package org.max.lesson3.seminar.accuweather;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.lesson3.home.accuweather.AccuweatherAbstractTest;
import org.max.lesson3.seminar.accuweather.location.Location;


import java.util.List;


import static io.restassured.RestAssured.given;


public class GetLocationTest extends AccuweatherAbstractTest {
    @Test
    void getLocation_autocomplete_returnYekaterinburg() {

        List<Location> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/locations/v1/cities/autocomplete?q=Yekaterinburg")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertEquals(10, response.size());
        Assertions.assertEquals("Yekaterinburg", response.get(0).getLocalizedName());
    }

    @Test
    void getLocation_15days_returnYekaterinburg() {

        List<Location> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/locations/v1/cities/15day?q=Yekaterinburg")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertEquals(15, response.size());
        Assertions.assertEquals("Yekaterinburg", response.get(0).getLocalizedName());
    }
}