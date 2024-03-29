package get_requests;

import base_urls.GoRestBaseUrl;
import groovyjarjarpicocli.CommandLine;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestBaseUrl {

       /*
        Given
            https://gorest.co.in/public/v1/users/13
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
           {
        "meta": null,
        "data": {
            "id": 13,
            "name": "The Hon. Sanya Patil",
            "email": "hon_the_patil_sanya@ritchie-mosciski.com",
             "gender": "male",
            "status": "active"
                 }
            }
     */
     @Test
    public void get01(){
    //1. Step: Set the Url
    spec.pathParams("first","users","second",20);

    //2. Step: Set the expected data
    GoRestTestData goRestTestData = new GoRestTestData();
    Map<String , String > dataMap =  goRestTestData.dataKeyMap("Satish Kapoor PhD","phd_kapoor_satish@hammes.org","female","inactive");
    Map<String, Object> expectedData =  goRestTestData.expectedDataMap(null, dataMap);
    System.out.println(expectedData);

    //3. Step: Send the request and Get the Response
    Response response = given().spec(spec).when().get("/{first}/{second}");
    response.prettyPrint();

    //4. Step: Do Assertion

    Map<String, Object> actualData = response.as(HashMap.class);
    assertEquals(expectedData.get("meta"),actualData.get("meta"));
    assertEquals(dataMap.get("name"),((Map)actualData.get("data")).get("name"));
    assertEquals(dataMap.get("email"),((Map)actualData.get("data")).get("email"));
    assertEquals(dataMap.get("gender"),((Map)actualData.get("data")).get("gender"));
    assertEquals(dataMap.get("status"),((Map)actualData.get("data")).get("status"));
    assertEquals(200,response.getStatusCode());

    }
}