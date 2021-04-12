package com.busenamli.mealbook.contract;

import com.busenamli.mealbook.model.CategoryModel;

public interface ICategoryContract {

    interface View {

        //void init();

        void showProgress();

        void hideProgress();

        void showError(String errorMsg);

        void loadList(CategoryModel categoryModel);

    }

    interface Presenter {
        void start();

        void fetchStates();
    }
}
