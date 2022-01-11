package com.bestbuyapi.bestbuyinfo;

import com.bestbuyapi.constants.EndPoints;
import com.bestbuyapi.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductsSteps {
    @Step("Creating product with name: {0}, type: {1}, price:{2}, shipping:{3}, upc:{4}, description:{5},\n" +
            "                manufacturer:{6}, model:{7}, url:{8}, image:{9} ")
    public ValidatableResponse createProduct(String name, String type, double price, int shipping, String upc, String description, String manufacturer, String model,
                                             String url, String image) {
        ProductPojo productPojo = ProductPojo.getProductPojo(name, type, price, shipping, upc, description,
                manufacturer, model, url, image);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post()
                .then();
    }

    @Step("Getting product with name: {0}")
    public HashMap<String, Object> getProductByName(String name) {
        String p1 = "data.findAll{it.name=='";
        String p2 = "'}.get(0)";

        return SerenityRest.given().log().all()
                .queryParam("name", name)
                .when()
                .get()
                .then().statusCode(200)
                .extract()
                .path(p1 + name + p2);
    }

    @Step("Updating the product with productId: {0},name: {1}, type:{2}, price:{3}, shipping:{4}, upc:{5}, description:{6},\\n\" +\n" +
            "        \"                manufacturer:{7}, model:{8}, url:{9}, image:{10}")
    public ValidatableResponse updateProduct(int productId, String name, String type, double price, int shipping, String upc, String description, String manufacturer, String model,
                                             String url, String image) {
        ProductPojo productPojo = ProductPojo.getProductPojo(name, type, price, shipping, upc, description,
                manufacturer, model, url, image);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .pathParam("productID", productId)
                .when()
                .put(EndPoints.SINGLE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Delete product with productId: {0}")
    public ValidatableResponse deleteProduct(int productId) {
        return SerenityRest.given().log().all()
                .pathParam("productID", productId)
                .when()
                .delete(EndPoints.SINGLE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Getting product with productId: {0}")
    public ValidatableResponse getProductById(int productId) {
        return SerenityRest.given().log().all()
                .pathParam("productID", productId)
                .when()
                .get(EndPoints.SINGLE_PRODUCT_BY_ID)
                .then();
    }
}
