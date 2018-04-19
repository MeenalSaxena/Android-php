package com.example.asus.layout;

public class RecipeData {

    private String name;
    private String description;
   private String image;

    public RecipeData() {
        // TODO Auto-generated constructor stub
    }

    public RecipeData(String recipe_name, String recipe_description, String image_url) {
        super();
        this.name = recipe_name;
        this.description = recipe_description;
        this.image = image_url;

    }


    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage() {

        return image;
    }

    public void setImage(String image) {
        System.out.println("!!@#$$%^&%$##$%^&");
        System.out.println(image);
        this.image = image;
    }





}