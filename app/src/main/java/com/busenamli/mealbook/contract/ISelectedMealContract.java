package com.busenamli.mealbook.contract;

import com.busenamli.mealbook.model.SelectedMealModel;

public interface ISelectedMealContract {

    interface View {

        void showProgress();

        void hideProgress();

        void showError(String errorMsg);

        void loadList(SelectedMealModel selectedMealModel);

    }

    interface Presenter {
        void start();

        void fetchStates(String mealId);
    }
}
