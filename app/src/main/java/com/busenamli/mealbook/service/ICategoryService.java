package com.busenamli.mealbook.service;

import com.busenamli.mealbook.model.CategoryModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

import static com.busenamli.mealbook.Constants.CATEGORY_KEY;

public interface ICategoryService {

    @GET(CATEGORY_KEY)
    Observable<CategoryModel> getCategories();
}
