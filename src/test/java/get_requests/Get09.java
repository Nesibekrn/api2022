package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/2461
        When
            I send GET Request to the url
        Then
            Response body should be like that;
        {
        "firstname": "Create2",
        "lastname": "Parchment",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
         }
     */

    @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParams("first", "booking","second", 2461);

        //2. Step: Set the expected data
        Map<String, String>  bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin","2019-01-01");
        bookingdatesMap.put("checkout","2019-02-01");

        Map<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname","Create2");
        expectedDataMap.put("lastname","Booking2");
        expectedDataMap.put("totalprice",2222);
        expectedDataMap.put("depositpaid",true);
        expectedDataMap.put("bookingdates", bookingdatesMap);
        expectedDataMap.put("additionalneeds", "Breakfast");
        System.out.println(expectedDataMap);

        //3. Step: Send the request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4. Step: Do Assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));

        assertEquals(bookingdatesMap.get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)actualDataMap.get("bookingdates")).get("checkout"));
    }
}