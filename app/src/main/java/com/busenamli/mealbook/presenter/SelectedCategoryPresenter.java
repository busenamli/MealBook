package com.busenamli.mealbook.presenter;

import com.busenamli.mealbook.Constants;
import com.busenamli.mealbook.MyStorage;
import com.busenamli.mealbook.contract.ISelectedCategoryContract;
import com.busenamli.mealbook.model.SelectedCategoryModel;
import com.busenamli.mealbook.service.ISelectedCategoryService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectedCategoryPresenter implements ISelectedCategoryContract.Presenter {

    ISelectedCategoryContract.View mView;
    MyStorage myStorage;

    public SelectedCategoryPresenter(ISelectedCategoryContract.View mView, MyStorage myStorage) {
        this.mView = mView;
        this.myStorage = myStorage;
    }

    @Override
    public void start() {

    }

    @Override
    public void fetchStates(String strName, int key) {

        Gson gson = new GsonBuilder().setLenient().create();

        ISelectedCategoryService iSelectedCategoryService = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)) //JSON formatında geleceğini belirttik
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //RxJava kullandıysak
                .build().create(ISelectedCategoryService.class);

        if (key == 0){

            iSelectedCategoryService.getSelectedState(strName)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<SelectedCategoryModel>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            mView.showProgress();
                        }

                        @Override
                        public void onNext(@NonNull SelectedCategoryModel selectedCategoryModel) {
                            mView.hideProgress();
                            mView.loadList(selectedCategoryModel);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }
        if (key == 1){

            iSelectedCategoryService.getSelectedCategory(strName)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<SelectedCategoryModel>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            mView.showProgress();
                        }

                        @Override
                        public void onNext(@NonNull SelectedCategoryModel selectedCategoryModel) {
                            mView.hideProgress();
                            mView.loadList(selectedCategoryModel);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            mView.showError(e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }
}
