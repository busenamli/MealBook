package com.busenamli.mealbook.contract;

import com.busenamli.mealbook.model.StateModel;

public interface IStateContract {

    interface View {

        void showProgress();

        void hideProgress();

        void showError(String errorMsg);

        void loadList(StateModel stateModel);

    }

    interface Presenter {
        void start();

        void fetchStates();
    }
}
