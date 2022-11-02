package patch_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {
       /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "Wash the dishes"
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									   }
     */

    @Test
    public void patch01(){

        //1. Step: Set the Url
        spec.pathParams("first", "todos","second",198);

        //2. Step: Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData = obj.expectedDataWithMissingKeys(null,"Wash the dishes",null);
        System.out.println(expectedData);//{title=Wash the dishes}

        //3. Step: Send the Patch Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
        response.prettyPrint();

        //4. Step: Do Assertion
        //1. Way:
        response.then().assertThat().statusCode(200).body("title",equalTo(expectedData.get("title")));

        //2. Way: If someone ask you to assert all the body, you can do following:
        Map<String,Object> expectedDataMapAllKeys = obj.expectedDataWithAllKey(10,"Wash the dishes",true);
        Map<String ,Object> actualDataMap = response.as(HashMap.class);

        assertEquals(expectedDataMapAllKeys.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMapAllKeys.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMapAllKeys.get("completed"),actualDataMap.get("completed"));

        //Or

        response.then().assertThat().statusCode(200).body("title",equalTo(expectedDataMapAllKeys.get("title")),
                "userId",equalTo(expectedDataMapAllKeys.get("userId")),
                "completed",equalTo(expectedDataMapAllKeys.get("completed")));

    }
}
