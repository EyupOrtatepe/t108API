package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C12_Post_ExpectedDataVeJsonPathIleAssertion {

    /*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un id disinda asagidaki gibi oldugunu test edin.
                        Request body
                   {
                        "firstname" : "Ahmet",
                        "lastname" : “Bulut",
                        "totalprice" : 500,
                        "depositpaid" : false,
                        "bookingdates" : {
                                 "checkin" : "2021-06-01",
                                 "checkout" : "2021-06-10"
                                          },
                        "additionalneeds" : "wi-fi"
                    }
                        Response Body - expected body
                   {
                    "bookingid":24,
                    "booking":{
                        "firstname":"Ahmet",
                        "lastname":"Bulut",
                        "totalprice":500,
                        "depositpaid":false,
                        "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                                        }
                        ,
                        "additionalneeds":"wi-fi"
                             }
                    }
         */
    @Test
    public void post01(){

        String url = "https://restful-booker.herokuapp.com/booking";
        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin","2021-06-01");
        bookingdates.put("checkout","2021-06-10");

        JSONObject reqBody = new JSONObject();

        reqBody.put("firstname","Ahmet");
        reqBody.put("lastname" , "Bulut");
        reqBody.put("totalprice" , 500);
        reqBody.put("depositpaid",false);
        reqBody.put("bookingdates",bookingdates);
        reqBody.put("additionalneeds","wi-fi");

        // expected data
        /*
         {
                    "bookingid":24,
                    "booking":{
                        "firstname":"Ahmet",
                        "lastname":"Bulut",
                        "totalprice":500,
                        "depositpaid":false,
                        "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                                        }
                        ,
                        "additionalneeds":"wi-fi"
                             }
                    }
         */

        JSONObject expBody = new JSONObject();

        expBody.put("bookingid",24);
        expBody.put("booking",reqBody);

        Response response = given().
                contentType(ContentType.JSON).
                when().
                body(reqBody.toString()).
                post(url);

        //assertion

        JsonPath restJS = response.jsonPath();

        Assert.assertEquals(expBody.getJSONObject("booking").get("firstname"),restJS.get("booking.firstname"));
        Assert.assertEquals(expBody.getJSONObject("booking").get("lastname"),restJS.get("booking.lastname"));
        Assert.assertEquals(expBody.getJSONObject("booking").get("totalprice"),restJS.get("booking.totalprice"));
        Assert.assertEquals(expBody.getJSONObject("booking").get("depositpaid"),restJS.get("booking.depositpaid"));
        Assert.assertEquals(expBody.getJSONObject("booking").get("additionalneeds"),restJS.get("booking.additionalneeds"));
        Assert.assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),restJS.get("booking.bookingdates.checkin"));
        Assert.assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),restJS.get("booking.bookingdates.checkout"));



    }

}
