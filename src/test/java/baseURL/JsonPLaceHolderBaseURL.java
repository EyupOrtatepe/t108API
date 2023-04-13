package baseURL;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPLaceHolderBaseURL {

    //https://jsonplaceholder.typicode.com

    protected RequestSpecification specJsonPLace;

    @Before
    public void setup(){
        specJsonPLace = new RequestSpecBuilder()
                                .setBaseUri("https://jsonplaceholder.typicode.com")
                                .build();
    }
}
