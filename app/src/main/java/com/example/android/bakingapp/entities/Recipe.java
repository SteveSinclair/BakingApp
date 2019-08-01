package com.example.android.bakingapp.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    @SerializedName("ingredients")
    public final List<Ingredient> ingerdients;
    @SerializedName("steps")
    public final List<Step> steps;
    @SerializedName("id")
    private final int id;
    @SerializedName("name")
    private final String name;
    @SerializedName("servings")
    private final int servings;
    @SerializedName("image")
    private final String image;

    public Recipe(int id, String name, List<Ingredient> ingerdients, List<Step> steps, int servings, String image) {
        this.id = id;
        this.name = name;
        this.ingerdients = ingerdients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngerdients() {
        return ingerdients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}
