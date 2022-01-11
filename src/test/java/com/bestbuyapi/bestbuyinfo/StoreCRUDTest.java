package com.bestbuyapi.bestbuyinfo;

import com.bestbuyapi.testbase.TestBaseStores;
import com.bestbuyapi.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoreCRUDTest extends TestBaseStores {

    public static String name = "Fraser" + TestUtils.getRandomValue();
    public static String type = "House";
    public static String address = "12 watford street";
    public static String address2 = "abc";
    public static String city = "London";
    public static String state = "MN";
    public static String zip = "23457";
    public static double lat = 11.812314;
    public static double lng = 58.071526;
    public static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    public static int storeID;

@Steps
StoresSteps storesSteps;

    @Title("Create new store record")
    @Test
    public void test009() {

        storesSteps.createStore(name,type,address,address2,city,state,zip,lat,lng,hours).log().all().statusCode(201);

    }

    @Title("Verify new store has been created in the application and get store ID")
    @Test
    public void test010() {

        HashMap<String,Object> storeRecord = storesSteps.getStoreInformationWithName(name);
        Assert.assertThat(storeRecord,hasValue(name));
        storeID = (int) storeRecord.get("id");

    }

    @Title("Change store information and verify change has been reflected in the record")
    @Test
    public void test011() {
        name = name+TestUtils.getRandomValue();

        storesSteps.changeStoreInfo(storeID,name,type,address,address2,city,state,zip,lat,lng,hours).log().all().statusCode(200);
        HashMap<String,Object> storeRecord = storesSteps.getStoreInformationWithName(name);
        Assert.assertThat(storeRecord,hasValue(name));


    }

    @Title("Delete store record and verify store has been deleted")
    @Test
    public void test012() {

        storesSteps.deleteStoreRecord(storeID).log().all().statusCode(200);
        storesSteps.verifyStoreRecord(storeID).log().all().statusCode(404);

    }

}
