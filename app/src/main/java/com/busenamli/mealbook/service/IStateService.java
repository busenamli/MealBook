package com.busenamli.mealbook.service;

import com.busenamli.mealbook.model.StateModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

import static com.busenamli.mealbook.Constants.STATE_KEY;

public interface IStateService {

    @GET(STATE_KEY)
    Observable<StateModel> getStateMeals();
}
