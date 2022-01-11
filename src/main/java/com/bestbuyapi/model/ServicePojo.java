package com.bestbuyapi.model;

public class ServicePojo {

    /*{
        "id": 1,
            "name": "Geek Squad Services",
            "createdAt": "2016-11-17T17:56:35.881Z",
            "updatedAt": "2016-11-17T17:56:35.881Z"
    }*/


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ServicePojo getService(String name){
        ServicePojo servicePojo = new ServicePojo();
        servicePojo.setName(name);

        return servicePojo;
    }

}
