package com.busenamli.mealbook.service;

import com.busenamli.mealbook.model.SelectedCategoryModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.busenamli.mealbook.Constants.SELECTED_CATEGORY_KEY;

public interface ISelectedCategoryService {

    @GET(SELECTED_CATEGORY_KEY)
    Observable<SelectedCategoryModel> getSelectedState(@Query("a") String stateName);
    @GET(SELECTED_CATEGORY_KEY)
    Observable<SelectedCategoryModel> getSelectedCategory(@Query("c") String categoryName);
}
