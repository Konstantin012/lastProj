package com.telesens.mobilerest;

import com.telesens.framework.model.Gender;
import com.telesens.framework.model.Subscriber;
import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class SubscriberTests {

@BeforeClass
public void setUp(){
    RestAssured.baseURI = "http://localhost:8081/rest/json/";
    RestAssured.port = 8081;
}



    @Test
    public void testGet() {
        System.out.println("Subscribers get");
        RestAssured.baseURI = "http://localhost/rest/json";
        RestAssured.port = 8081;

        ResponseBody body = RestAssured.given()
                .get("/subscribers")
                .body();

        String jsonSubscribers = body.asString();
        System.out.println(jsonSubscribers);
    }
    @Test
    public void testAdd() {
        RestAssured.baseURI = "http://localhost/rest/json";
        RestAssured.port = 8081;
        JSONObject json = new JSONObject();
        json.put("id", 666);
        json.put("firstName", "Santa"); // Cast
        json.put("lastName", "Barbara");
        json.put("age", 25);
        json.put("gender", "m");

        RestAssured.given()
                .header("Content-Type", "application/json")
                .body(json.toJSONString())
                .post("/subscribers");

    }

    @Test(dataProvider="subscriberProvider")
    public void testDelete(Subscriber subscriber) {

        // delete
        given().log().all()
                .delete("/subscribers/{id}", subscriber.getId())
                .then().assertThat()
                .statusCode(200);

    }
    @DataProvider
    private Object[] subscriberProvider() {
        return new Object[] {
                Subscriber.newSubscriber()
                        .id(342L)
                        .firstName("Агнесса")
                        .lastName("Александрова")
                        .age(35)
                        .gender(Gender.FEMALE)
                        .build()
        };
    }

}
