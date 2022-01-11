package com.bestbuyapi.bestbuyinfo;

import com.bestbuyapi.constants.EndPoints;
import com.bestbuyapi.model.ProductPojo;
import com.bestbuyapi.testbase.TestBase;
import com.bestbuyapi.utils.TestUtils;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

//@RunWith(SerenityRunner.class)
public class ProductsCRUDTest extends TestBase {

    static String name = "LG Plasma TV 49Inch Model" + TestUtils.getRandomValue();
    static String type = "Electronics";
    static double price = 499.99;
    static int shipping = 1;
    static String upc = "string";
    static String description = "string";
    static String manufacturer = "string";
    static String model = "string";
    static String url= "string";
    static String image = "string";
    static int productId;

    @Title("This will create a new product record")
    @Test
    public void test001(){
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post()
                .then().log().all()
                .statusCode(201);
    }

    @Title("This will verify if a new product is created and added in the list")
    @Test
    public void test002(){
        String p1 = "data.findAll{it.name=='";
        String p2 = "'}.get(0)";

       HashMap<String, Object> productRecord = SerenityRest.given().log().all()
               .queryParam("name", name)
               .when()
               .get()
               .then().statusCode(200)
               .extract()
               .path(p1 + name +p2);
        Assert.assertThat(productRecord, hasValue(name));

        productId = (int) productRecord.get("id");

    }

    @Title("This will update the product record and verify the updated information")
    @Test
    public void test003(){

        name = name+"Upgraded";
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .pathParam("productID" , productId)
                .when()
                .put(EndPoints.SINGLE_PRODUCT_BY_ID)
                .then().log().all()
                .statusCode(200);

        String p1 = "data.findAll{it.name=='";
        String p2 = "'}.get(0)";
        HashMap<String, Object> productRecord = SerenityRest.given().log().all()
                .queryParam("name", name)
                //.pathParam("productID" , productId)
                .when()
                //.get(EndPoints.SINGLE_PRODUCT_BY_ID)
                .get()
                .then().statusCode(200)
                .extract()
                .path(p1 + name +p2);
        Assert.assertThat(productRecord, hasValue(name));
    }

    @Title("This will delete the product record and verify if the product is deleted")
    @Test
    public void test004(){
        SerenityRest.given().log().all()
                .pathParam("productID" , productId)
                .when()
                .delete(EndPoints.SINGLE_PRODUCT_BY_ID)
                .then().log().all()
                .statusCode(200);

        SerenityRest.given().log().all()
                .pathParam("productID" , productId)
                .when()
                .get(EndPoints.SINGLE_PRODUCT_BY_ID)
                .then().log().all()
                .statusCode(404);
    }
}
