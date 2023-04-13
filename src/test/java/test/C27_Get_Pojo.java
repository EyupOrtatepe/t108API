package test;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.DummyBaseUrl;
import pojos.PojoDummyData;
import pojos.PojoDummyExpBody;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C27_Get_Pojo extends DummyBaseUrl {
     /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
     gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
    Response Body
    {
    "status":"success",
    "data":{
            "id":3,
            "employee_name":"Ashton Cox",
            "employee_salary":86000,
            "employee_age":66,
            "profile_image":""
            },
    "message":"Successfully! Record has been fetched."
    }
     */

    @Test
    public void get01(){

        specDummy.pathParams("pp1","api","pp2","v1","pp3","employee","pp4",3);

        PojoDummyData data = new PojoDummyData(3,"Ashton Cox",86000,66,"");

        PojoDummyExpBody expBody = new PojoDummyExpBody("success",data,"Successfully! Record has been fetched.");

        Response response = given().spec(specDummy).when().get("/{pp1}/{pp2}/{pp3}/{pp4}");
        response.prettyPrint();


        PojoDummyExpBody resPojo = response.as(PojoDummyExpBody.class);

        assertEquals(expBody.getStatus(),resPojo.getStatus());
        assertEquals(expBody.getMessage(),resPojo.getMessage());
        assertEquals(expBody.getData().getId(),resPojo.getData().getId());
        assertEquals(expBody.getData().getEmployee_name(),resPojo.getData().getEmployee_name());
        assertEquals(expBody.getData().getEmployee_salary(),resPojo.getData().getEmployee_salary());
        assertEquals(expBody.getData().getEmployee_age(),resPojo.getData().getEmployee_age());
        assertEquals(expBody.getData().getProfile_image(),resPojo.getData().getProfile_image());


    }
}
