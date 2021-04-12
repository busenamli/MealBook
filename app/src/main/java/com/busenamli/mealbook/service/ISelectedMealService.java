package com.busenamli.mealbook.service;

import com.busenamli.mealbook.model.SelectedMealModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.busenamli.mealbook.Constants.SELECTED_MEAL_KEY;

public interface ISelectedMealService {

    @GET(SELECTED_MEAL_KEY)
    Observable<SelectedMealModel> getMeal(@Query("i") String mealId);

}
