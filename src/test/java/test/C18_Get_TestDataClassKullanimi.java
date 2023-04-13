package test;

import baseURL.JsonPLaceHolderBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import static io.restassured.RestAssured.given;

public class C18_Get_TestDataClassKullanimi extends JsonPLaceHolderBaseURL {
    /*
  https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET
  request yolladigimizda donen response’in status kodunun 200 ve
  response body’sinin asagida verilen ile ayni oldugunu test ediniz
   Response body :
    {
    "userId":3,
    "id":22,
    "title":"dolor sint quo a velit explicabo quia nam",
    "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
    um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
    }
     */
    @Test
    public void get01(){

        specJsonPLace.pathParams("pp1","posts","pp2",22);

        TestDataJsonPlaceHolder testDataJsonPlaceHolder  = new TestDataJsonPlaceHolder();

        JSONObject expData = testDataJsonPlaceHolder.expectedBodyOlusturJsonObject();

        Response response = given().spec(specJsonPLace).when().get("/{pp1}/{pp2}");

        response.prettyPrint();


        JsonPath resJsonpath =response.jsonPath();

        Assert.assertEquals(testDataJsonPlaceHolder.basariliStatusCode, response.getStatusCode());
        Assert.assertEquals(expData.get("userId"), resJsonpath.get("userId"));
        Assert.assertEquals(expData.get("id"), resJsonpath.get("id"));
        Assert.assertEquals(expData.get("title"), resJsonpath.get("title"));
        Assert.assertEquals(expData.get("body"), resJsonpath.get("body"));




    }
}
