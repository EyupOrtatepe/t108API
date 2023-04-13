package test;

import baseURL.HerokuAppbaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoHerokuAppBooking;
import pojos.PojoHerokuAppBookingdates;
import pojos.PojoHerokuAppExpBody;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C26_Post_Pojo extends HerokuAppbaseUrl {
    /*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un id disinda asagidaki gibi oldugunu test edin.
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
                        Response Body = Expected Data
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
                                        }
                        ,
                        "additionalneeds":"wi-fi"
                              }
                    }
         */
    @Test
    public void post01(){
        specHerokuApp.pathParam("pp1","booking");

        PojoHerokuAppBookingdates bookingdates = new PojoHerokuAppBookingdates("2021-06-01","2021-06-10");

        PojoHerokuAppBooking reqBody = new PojoHerokuAppBooking("Ali","Bak",500,false,bookingdates,"wi-fi");

        // 2 - exp data

        PojoHerokuAppExpBody expBody = new PojoHerokuAppExpBody(24, reqBody);

        // 3 - response kaydet

        Response response = given().spec(specHerokuApp).contentType(ContentType.JSON).when().body(reqBody).post("/{pp1}");

        response.prettyPrint();

        // 4 - assertion

        PojoHerokuAppExpBody resPojo = response.as(PojoHerokuAppExpBody.class);

        assertEquals(expBody.getBooking().getFirstname(), resPojo.getBooking().getFirstname());
        assertEquals(expBody.getBooking().getLastname(), resPojo.getBooking().getLastname());
        assertEquals(expBody.getBooking().getTotalprice(), resPojo.getBooking().getTotalprice());
        assertEquals(expBody.getBooking().isDepositpaid(), resPojo.getBooking().isDepositpaid());
        assertEquals(expBody.getBooking().getAdditionalneeds(), resPojo.getBooking().getAdditionalneeds());
        assertEquals(expBody.getBooking().getBookingdates().getCheckin(), resPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(expBody.getBooking().getBookingdates().getCheckout(), resPojo.getBooking().getBookingdates().getCheckout());


    }

}
