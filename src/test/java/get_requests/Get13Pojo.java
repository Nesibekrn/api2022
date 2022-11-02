package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {

     /*
        Given
            https://gorest.co.in/public/v1/users/3615
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
                                           {
                        "meta": null,
                        "data": {
                            "id": 3615,
                            "name": "Sarisha Mehrotra",
                            "email": "mehrotra_sarisha@crooks.biz",
                            "gender": "male",
                            "status": "inactive"
                        }
                    }
    */

    @Test
    public void get01Pojo(){
    //1. Step: Set the Url
        spec.pathParams("first","users","second",3615);
    //2. Step:  Set the expected data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo(3615,"Sarisha Mehrotra","mehrotra_sarisha@crooks.biz","male", "inactive");
        GoRestResponseBodyPojo expectedData = new GoRestResponseBodyPojo(null,goRestDataPojo);

    //3. Step: Send the request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

    //4. Step: Do Assertion
        GoRestResponseBodyPojo actualData =  response.as(GoRestResponseBodyPojo.class);

        assertEquals(expectedData.getMeta(),actualData.getMeta());
        assertEquals(expectedData.getData().getId(),actualData.getData().getId());
        assertEquals(expectedData.getData().getName(),actualData.getData().getName());
        assertEquals(expectedData.getData().getEmail(),actualData.getData().getEmail());
        assertEquals(expectedData.getData().getGender(),actualData.getData().getGender());
        assertEquals(expectedData.getData().getStatus(),actualData.getData().getStatus());

        assertEquals(200, response.getStatusCode());

    }
}
