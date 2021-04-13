package com.busenamli.mealbook.contract;

import com.busenamli.mealbook.model.CategoryModel;
import com.busenamli.mealbook.model.SelectedCategoryModel;

public interface ISelectedCategoryContract {

    interface View {

        void showProgress();

        void hideProgress();

        void showError(String errorMsg);

        void loadList(SelectedCategoryModel selectedCategoryModel);

    }

    interface Presenter {
        void start();

        void fetchStates(String key, int code);
    }
}
