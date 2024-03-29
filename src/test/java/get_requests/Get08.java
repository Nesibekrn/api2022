package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
//import static org.apache.commons.codec.digest.UnixCrypt.body;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {
    /*
    Serialization: To convert Java Object to Json Data
    De-Serialization: To convert Json Data to Java Object

    To do De-Serialization and Serialization we can use:
    1) Gson: Google created
    2) Object Mapper: More popular
     */

    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false

            }
     */
     @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParams("first", "todos", "second",2 );

        //2. Step: Set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        expectedData.put("StatusCode", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server","cloudflare");
        System.out.println("expectedData: "+expectedData);

        //3. Step: Send the request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        Map<String,Object> actualData = response.as(HashMap.class);//De-Serialization
        System.out.println("actualData: "+actualData);

        //4. step: Do Assertion
       assertEquals(expectedData.get("userId"),actualData.get("userId"));
       assertEquals(expectedData.get("title"),actualData.get("title"));
       assertEquals(expectedData.get("completed"),actualData.get("completed"));
       assertEquals(expectedData.get("StatusCode"),response.getStatusCode());
       assertEquals(expectedData.get("Via"),response.getHeader("Via"));
       assertEquals(expectedData.get("Server"),response.getHeader("Server"));
    }

    @Test
    public void get02(){
        //1. Step: Set the Url
        spec.pathParams("first","todos","second", 2);

        //2. Step: Set the expected data
        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
        Map<String, Object> expectedDataMap = expectedData.expectedDataWithAllKey(1,"quis ut nam facilis et officia qui",false);
        expectedDataMap.put("StatusCode",200);
        expectedDataMap.put("Via","1.1 vegur");
        expectedDataMap.put("Server","cloudflare");
        System.out.println("expectedDataMap: "+expectedDataMap);

        //3. Step: Send the request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4. Step: Do Assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualData: "+actualDataMap);

        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
        assertEquals(expectedDataMap.get("StatusCode"),response.getStatusCode());
        assertEquals(expectedDataMap.get("Via"),response.getHeader("Via"));
        assertEquals(expectedDataMap.get("Server"),response.getHeader("Server"));

    }

}
