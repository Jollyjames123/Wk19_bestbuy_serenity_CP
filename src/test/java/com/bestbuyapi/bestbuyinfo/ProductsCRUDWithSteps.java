package com.bestbuyapi.bestbuyinfo;

import com.bestbuyapi.testbase.TestBase;
import com.bestbuyapi.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductsCRUDWithSteps extends TestBase {

    static String name = "LG Plasma TV 49Inch Model" + TestUtils.getRandomValue();
    static String type = "Electronics";
    static double price = 499.99;
    static int shipping = 1;
    static String upc = "string";
    static String description = "string";
    static String manufacturer = "string";
    static String model = "string";
    static String url = "string";
    static String image = "string";
    static int productId;

    @Steps
    ProductsSteps productsSteps;

    @Title("This will create a new product record")
    @Test
    public void test001() {

        ValidatableResponse response = productsSteps.createProduct(name, type, price, shipping, upc, description,
                manufacturer, model, url, image);
        response.log().all()
                .statusCode(201);
    }

    @Title("This will verify if a new product is created and added in the list")
    @Test
    public void test002() {

        HashMap<String, Object> productRecord = productsSteps.getProductByName(name);

        Assert.assertThat(productRecord, hasValue(name));

        productId = (int) productRecord.get("id");

    }

    @Title("This will update the product record and verify the updated information")
    @Test
    public void test003() {

        name = name + "Upgraded";
        ValidatableResponse response = productsSteps.updateProduct(productId ,name, type, price, shipping, upc, description,
                manufacturer, model, url, image);
        response.log().all()
                .statusCode(200);

        HashMap<String, Object> productRecord = productsSteps.getProductByName(name);

        Assert.assertThat(productRecord, hasValue(name));
    }

    @Title("This will delete the product record and verify if the product is deleted")
    @Test
    public void test004() {

        productsSteps.deleteProduct(productId).log().all().statusCode(200);
        productsSteps.getProductById(productId).log().all().statusCode(404);
    }
}
