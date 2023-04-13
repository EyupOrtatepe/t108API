package test;

import baseURL.JsonPLaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolderReqBodyPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C25_Put_PojoClass extends JsonPLaceHolderBaseURL {

    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki
 body’e sahip bir PUT request yolladigimizda donen response’in
 response body’sinin asagida verilen ile ayni oldugunu test ediniz
     Request Body
    {
    "title":"Ahmet",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }
    Expected Body
    {
    "title":"Ahmet",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }
     */

    @Test
    public void put01(){

        // 1 - URL body hazirla

        specJsonPLace.pathParams("pp1","posts","pp2",70);

        JsonPlaceHolderReqBodyPojo reqBody = new JsonPlaceHolderReqBodyPojo("Ahmet","Merhaba",10,70);

        System.out.println("reqBody = " + reqBody);

        // 2- expected body hazirla

        JsonPlaceHolderReqBodyPojo expBody = new JsonPlaceHolderReqBodyPojo("Ahmet","Merhaba",10,70);

        // 3 - response i kaydet

        Response response = given().spec(specJsonPLace).contentType(ContentType.JSON).when().body(reqBody).put("/{pp1}/{pp2}");

        response.prettyPrint();

        // 4 - Assertion

        // JsonPath resJP = response.jsonPath();
        // Map<String, Object> resMap = response.as(HashMap.class);

        JsonPlaceHolderReqBodyPojo resPojo = response.as(JsonPlaceHolderReqBodyPojo.class);

        assertEquals(expBody.getTitle(),resPojo.getTitle());
        assertEquals(expBody.getBody(),reqBody.getBody());
        assertEquals(expBody.getId(),reqBody.getId());
        assertEquals(expBody.getUserId(),reqBody.getUserId());
    }
}
