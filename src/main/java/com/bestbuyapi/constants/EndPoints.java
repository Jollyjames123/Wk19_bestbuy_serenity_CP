package com.bestbuyapi.constants;

public class EndPoints {

    /**
     * Store End Points
     */

    public static final String GET_STORE_BY_ID = "/{storeID}";
    public static final String CHANGE_STORE_INFO = "/{storeID}";
    public static final String DELETE_STORE="/{storeID}";

    /**
     * Services End Points
     */

    public static final String GET_SERVICE_BY_ID = "/{serviceID}";
    public static final String CHANGE_SERVICE_INFO = "/{serviceID}";
    public static final String DELETE_SERVICE = "/{serviceID}";

    /**
     * Products End Points
     */


    public static final String SINGLE_PRODUCT_BY_ID = "/{productID}";


    /**
     * Categories End Points
     */

    public static final String GET_CATEGORY_BY_ID = "/{id}";
    public static final String CHANGE_CATEGORY_INFO = "/{id}";
    public static final String DELETE_CATEGORY = "/{id}";


}
