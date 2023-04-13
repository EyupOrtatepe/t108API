package test;

import baseURL.HerokuAppbaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataHerokuApp;

import static io.restassured.RestAssured.given;

public class C21_Post_TestDataKullanimi extends HerokuAppbaseUrl {
    /*
    https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip
    bir POST request gonderdigimizde donen response’un status kodunu ve id haric
    body'sinin asagidaki gibi oldugunu test edin.
    Request body
          {
          "firstname" : "Ali",
          "lastname" : “Bak",
          "totalprice" : 500,
          "depositpaid" : false,
          "bookingdates" : {
                      "checkin" : "2021-06-01",
                      "checkout" : "2021-06-10"
                        },
          "additionalneeds" : "wi-fi"
           }
    Expected Body
    {
    "bookingid":24,
    "booking":{
            "firstname":"Ali",
            "lastname":"Bak",
            "totalprice":500,
            "depositpaid":false,
            "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                            },
            "additionalneeds":"wi-fi"
               }
    }
     */
    @Test
    public void post01(){

        specHerokuApp.pathParam("pp1","booking");

        TestDataHerokuApp testDataHerokuApp = new TestDataHerokuApp();

        JSONObject reqBody = testDataHerokuApp.reqBodyJson();

        // expected data
        JSONObject expBody = testDataHerokuApp.expBodyJson();

        // response

        Response response = given().spec(specHerokuApp).contentType(ContentType.JSON)
                .when().body(reqBody.toString()).post("/{pp1}");

        response.prettyPrint();

        //assertion


        JsonPath resJP = response.jsonPath();


        Assert.assertEquals(testDataHerokuApp.basariliStatusCode, response.getStatusCode());
        Assert.assertEquals(expBody.getJSONObject("booking").get("firstname"), resJP.get("booking.firstname"));
        Assert.assertEquals(expBody.getJSONObject("booking").get("lastname"), resJP.get("booking.lastname"));
        Assert.assertEquals(expBody.getJSONObject("booking").get("totalprice"), resJP.get("booking.totalprice"));
        Assert.assertEquals(expBody.getJSONObject("booking").get("additionalneeds"), resJP.get("booking.additionalneeds"));
        Assert.assertEquals(expBody.getJSONObject("booking").get("lastname"), resJP.get("booking.lastname"));

    }
}
