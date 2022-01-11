package com.bestbuyapi.model;

public class CategoryPojo {

    private String id;
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CategoryPojo getCategoryPojo(String id, String name){

        CategoryPojo categoryPojo = new CategoryPojo();
        categoryPojo.setId(id);
        categoryPojo.setName(name);
        return categoryPojo;

    }

}
