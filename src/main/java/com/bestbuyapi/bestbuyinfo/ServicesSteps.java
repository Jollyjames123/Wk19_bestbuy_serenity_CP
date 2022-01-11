package com.bestbuyapi.bestbuyinfo;

import com.bestbuyapi.constants.EndPoints;
import com.bestbuyapi.model.ServicePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ServicesSteps {

    @Step ("Create service record with name : {0}")
    public ValidatableResponse createService(String name){

        ServicePojo servicePojo = ServicePojo.getService(name);

        return SerenityRest.given().
                log().all()
                .contentType(ContentType.JSON)
                .body(servicePojo)
                .when()
                .post()
                .then();

    }


    @Step ("Verify service record with name : {0}")
    public HashMap<String,Object> verifyServiceRecord(String name){

        return SerenityRest.given()
                .log().all()
                .queryParam("name",name)
                .when()
                .get()
                .then()
                .extract()
                .path("data.findAll{it.name='"+name+"'}.get(0)");

    }


    @Step ("Change service record for serviceID : {0}, name : {1}")
    public ValidatableResponse changeServiceRecord(int serviceID, String name){


        ServicePojo servicePojo = ServicePojo.getService(name);


        return SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("serviceID",serviceID)
                .body(servicePojo)
                .when()
                .put(EndPoints.CHANGE_SERVICE_INFO)
                .then();
    }

    @Step ("Delete service record for serviceID: {0}")
    public ValidatableResponse deleteService(int serviceID){

        return SerenityRest.given()
                .log().all()
                .pathParam("serviceID",serviceID)
                .when()
                .delete(EndPoints.DELETE_SERVICE)
                .then();
    }

    @Step ("Verify service record has been deleted for serviceID: {0}")
    public ValidatableResponse verifyServiceRecordByID(int serviceID){

        return SerenityRest.given()
                .log().all()
                .pathParam("serviceID",serviceID)
                .when()
                .get(EndPoints.GET_SERVICE_BY_ID)
                .then();

    }


}
