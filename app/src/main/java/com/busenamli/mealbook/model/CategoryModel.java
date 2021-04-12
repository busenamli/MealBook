package com.busenamli.mealbook.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryModel {

    @SerializedName("categories")
    private List<StrCategoryModel> categories = null;

    public List<StrCategoryModel> getCategories(){
        return categories;
    }
}
